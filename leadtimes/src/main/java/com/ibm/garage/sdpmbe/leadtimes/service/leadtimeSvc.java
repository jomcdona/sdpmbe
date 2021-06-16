package com.ibm.garage.sdpmbe.leadtimes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.garage.sdpmbe.leadtimes.repository.leadtimeRepo;
import com.ibm.garage.sdpmbe.leadtimes.model.leadtimeEntity;

import java.util.ArrayList;

@Service
public class leadtimeSvc 
{
    @Autowired
    leadtimeRepo ltr;

    public void addLeadTime(leadtimeEntity lte)
    {
        if (ltr.count() == 0)
            ltr.save(lte);
        else
        {
            ltr.deleteAll();
            ltr.save(lte);
        }
    }

    public ArrayList <leadtimeEntity>getLeadTime()
    {
        return (ArrayList<leadtimeEntity>)ltr.findAll();
    }

}
