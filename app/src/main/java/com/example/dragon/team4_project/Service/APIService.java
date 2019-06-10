package com.example.dragon.team4_project.Service;

public class APIService {

    private static String base_url = "http://203.234.62.86/mucon/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
