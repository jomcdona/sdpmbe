package com.ibm.garage.sdpmbe.recovery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.garage.sdpmbe.recovery.model.recoveryEntity;

@Repository
public interface recoveryRepo extends CrudRepository<recoveryEntity, Long> 
{
    
}
