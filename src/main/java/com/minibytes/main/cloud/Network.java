package com.minibytes.main.cloud;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Network {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final OkHttpClient CLIENT = new OkHttpClient();
    public final String BASE_URL;
    public boolean IsOnline;
    public OfflineCluster offlineCluster;

    /*
        Constructs new CloudService.Network with the specified url

        Makes GET request to URL to test connection
     */
    public Network(String url) {
        this.BASE_URL = url;

        // Create get request to server
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        // Validate that server is real
        try (Response response = CLIENT.newCall(request).execute()) {
            this.IsOnline = true;

            System.out.println("Connected to MiniBytes Cloud!");
        } catch (Exception e) {
            this.IsOnline = false;
            this.offlineCluster = new OfflineCluster();

            System.out.println("Couldn't connect to MiniBytes Cloud!");
        }
    }

    /*
        Makes GET request to specified request URL

        Returns HashMap containing data from HTTP request
     */
    public HashMap GET(Request request) {
        Call call = CLIENT.newCall(request);

        try {
            return mapper.readValue(
                    call.execute().body().string(),
                    new TypeReference<HashMap<String,Object>>(){}
            );
        } catch (Exception e) {
            return new HashMap();
        }
    }

    /*
        Makes POST request to specified request URL

        Returns HashMap containing data from HTTP request
     */
    public HashMap POST(Request request) {
        Call call = CLIENT.newCall(request);

        try {
            return mapper.readValue(
                    call.execute().body().string(),
                    new TypeReference<HashMap<String,Object>>(){}
            );
        } catch (Exception e) {
            return new HashMap();
        }
    }

    /*
        Turns Map into JSON String
     */
    public String toJSON(Map data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            return "Failed!" + e.getStackTrace();
        }
    }
}
