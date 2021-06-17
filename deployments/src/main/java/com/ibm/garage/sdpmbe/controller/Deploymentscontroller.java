package com.ibm.garage.sdpmbe.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.ibm.garage.sdpmbe.model.deploymentsEntity;
import com.ibm.garage.sdpmbe.service.deploymentsSvc;


@RestController
@ResponseBody
public class Deploymentscontroller
{

    @Autowired
    deploymentsSvc ds;

    @GetMapping(path = "/getdeploymentsdummy", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String getdeployments() throws IOException
    {
      System.out.println("getDeployments()");
      ArrayList <JSONObject>jsonArray = new ArrayList<JSONObject>();
      for (int i=0; i<10; ++i)
      {
         
        JSONObject jsonEntry = new JSONObject();
        Calendar c = Calendar.getInstance();
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH)+1);
        if (month.length() == 1)
           month = "0" + month;
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(c.get(Calendar.MINUTE));
        String second = String.valueOf(c.get(Calendar.SECOND));
        if (second.length() == 1)
          second = "0" + second;
        String am_pm = "AM";
        if (Integer.parseInt(hour) >= 12)
          am_pm = "PM";

        if (hour.length() == 1)
          hour = "0" + hour;

        if (minute.length() == 1)
          minute = "0" + minute;

        String retDate = year + "-" + day + "-" + month;
        String retTime = hour + ":" + minute + ":" + second + " " + am_pm;
        jsonEntry.put("id", i);
        jsonEntry.put("Date", retDate);
        jsonEntry.put("Time", retTime);
        jsonArray.add(jsonEntry);
      };
 
      int aSize = jsonArray.size();
      String retString = "";
      for (int i=0; i < aSize; ++i)
      {
          JSONObject entry = jsonArray.get(i);
          StringWriter out = new StringWriter();
          entry.writeJSONString(out);
          retString = retString + "\t" + out.toString() + ",\n";
 
      }
      retString = retString.substring(0, retString.length()-2);
      retString = "[\n" + retString + "\n  ]";
      return(retString);
   }

   @PostMapping(path = "/adddeployment", consumes = MediaType.TEXT_PLAIN_VALUE)
   @ResponseBody
   @CrossOrigin(origins = "http://localhost:3000")
   public void addDeployment(@RequestBody String deploymententry) throws IOException, ParseException
   {

     deploymentsEntity de = new deploymentsEntity();
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
     java.util.Date parsed = format.parse(deploymententry);
     java.sql.Date sqldate = new java.sql.Date(parsed.getTime());
     java.sql.Time sqltime = new java.sql.Time(parsed.getTime());
     de.setDeploymentDate(sqldate);
     de.setDeploymentTime(sqltime);
     ds.addDeployment(de);
  }

  @GetMapping(path = "/getdeployments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String getdeploymentsdb() throws IOException
    {
      ArrayList<deploymentsEntity> deploymentEntries = ds.getDeployments();
      ArrayList <JSONObject>jsonArray = new ArrayList<JSONObject>();
      int size = deploymentEntries.size();
      for (int i=0; i < size; ++i)
      {
        JSONObject jsonEntry = new JSONObject();
        deploymentsEntity de = deploymentEntries.get(i);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        String ampmtime =  sdf.format(de.getDeploymentTime());
        jsonEntry.put("id", String.valueOf(de.getId().intValue()));
        jsonEntry.put("Date", de.getDeploymentDate().toString());
        jsonEntry.put("Time", ampmtime);
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

    @GetMapping(path = "/cleardeployments")
    @CrossOrigin(origins = "http://localhost:3000")
    public void clearDeplyments()
    {
      ds.clearDeployments();
    }
 

}
