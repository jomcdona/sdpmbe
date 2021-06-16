package com.ibm.garage.sdpmbe.service;

import java.util.ArrayList;

import com.ibm.garage.sdpmbe.model.deploymentsEntity;
import com.ibm.garage.sdpmbe.repository.deploymentsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class deploymentsSvc 
{
    @Autowired
    deploymentsRepo dmr;

    public void addDeployment(deploymentsEntity de)
    {
        dmr.save(de);
    }

    public ArrayList<deploymentsEntity> getDeployments()
    {
        return (ArrayList<deploymentsEntity>)dmr.findAll();
    }

    public void clearDeployments()
    {
        dmr.deleteAll();
    }
}
