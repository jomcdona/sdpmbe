package com.ibm.garage.sdpmbe.leadtimes.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.ibm.garage.sdpmbe.leadtimes.service.leadtimeSvc;
import com.ibm.garage.sdpmbe.leadtimes.model.leadtimeEntity;

@RestController
public class LeadtimeController {

    @Autowired
    leadtimeSvc lts;

    @GetMapping(path = "/getleadtime", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String getLeadTime()
    {
        ArrayList<leadtimeEntity> leadtimeEntry = lts.getLeadTime();
        String retString = "";
        if (leadtimeEntry.size() > 0)
        {
            leadtimeEntity lte = (leadtimeEntity)leadtimeEntry.get(0);
            retString = String.valueOf(lte.getLeadtime());
        }

        return retString;

    }

    @GetMapping(path = "/addleadtime", consumes = MediaType.TEXT_PLAIN_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public void addleadtime(@RequestParam String leadtime)
    {
        leadtimeEntity lte = new leadtimeEntity();
        lte.setLeadtime(Integer.parseInt(leadtime));
        lts.addLeadTime(lte);
    }
    
}
