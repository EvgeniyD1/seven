package com.example.seven.dto;

import com.example.seven.domain.Cluster;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClusterMapper {

    ClusterMapper CLUSTER_MAPPER = Mappers.getMapper(ClusterMapper.class);

    ClusterDto clusterToClusterDto(Cluster cluster);

    Cluster clusterDtoToCluster(ClusterDto clusterDto);
}
