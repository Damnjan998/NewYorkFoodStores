package com.damnjan.nystores.repository;

import com.damnjan.nystores.document.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StoreRepository extends ElasticsearchRepository<Store, String> {

    Page<Store> findByEntityNameContainingOrStreetNameContaining(String name, String streetName, Pageable pageable);

}
