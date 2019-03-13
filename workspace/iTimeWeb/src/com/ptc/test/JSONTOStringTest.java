package com.ptc.test;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONTOStringTest {
    static String json = "{\"Active\": \"Y\",\"DepartmentId\": \"435468\",\"EmailAddress\": \"jdoetest@ptc.com\",\"Employee\": \"Y\",\"EmployeeId\": \"00112\",\"FirstName\": \"John\",\"LastName\": \"Snow\",\"PasswordHash\": \"3456789\", \"RegionId\": \"A001\",\"UserManager\": \"1\",\"UserName\": \"Jsnow\"}";
    public static void main(String[] args) {
        JSONObject obj = new JSONObject(json);
        String pageName = obj.getString("Active");

        System.out.println(pageName);

       
    }
}