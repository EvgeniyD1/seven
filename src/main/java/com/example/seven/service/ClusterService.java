package com.example.seven.service;

import com.example.seven.domain.Cluster;
import com.example.seven.domain.User;
import com.example.seven.dto.ClusterDto;
import com.example.seven.dto.ClusterMapper;
import com.example.seven.repository.ClusterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ClusterService {

    private final ClusterRepository clusterRepository;
    private final CloudService cloudService;

    public Page<ClusterDto> findAllByUserUsername(Pageable pageable, String username) {
        return clusterRepository.findAllByUserUsername(pageable, username)
                .map(ClusterMapper.CLUSTER_MAPPER::clusterToClusterDto);
    }

    public ClusterDto findOne(Long id) {
        Cluster cluster = clusterRepository.findById(id).orElse(null);
        if (cluster != null) {
            return ClusterMapper.CLUSTER_MAPPER.clusterToClusterDto(cluster);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ClusterDto create(User user, ClusterDto clusterDto) {
        Cluster cluster = ClusterMapper.CLUSTER_MAPPER.clusterDtoToCluster(clusterDto);
        cluster.setUser(user);
        clusterRepository.save(cluster);
        return ClusterMapper.CLUSTER_MAPPER.clusterToClusterDto(cluster);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public String uploadImg(Long id, MultipartFile file) {
        Cluster cluster = clusterRepository.findById(id).orElse(null);
        if (cluster != null && file != null) {
            String url = cloudService.getUrl(file);
            cluster.setImgUrl(url);
            clusterRepository.save(cluster);
            return url;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ClusterDto update(Long id, ClusterDto clusterDto) {
        Cluster cluster = clusterRepository.findById(id).orElse(null);
        if (cluster != null) {
            cluster.setName(clusterDto.getName());
            cluster.setDescription(clusterDto.getDescription());
            cluster.setFieldOne(clusterDto.getFieldOne());
            cluster.setFieldTwo(clusterDto.getFieldTwo());
            cluster.setFieldThree(clusterDto.getFieldThree());
            clusterRepository.save(cluster);
            return ClusterMapper.CLUSTER_MAPPER.clusterToClusterDto(cluster);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    /*need to optimize*/
    public void delete(Long id) {
        Cluster cluster = clusterRepository.findById(id).orElse(null);
        if (cluster != null) {
            cluster.getItems().forEach(item ->
                    item.getTags().forEach(tag ->
                            tag.getItems().remove(item)));
            clusterRepository.delete(cluster);
        }
    }

    public Page<ClusterDto> findAllOrderByItemsSize(Pageable pageable) {
        return clusterRepository.findAllOrderByItemsSize(pageable)
                .map(ClusterMapper.CLUSTER_MAPPER::clusterToClusterDto);
    }
}
