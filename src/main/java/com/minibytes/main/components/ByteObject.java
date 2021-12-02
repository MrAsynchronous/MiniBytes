package com.minibytes.main.components;

import java.util.HashMap;

public class ByteObject {
    private final String authorId;
    private final String byteId;
    private final String body;
    private final String date;

    public ByteObject(HashMap byteInfo) {
        this.authorId = (String) byteInfo.get("author_userid");
        this.byteId = (String) byteInfo.get("_id");
        this.body = (String) byteInfo.get("body");
        this.date = (String) byteInfo.get("date");
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getByteId() {
        return byteId;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

}
