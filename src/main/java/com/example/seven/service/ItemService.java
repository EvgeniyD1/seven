package com.example.seven.service;

import com.example.seven.domain.Cluster;
import com.example.seven.domain.FieldTypes;
import com.example.seven.domain.Tag;
import com.example.seven.domain.item.Item;
import com.example.seven.domain.item.TypeFive;
import com.example.seven.domain.item.TypeFour;
import com.example.seven.domain.item.TypeOne;
import com.example.seven.domain.item.TypeThree;
import com.example.seven.domain.item.TypeTwo;
import com.example.seven.dto.item.ItemDto;
import com.example.seven.dto.item.ItemMapper;
import com.example.seven.repository.ClusterRepository;
import com.example.seven.repository.ItemRepository;
import com.example.seven.repository.TagRepository;
import com.example.seven.request.ItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ClusterRepository clusterRepository;
    private final CloudService cloudService;
    private final TagRepository tagRepository;

    public Page<ItemDto> findAll(Pageable pageable) {
        return itemRepository.findAllByIdIsNotNull(pageable).map(ItemMapper.ITEM_MAPPER::itemToItemDto);
    }

    public Page<ItemDto> findAllByClusterId(Pageable pageable, Long id) {
        return itemRepository.findAllByClusterId(pageable, id).map(ItemMapper.ITEM_MAPPER::itemToItemDto);
    }

    public Page<ItemDto> fullSearch(Pageable pageable, String text) {
        List<Long> ids = itemRepository.getIds(text);
        if (!ids.isEmpty()) {
            return itemRepository.fulTextSearch(pageable, ids).map(ItemMapper.ITEM_MAPPER::itemToItemDto);
        }
        return null;
    }

    public ItemDto findOne(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            return ItemMapper.ITEM_MAPPER.itemToItemDto(item);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ItemDto create(Long clusterId, ItemRequest request) {
        Cluster cluster = clusterRepository.findById(clusterId).orElse(null);
        if (cluster == null) {
            return null;
        }
        Item item = new Item();
        item.setName(request.getName());
        item.setFieldsType(FieldTypes.valueOf(request.getFieldsType()));

        switch (request.getFieldsType()) {
            case "INTEGER": {
                TypeOne typeOne = TypeOne.builder()
                        .fieldOne(Integer.valueOf(request.getFieldOne()))
                        .fieldTwo(Integer.valueOf(request.getFieldTwo()))
                        .fieldThree(Integer.valueOf(request.getFieldThree()))
                        .item(item).build();
                item.setTypeOne(typeOne);
                break;
            }
            case "TEXT": {
                TypeTwo typeTwo = TypeTwo.builder()
                        .fieldOne(request.getFieldOne())
                        .fieldTwo(request.getFieldTwo())
                        .fieldThree(request.getFieldThree())
                        .item(item).build();
                item.setTypeTwo(typeTwo);
                break;
            }
            case "MULTILINE_TEXT": {
                TypeThree typeThree = TypeThree.builder()
                        .fieldOne(request.getFieldOne())
                        .fieldTwo(request.getFieldTwo())
                        .fieldThree(request.getFieldThree())
                        .item(item).build();
                typeThree.setItem(item);
                item.setTypeThree(typeThree);
                break;
            }
            case "BOOLEAN": {
                TypeFour typeFour = TypeFour.builder()
                        .fieldOne(Boolean.valueOf(request.getFieldOne()))
                        .fieldTwo(Boolean.valueOf(request.getFieldTwo()))
                        .fieldThree(Boolean.valueOf(request.getFieldThree()))
                        .item(item).build();
                typeFour.setItem(item);
                item.setTypeFour(typeFour);
                break;
            }
            case "DATE": {
                TypeFive typeFive = TypeFive.builder()
                        .fieldOne(new Date(request.getFieldOne()))
                        .fieldTwo(new Date(request.getFieldTwo()))
                        .fieldThree(new Date(request.getFieldThree()))
                        .item(item).build();
                typeFive.setItem(item);
                item.setTypeFive(typeFive);
                break;
            }
            default: return null;
        }
        item.setCluster(cluster);

        /*need to optimize*/
        request.getTags().forEach(tag -> item.addTag(tagRepository.findByName(tag).orElse(new Tag(tag))));
        itemRepository.save(item);
        return ItemMapper.ITEM_MAPPER.itemToItemDto(item);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ItemDto updateItem(Long id, ItemRequest request) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setName(request.getName());
            switch (item.getFieldsType()) {
                case INTEGER: {
                    item.getTypeOne().setFieldOne(Integer.valueOf(request.getFieldOne()));
                    item.getTypeOne().setFieldTwo(Integer.valueOf(request.getFieldTwo()));
                    item.getTypeOne().setFieldThree(Integer.valueOf(request.getFieldThree()));
                    break;
                }
                case TEXT: {
                    item.getTypeTwo().setFieldOne(request.getFieldOne());
                    item.getTypeTwo().setFieldTwo(request.getFieldTwo());
                    item.getTypeTwo().setFieldThree(request.getFieldThree());
                    break;
                }
                case MULTILINE_TEXT: {
                    item.getTypeThree().setFieldOne(request.getFieldOne());
                    item.getTypeThree().setFieldTwo(request.getFieldTwo());
                    item.getTypeThree().setFieldThree(request.getFieldThree());
                    break;
                }
                case BOOLEAN: {
                    item.getTypeFour().setFieldOne(Boolean.valueOf(request.getFieldOne()));
                    item.getTypeFour().setFieldTwo(Boolean.valueOf(request.getFieldTwo()));
                    item.getTypeFour().setFieldThree(Boolean.valueOf(request.getFieldThree()));
                    break;
                }
                case DATE: {
                    item.getTypeFive().setFieldOne(new Date(request.getFieldOne()));
                    item.getTypeFive().setFieldTwo(new Date(request.getFieldTwo()));
                    item.getTypeFive().setFieldThree(new Date(request.getFieldThree()));
                    break;
                }
                default: return null;
            }
            itemRepository.save(item);
            return ItemMapper.ITEM_MAPPER.itemToItemDto(item);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public String uploadImg(Long id, MultipartFile file) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            String url = cloudService.getUrl(file);
            item.setImgUrl(url);
            itemRepository.save(item);
            return url;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Item item = itemRepository.findOneForDelete(id).orElse(null);
        if (item != null) {
            item.getTags().forEach(tag -> tag.getItems().remove(item));
            itemRepository.delete(item);
        }
    }

}
