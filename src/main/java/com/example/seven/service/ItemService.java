package com.example.seven.service;

import com.example.seven.domain.Cluster;
import com.example.seven.domain.FieldTypes;
import com.example.seven.domain.item.Item;
import com.example.seven.domain.item.TypeFive;
import com.example.seven.domain.item.TypeFour;
import com.example.seven.domain.item.TypeOne;
import com.example.seven.domain.item.TypeThree;
import com.example.seven.domain.item.TypeTwo;
import com.example.seven.repository.ClusterRepository;
import com.example.seven.repository.ItemRepository;
import com.example.seven.request.ItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ClusterRepository clusterRepository;
    private final CloudService cloudService;

    public Page<Item> findAll(Pageable pageable){
        return itemRepository.findAllByIdIsNotNull(pageable);
    }

    public Page<Item> findAllByClusterId(Pageable pageable, Long id) {
        return itemRepository.findAllByClusterId(pageable, id);
    }

    public Page<Item> fullSearch(Pageable pageable, String text){
        List<Long> ids = itemRepository.getIds(text);
        if (!ids.isEmpty()){
            return itemRepository.fulTextSearch(pageable, ids);
        }
        return null;
    }

    public Item findOne(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item create(Long clusterId, ItemRequest request) {
        Cluster cluster = clusterRepository.findById(clusterId).orElse(null);
        if (cluster == null) {
            return null;
        }
        Item item = new Item();
        item.setName(request.getName());
        item.setTag(request.getTag());
        item.setFieldsType(FieldTypes.valueOf(request.getFieldsType()));
        item.setCluster(cluster);
        itemRepository.save(item);
        switch (request.getFieldsType()) {
            case "INTEGER": {
                TypeOne typeOne = getTypeOne(request, item);
                item.setTypeOne(typeOne);
                return itemRepository.save(item);
            }
            case "TEXT": {
                TypeTwo typeTwo = getTypeTwo(request, item);
                item.setTypeTwo(typeTwo);
                return itemRepository.save(item);
            }
            case "MULTILINE_TEXT": {
                TypeThree typeThree = getTypeThree(request, item);
                item.setTypeThree(typeThree);
                return itemRepository.save(item);
            }
            case "BOOLEAN": {
                TypeFour typeFour = getTypeFour(request, item);
                item.setTypeFour(typeFour);
                return itemRepository.save(item);
            }
            case "DATE": {
                TypeFive typeFive = getTypeFive(request, item);
                item.setTypeFive(typeFive);
                return itemRepository.save(item);
            }
            default: return null;
        }
    }

    public Item updateItem(Long id, ItemRequest request){
        Item item = itemRepository.findById(id).orElse(null);
        if (item!=null){
            item.setName(request.getName());
            item.setTag(request.getTag());
            switch (item.getFieldsType()) {
                case INTEGER: {
                    item.getTypeOne().setFieldOne(Integer.valueOf(request.getFieldOne()));
                    item.getTypeOne().setFieldTwo(Integer.valueOf(request.getFieldTwo()));
                    item.getTypeOne().setFieldThree(Integer.valueOf(request.getFieldThree()));
                    return itemRepository.save(item);
                }
                case TEXT: {
                    item.getTypeTwo().setFieldOne(request.getFieldOne());
                    item.getTypeTwo().setFieldTwo(request.getFieldTwo());
                    item.getTypeTwo().setFieldThree(request.getFieldThree());
                    return itemRepository.save(item);
                }
                case MULTILINE_TEXT: {
                    item.getTypeThree().setFieldOne(request.getFieldOne());
                    item.getTypeThree().setFieldTwo(request.getFieldTwo());
                    item.getTypeThree().setFieldThree(request.getFieldThree());
                    return itemRepository.save(item);
                }
                case BOOLEAN: {
                    item.getTypeFour().setFieldOne(Boolean.valueOf(request.getFieldOne()));
                    item.getTypeFour().setFieldTwo(Boolean.valueOf(request.getFieldTwo()));
                    item.getTypeFour().setFieldThree(Boolean.valueOf(request.getFieldThree()));
                    return itemRepository.save(item);
                }
                case DATE: {
                    item.getTypeFive().setFieldOne(new Date(request.getFieldOne()));
                    item.getTypeFive().setFieldTwo(new Date(request.getFieldTwo()));
                    item.getTypeFive().setFieldThree(new Date(request.getFieldThree()));
                    return itemRepository.save(item);
                }
                default: return null;
            }
        }
        return null;
    }

    public String uploadImg(Long id, MultipartFile file){
        Item item = itemRepository.findById(id).orElse(null);
        if (item!=null){
            String url = cloudService.getUrl(file);
            item.setImgUrl(url);
            itemRepository.save(item);
            return url;
        }
        return null;
    }

    public void delete(Long id){
        Item item = itemRepository.findById(id).orElse(null);
        if (item!=null){
            itemRepository.delete(item);
        }
    }

    private TypeFive getTypeFive(ItemRequest request, Item item) {
        TypeFive typeFive = new TypeFive();
        typeFive.setItem(item);
        typeFive.setFieldOne(new Date(request.getFieldOne()));
        typeFive.setFieldTwo(new Date(request.getFieldTwo()));
        typeFive.setFieldThree(new Date(request.getFieldThree()));
        return typeFive;
    }

    private TypeFour getTypeFour(ItemRequest request, Item item) {
        TypeFour typeFour = new TypeFour();
        typeFour.setItem(item);
        typeFour.setFieldOne(Boolean.valueOf(request.getFieldOne()));
        typeFour.setFieldTwo(Boolean.valueOf(request.getFieldTwo()));
        typeFour.setFieldThree(Boolean.valueOf(request.getFieldThree()));
        return typeFour;
    }

    private TypeThree getTypeThree(ItemRequest request, Item item) {
        TypeThree typeThree = new TypeThree();
        typeThree.setItem(item);
        typeThree.setFieldOne(request.getFieldOne());
        typeThree.setFieldTwo(request.getFieldTwo());
        typeThree.setFieldThree(request.getFieldThree());
        return typeThree;
    }

    private TypeTwo getTypeTwo(ItemRequest request, Item item) {
        TypeTwo typeTwo = new TypeTwo();
        typeTwo.setItem(item);
        typeTwo.setFieldOne(request.getFieldOne());
        typeTwo.setFieldTwo(request.getFieldTwo());
        typeTwo.setFieldThree(request.getFieldThree());
        return typeTwo;
    }

    private TypeOne getTypeOne(ItemRequest request, Item item) {
        TypeOne typeOne = new TypeOne();
        typeOne.setItem(item);
        typeOne.setFieldOne(Integer.valueOf(request.getFieldOne()));
        typeOne.setFieldTwo(Integer.valueOf(request.getFieldTwo()));
        typeOne.setFieldThree(Integer.valueOf(request.getFieldThree()));
        return typeOne;
    }

}
