package com.minibytes.main.components;

import java.util.HashMap;

public class User {
    public static HashMap<String, User> users = new HashMap<>();

    private final String userName;
    private final String userBio;
    private final String userId;

    private int totalUpvotes;
    private int totalBytes;

    public User(String userName, String userId, String bio, int upvotes, int bytes) {
        this.userName = userName;
        this.userId = userId;
        this.userBio = bio;

        this.totalUpvotes = upvotes;
        this.totalBytes = bytes;

        users.put(userId, this);
    }

    public String getUsername() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getBio() {
        return userBio;
    }

    public int getTotalUpvotes() {
        return totalUpvotes;
    }

    public int getTotalBytes() {
        return totalBytes;
    }

    public HashMap getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, TotalBytes: %d, TotalUpvotes: %d", userName, userId, userBio, totalBytes, totalUpvotes);
    }
}
