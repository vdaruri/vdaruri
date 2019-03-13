package com.iTime.dao;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.iTime.jdbc.JDBCUtil;
import com.iTime.model.User;

public class UserDAOImpl implements UserDAO {

	private Connection dbConnection;
	private PreparedStatement pStmt;

	private String SQL_SELECT = "SELECT name, address FROM users WHERE email = ? AND password = ?";
	private String SQL_INSERT = "INSERT INTO users (name,email,password,address) VALUES (?,?,?,?)";

/*	public UserDAOImpl() {
		dbConnection = JDBCUtil.getConnection();
	}*/

	public User loginUser(User user) {
		
		
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://ptcapicatalog.azure-api.net/tcard-getemployee/v1/invoke/vdaruri");


            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "ea7b1b9fe11a4dc5b859ad597933f1b9");

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

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
                  
                  String firstName = obj.getString("FirstName");
                  String lastName = obj.getString("LastName");
                  String userName = obj.getString("UserName");

                  user.setFirstName(firstName);
                  user.setLastName(lastName);
                  user.setUserName(userName);
                  System.out.println(firstName);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

		
		
		return user;
	}

	public int createUser(User user) {
		int result = 0;
		try {
			pStmt = dbConnection.prepareStatement(SQL_INSERT);
			/*pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getEmail());
			pStmt.setString(3, user.getPassword());
			pStmt.setString(4, user.getAddress());*/
			result = pStmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
}