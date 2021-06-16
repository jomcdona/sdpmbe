package com.ibm.garage.sdpmbe.recovery.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.garage.sdpmbe.recovery.repository.recoveryRepo;
import com.ibm.garage.sdpmbe.recovery.model.recoveryEntity;

@Service
public class recoveryService {

    @Autowired
    recoveryRepo rr;
    

    public void addRecovery(recoveryEntity re)
    {
        rr.save(re);
    }

    public ArrayList<recoveryEntity> getRecoveries()
    {
        return (ArrayList<recoveryEntity>)rr.findAll();
    }

    public void clearRecoveries()
    {
        rr.deleteAll();
    }

    
}
