package com.ibm.garage.sdpmbe.service;

import java.util.ArrayList;

import com.ibm.garage.sdpmbe.model.deploymentsDM;
import com.ibm.garage.sdpmbe.repository.deploymentsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class deploymentsSvc 
{
    @Autowired
    deploymentsRepo dmr;

    public void addDeployment(deploymentsDM dm)
    {
        dmr.save(dm);
    }

    public ArrayList<deploymentsDM> getDeployments()
    {
        return (ArrayList<deploymentsDM>)dmr.findAll();
    }
}
