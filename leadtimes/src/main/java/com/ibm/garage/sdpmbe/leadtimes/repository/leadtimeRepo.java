package com.ibm.garage.sdpmbe.leadtimes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.garage.sdpmbe.leadtimes.model.leadtimeEntity;

@Repository
public interface leadtimeRepo extends CrudRepository<leadtimeEntity, Long>
{
    
}
