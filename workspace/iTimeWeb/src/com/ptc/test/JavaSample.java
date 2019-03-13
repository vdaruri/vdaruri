package com.ptc.test;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class JavaSample 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://ptcapicatalog.azure-api.net/tcard-createemployee/v1/invoke");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "ea7b1b9fe11a4dc5b859ad597933f1b9");


            // Request body
            StringEntity reqEntity = new StringEntity("{\"Active\": \"Y\",\"DepartmentId\": \"435468\",\"EmailAddress\": \"jdoetest@ptc.com\",\"Employee\": \"Y\",\"EmployeeId\": \"00112\",\"FirstName\": \"John\",\"LastName\": \"Snow\",\"PasswordHash\": \"3456789\", \"RegionId\": \"A001\",\"UserManager\": \"1\",\"UserName\": \"Jsnow\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
