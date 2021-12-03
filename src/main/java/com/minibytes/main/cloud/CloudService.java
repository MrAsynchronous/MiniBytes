/*
    Brandon Wilcox
    Nov 20 2021
 */

package com.minibytes.main.cloud;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class CloudService extends Network {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public CloudService(String url) {
        super(url);
    }

    /*
        Registers a user to Minibytes and returns the user_id required
        for making other requests

        @param username String
        @param password String MUST BE HASH
        @returns HashMap {
            user_id: String
        }
     */
    public HashMap Signup(String username, String password, String bio) {

        if (!IsOnline) {
            return offlineCluster.Signup(username, password, bio);
        }

        Map<String, String> elements = new HashMap();
        elements.put("name", username);
        elements.put("password", password);
        elements.put("bio", bio);

        RequestBody body = RequestBody.create(toJSON(elements), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signup")
                .post(body)
                .build();

        return POST(request);
    }

    /*
        Logs a user in to Minibytes and returns the user_id required
        for making other requests

        @param username String
        @param password String
        @returns HashMap {
            user_id: String
        }
     */
    public HashMap Login(String username, String password) {

        if (!IsOnline) {
            return offlineCluster.Login(username, password);
        }

        Map<String, String> elements = new HashMap();
        elements.put("name", username);
        elements.put("password", password);

        RequestBody body = RequestBody.create(toJSON(elements), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/login")
                .post(body)
                .build();

        return POST(request);
    }

    /*
        Returns the user info for the user_id

        @param user_id String
        @returns HashMap {
            name: String,
            bio: String,
            total_bytes: Integer
            total_upvotes: Integer
        }
     */
    public HashMap GetUserInfo(String user_id) {
        Map<String, String> elements = new HashMap();
        elements.put("user_id", user_id);

        RequestBody body = RequestBody.create(toJSON(elements), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/getuserinfo")
                .post(body)
                .build();

        return POST(request);
    }

    /*
        Posts a byte

        @param user_id: String
        @param body: String
        @return HashMap {
            byte_id: String
        }
     */
    public HashMap PostByte(String user_id, String body) {
        Map<String, String> elements = new HashMap();
        elements.put("user_id", user_id);
        elements.put("body", body);

        RequestBody resBody = RequestBody.create(toJSON(elements), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/bytes/post")
                .post(resBody)
                .build();

        return POST(request);
    }

    /*
        Posts a comment to a byte

        @param user_id: String
        @param byte_id: String
        @param body: String
        @return HashMap {
            byte_id: String,
            comments: [Comments]
        }
     */
    public HashMap CommentOnByte(String user_id, String byte_id, String body) {
        Map<String, String> elements = new HashMap();
        elements.put("user_id", user_id);
        elements.put("byte_id", byte_id);
        elements.put("body", body);

        RequestBody resBody = RequestBody.create(toJSON(elements), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/bytes/comment")
                .post(resBody)
                .build();

        return POST(request);
    }

    /*
        Upvotes a byte

        @param user_id: String
        @param byte_id: String
        @return HashMap {
            upvote_count: Integer
        }
     */
    public HashMap Upvote(String user_id, String byte_id) {
        Map<String, String> elements = new HashMap();
        elements.put("user_id", user_id);
        elements.put("byte_id", byte_id);

        RequestBody resBody = RequestBody.create(toJSON(elements), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/bytes/upvote")
                .post(resBody)
                .build();

        return POST(request);
    }

    /*
        Returns an array of all bytes and their comments to be posted to the feed

        @return HashMap {
            Byte,
            Byte
        }
     */
    public HashMap GetByteFeed() {
        Request request = new Request.Builder()
                .url(BASE_URL + "/bytes/fetch")
                .build();

        return GET(request);
    }
}
