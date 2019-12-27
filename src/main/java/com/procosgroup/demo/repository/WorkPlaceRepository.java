package com.procosgroup.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.procosgroup.demo.domain.WorkPlace;

public interface WorkPlaceRepository extends PagingAndSortingRepository<WorkPlace, Long> {

}
