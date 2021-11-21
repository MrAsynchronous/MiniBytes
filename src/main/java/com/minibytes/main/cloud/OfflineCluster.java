package com.minibytes.main.cloud;

import com.fasterxml.jackson.core.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class OfflineCluster {
    ArrayList<HashMap<String, String>> users = new ArrayList<>();
    ArrayList<HashMap<String, String>> bytes = new ArrayList<>();

    public OfflineCluster() {

    }

    private void InitializeUsers() {

    }

    private void InitializeBytes() {
        HashMap<String, String> nathan = new HashMap<>();
        nathan.put("name", "Nathan");
        nathan.put("bio", "This is a test account!");
        nathan.put("password", "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f");
        nathan.put("user_id", "ab97620x9czb61");
        nathan.put("total_bytes", "0");
        nathan.put("total_upvotes", "0");

        HashMap<String, String> brandon = new HashMap<>();
        brandon.put("name", "Brandon");
        brandon.put("bio", "This is a test account!");
        brandon.put("password", "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f");
        brandon.put("user_id", "b9726x0o81391823x");
        brandon.put("total_bytes", "0");
        brandon.put("total_upvotes", "0");

        HashMap<String, String> kevin = new HashMap<>();
        kevin.put("name", "Kevin");
        kevin.put("bio", "This is a test account!");
        kevin.put("password", "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f");
        kevin.put("user_id", "bb86296789xajv7g31");
        kevin.put("total_bytes", "0");
        kevin.put("total_upvotes", "0");

        users.add(nathan);
        users.add(brandon);
        users.add(kevin);
    }

    private HashMap createUser(String username, String password, String bio) {
        HashMap<String, String> user = new HashMap<>();

        user.put("name", username);
        user.put("password", password);
        user.put("bio", bio);
        user.put("user_id", UUID.randomUUID().toString());
        user.put("total_bytes", "0");
        user.put("total_upvotes", "0");

        return user;
    }

    private HashMap createByte(String user_id, String body) {

    }

    public HashMap Login(String username, String password) {


        return new HashMap();
    }

    public HashMap Signup(String username, String password, String bio) {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("name", username);
        userData.put("password", password);
        userData.put("bio", bio);
        userData.put("user_id", UUID.randomUUID().toString());
        userData.put("total_bytes", "0");
        userData.put("total_upvotes", "0");

        return new HashMap();
    }

    public HashMap PostByte() {
        return new HashMap();
    }

    public HashMap CommentOnByte() {
        return new HashMap();
    }

    public HashMap UpvoteByte() {
        return new HashMap();
    }

    public HashMap GetUserInfo() {
        HashMap<String, String> data = new HashMap<>();

        data.put("name", "John Doe");
        data.put("bio", "I am a person.");
        data.put("total_bytes", "19");
        data.put("total_upvotes", "31");

        return data;
    }

}
