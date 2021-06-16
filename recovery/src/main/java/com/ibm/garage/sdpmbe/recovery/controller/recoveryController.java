package com.ibm.garage.sdpmbe.recovery.controller;

import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


import com.ibm.garage.sdpmbe.recovery.model.recoveryEntity;
import com.ibm.garage.sdpmbe.recovery.service.recoveryService;
import com.ibm.garage.sdpmbe.recovery.utils.recoveryJson;

@RestController
public class recoveryController {

    @Autowired
    recoveryService rs;

    @PostMapping(path = "/addrecovery", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public void addRecovery(@RequestBody String recoveryEntry) throws IOException, ParseException
    {
      recoveryEntity re = new recoveryEntity();
      Gson gson = new Gson();
      recoveryJson rsj = gson.fromJson(recoveryEntry, recoveryJson.class);
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
      java.util.Date parsed = format.parse(rsj.getRecoverydate());
      java.sql.Date sqldate = new java.sql.Date(parsed.getTime());
      java.sql.Time sqltime = new java.sql.Time(parsed.getTime());
      re.setRecstartdate(sqldate);
      re.setRecstarttime(sqltime);
      re.setRecduration(rsj.getRecoveryduration());
      rs.addRecovery(re);
   }
 
   @GetMapping(path = "/getrecoveries", produces = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     @CrossOrigin(origins = "http://localhost:3000")
     public String getRecoveries() throws IOException
     {
       ArrayList<recoveryEntity> recoveryEntries = rs.getRecoveries();
       ArrayList <JSONObject>jsonArray = new ArrayList<JSONObject>();
       int size = recoveryEntries.size();
       for (int i=0; i < size; ++i)
       {
         JSONObject jsonEntry = new JSONObject();
         recoveryEntity re = recoveryEntries.get(i);
         SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
         String ampmtime =  sdf.format(re.getRecstarttime());
         jsonEntry.put("id", String.valueOf(re.getId().intValue()));
         jsonEntry.put("Date", re.getRecstartdate().toString() + " " + ampmtime);
         jsonEntry.put("Duration", String.valueOf(re.getRecduration()));
         jsonArray.add(jsonEntry);
       }
 
       int aSize = jsonArray.size();
       String retString = "";
       for (int i=0; i < aSize; ++i)
       {
           JSONObject entry = jsonArray.get(i);
           StringWriter out = new StringWriter();
           entry.writeJSONString(out);
           retString = retString + "\t" + out.toString() + ",\n";
  
       }
 
       if (retString.length() > 0)
       {
         retString = retString.substring(0, retString.length()-2);
         retString = "[\n" + retString + "\n  ]";
       }
       else
         retString = "[]";
       
       return(retString);
     }
 
     @GetMapping(path = "/clearrecoveries")
     public void clearRecoveries()
     {
       rs.clearRecoveries();
     }
    
}
