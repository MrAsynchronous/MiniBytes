/*
    Brandon Wilcox
    Nov 20 2021
 */

package com.minibytes.main.cloud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class OfflineCluster {
    ArrayList<HashMap> users = new ArrayList<>();
    ArrayList<HashMap> bytes = new ArrayList<>();

    public OfflineCluster() {

        users.add(createUser(
                "Brandon",
                "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f",
                "This is a test account owo"
        ));

        users.add(createUser(
                "Kevin",
                "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f",
                "This is a test account uwu"
        ));

        users.add(createUser(
                "Nathan",
                "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f",
                "This is a test account _w_"
        ));

        for (int i = 0; i < 3; i++) {
            HashMap user = users.get(i);

            bytes.add(createByte(
                    (String) user.get("user_id"),
                    "This is a test byte from the OfflineCluster!"
            ));
        }
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
        HashMap<String, Object> newByte = new HashMap<>();
        HashMap<String, Object> metaData = new HashMap<>();

        metaData.put("count", 0);
        metaData.put("users", new ArrayList<String>());

        newByte.put("author_userid", user_id);
        newByte.put("body", body);
        newByte.put("comments", new ArrayList<HashMap>());
        newByte.put("votes", metaData);
        newByte.put("date", getDate());
        newByte.put("_id", UUID.randomUUID().toString());

        return newByte;
    }

    public HashMap Login(String username, String password) {
        for (HashMap user : users) {
            String usr = (String) user.get("name");
            String pswd = (String) user.get("password");

            if (usr.equals(username)) {
                if (pswd.equals(password)) {
                    HashMap res = new HashMap();
                    res.put("user_id", (String) user.get("user_id"));

                    return res;
                } else {
                    HashMap res = new HashMap();
                    res.put("message", "Incorrect password!");

                    return res;
                }
            }
        }

        HashMap res = new HashMap();
        res.put("message", "User not found!");

        return res;
    }

    public HashMap Signup(String username, String password, String bio) {
        HashMap newUser = createUser(username, password, bio);
        HashMap res = new HashMap();

        res.put("user_id", (String) newUser.get("user_id"));

        return res;
    }

    public HashMap PostByte(String user_id, String body) {
        HashMap newByte = createByte(user_id, body);
        HashMap res = new HashMap();
        res.put("byte_id", newByte.get("_id"));

        bytes.add(newByte);

        return res;
    }

    public HashMap CommentOnByte(String user_id, String byte_id, String body) {

        for (HashMap bite : bytes) {
            String byteid = (String) bite.get("_id");

            if (byteid.equals(byte_id)) {
                ArrayList comments = (ArrayList) bite.get("comments");
                HashMap comment = new HashMap();
                comment.put("author_userid", user_id);
                comment.put("body", body);
                comment.put("date", getDate());
                comment.put("_id", UUID.randomUUID().toString());

                comments.add(comment);

                HashMap res = new HashMap();
                res.put("byte_id", byte_id);
                res.put("comments", comments);

                return res;
            }
        }

        HashMap res = new HashMap();
        res.put("message", "Byte not found!");

        return res;
    }

    public HashMap UpvoteByte(String user_id, String byte_id) {
        for (HashMap bite : bytes) {
            String byteid = (String) bite.get("_id");

            if (byteid.equals(byte_id)) {
                HashMap votes = (HashMap) bite.get("votes");
                ArrayList<String> users = (ArrayList) votes.get("users");

                for (String userid : users) {
                    if (userid.equals(user_id)) {
                        HashMap res = new HashMap();
                        res.put("message", "You already upvoted this byte!");

                        return res;
                    }
                }

                votes.put("count", ((int) votes.get("count")) + 1);
                users.add(user_id);

                HashMap res = new HashMap();
                res.put("byte_id", byte_id);
                res.put("upvote_count", votes.get("count"));

                return res;
            }
        }

        HashMap res = new HashMap();
        res.put("message", "Byte not found!");

        return res;
    }

    public HashMap GetUserInfo(String user_id) {
        for (HashMap user : users) {
            String usr_id = (String) user.get("user_id");

            if (user_id.equals(usr_id)) {
                HashMap res =  new HashMap();
                res.put("name", user.get("name"));
                res.put("bio", user.get("bio"));
                res.put("total_bytes", user.get("total_bytes"));
                res.put("total_upvotes", user.get("total_upvotes"));

                return res;
            }
        }

        HashMap res = new HashMap();
        res.put("message", "User not found!");

        return res;
    }

    private String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        return formatter.format(date);
    }
}
