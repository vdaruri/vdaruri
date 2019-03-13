package com.ptc.test;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GetEmployeeTest 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://ptcapicatalog.azure-api.net/tcard-getemployee/v1/invoke/vdaruri");


            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "ea7b1b9fe11a4dc5b859ad597933f1b9");


            // Request body
      /*      StringEntity reqEntity = new StringEntity("{body}");
            ((HttpResponse) request).setEntity(reqEntity);*/

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            System.out.println(entity);            
            if (entity != null) 
            {
                String JsonResponse = EntityUtils.toString(entity);
                
                System.out.println(JsonResponse);
                
                JsonResponse = JsonResponse.substring(JsonResponse.indexOf("[") + 1);
                JsonResponse = JsonResponse.substring(0, JsonResponse.indexOf("]"));
                
                System.out.println(JsonResponse);
                
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(JsonResponse);
                String prettyJsonString = gson.toJson(je);
                
                System.out.println(prettyJsonString);
                
                JSONObject obj = new JSONObject(prettyJsonString);
                
                String pageName = obj.getString("FirstName");

                System.out.println(pageName);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

