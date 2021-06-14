package com.ibm.garage.sdpmbe.sdpmbe.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;


@RestController
@ResponseBody
public class Sdpmbecontroller
{

    String producesString = MediaType.APPLICATION_JSON_VALUE;
    @GetMapping(path = "/testdummy", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String testdummy() throws IOException
    {
      ArrayList <JSONObject>jsonArray = new ArrayList<JSONObject>();
      for (int i=0; i<10; ++i)
      {

        JSONObject jsonEntry = new JSONObject();
        jsonEntry.put("name", "foo" + i);
        jsonEntry.put("num", new Integer(100) + i );
        jsonEntry.put("balance", new Double(1000.21 * i));
         if (i%2 == 0)
            jsonEntry.put("is_vip", new Boolean(true));
        else
            jsonEntry.put("is_vip", new Boolean(false));

        jsonArray.add(jsonEntry);
      }
      int aSize = jsonArray.size();
      String retString = "";
      for (int i=0; i < aSize; ++i)
      {
          JSONObject entry = jsonArray.get(i);
          StringWriter out = new StringWriter();
          entry.writeJSONString(out);
          retString = retString + out.toString() + ",\n";

      }
      retString = retString.substring(0, retString.length()-2);
      retString = "[" + retString + "]";
      return(retString);
    }

    @GetMapping(path = "/getdeployments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getdeployments() throws IOException
    {
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
        jsonEntry.put("Date:", retDate);
        jsonEntry.put("Time:", retTime);
        jsonArray.add(jsonEntry);
      };
 
      int aSize = jsonArray.size();
      String retString = "";
      for (int i=0; i < aSize; ++i)
      {
          JSONObject entry = jsonArray.get(i);
          StringWriter out = new StringWriter();
          entry.writeJSONString(out);
          retString = retString + out.toString() + ",\n";
 
      }
      retString = retString.substring(0, retString.length()-2);
      retString = "[" + retString + "]";
      return(retString);
   }
 

}