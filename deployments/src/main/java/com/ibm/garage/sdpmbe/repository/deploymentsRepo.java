package com.ibm.garage.sdpmbe.repository;

import com.ibm.garage.sdpmbe.model.deploymentsDM;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface deploymentsRepo extends CrudRepository<deploymentsDM, Long> {
    
}
