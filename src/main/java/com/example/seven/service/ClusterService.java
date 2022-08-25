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
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ClusterService {

    private final ClusterRepository clusterRepository;
    private final CloudService cloudService;

    public Page<Cluster> findAll(Pageable pageable, String username) {
        return clusterRepository.findAllByUserUsername(pageable, username);
    }

    public Cluster findOne(Long id) {
        return clusterRepository.findById(id).orElse(null);
    }

    public Cluster create(User user, ClusterDto clusterDto) {
        Cluster cluster = ClusterMapper.CLUSTER_MAPPER.clusterDtoToCluster(clusterDto);
        cluster.setUser(user);
        return clusterRepository.save(cluster);
    }

    public String uploadImg(Long id, MultipartFile file) {
        Cluster cluster = findOne(id);
        if (cluster != null && file != null) {
            String url = cloudService.getUrl(file);
            cluster.setImgUrl(url);
            clusterRepository.save(cluster);
            return url;
        }
        return null;
    }

    public Cluster update(Long id, ClusterDto clusterDto) {
        Cluster cluster = findOne(id);
        if (cluster != null) {
            cluster.setName(clusterDto.getName());
            cluster.setDescription(clusterDto.getDescription());
            cluster.setFieldOne(clusterDto.getFieldOne());
            cluster.setFieldTwo(clusterDto.getFieldTwo());
            cluster.setFieldThree(clusterDto.getFieldThree());
            return clusterRepository.save(cluster);
        }
        return null;
    }

    public void delete(Long id){
        Cluster cluster = findOne(id);
        clusterRepository.delete(cluster);
    }

    public Page<Cluster> findAllOrderByItemsSize(Pageable pageable){
        return clusterRepository.findAllOrderByItemsSize(pageable);
    }
}
