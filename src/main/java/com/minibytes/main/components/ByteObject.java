package com.minibytes.main.components;

import com.minibytes.main.cloud.CloudService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class ByteObject {
    public static CloudService cloud;

    private final String authorId;
    private final String byteId;
    private final String body;
    private final String date;

    private final HashMap userInfo;
    private final String authorName;

    private ArrayList usersUpvoted;
    private int upvoteCount;

    public final Pane container;
    private final Label usernameLabel;
    private final Label upvoteLabel;
    private final Label bodyLabel;
    private final Label timeLabel;
    public final Button upvoteButton;

    public ByteObject(HashMap byteInfo) {
        HashMap voteInfo = (HashMap) byteInfo.get("votes");

        this.authorId = (String) byteInfo.get("author_userid");
        this.usersUpvoted = (ArrayList) voteInfo.get("users");
        this.upvoteCount = (int) voteInfo.get("count");
        this.byteId = (String) byteInfo.get("_id");
        this.body = (String) byteInfo.get("body");
        this.date = (String) byteInfo.get("date");

        // Fetch user info
        HashMap response = cloud.GetUserInfo(authorId);
        if (response.get("message") != null) {
            System.out.println(byteInfo);
        }

        // Cache userinfo
        this.userInfo = (HashMap) response.get("user_info");
        this.authorName = (String) userInfo.get("name");

        // Create container
        container = new Pane();
        container.setMinSize(304, 128);
        container.setPrefSize(304, 128);
        container.setStyle("-fx-background-color: #82E0AA");

        // Create label to display username
        usernameLabel = new Label();
        usernameLabel.setText(authorName);
        usernameLabel.setMinSize(152, 20);
        usernameLabel.setPrefSize(152, 20);
        usernameLabel.setAlignment(Pos.TOP_LEFT);
        usernameLabel.setLayoutX(5);
        usernameLabel.setLayoutY(5);
        usernameLabel.setStyle("-fx-text-fill: #34495E");

        // Create label to display upvotes
        upvoteLabel = new Label();
        upvoteLabel.setMinSize(152, 20);
        upvoteLabel.setPrefSize(152, 20);
        upvoteLabel.setAlignment(Pos.TOP_RIGHT);
        upvoteLabel.setLayoutX(131);
        upvoteLabel.setLayoutY(5);
        upvoteLabel.setStyle("-fx-text-fill: #34495E");

        bodyLabel = new Label();
        bodyLabel.setText(body);
        bodyLabel.setMinSize(279, 60);
        bodyLabel.setPrefSize(279, 60);
        bodyLabel.setAlignment(Pos.TOP_LEFT);
        bodyLabel.setWrapText(true);
        bodyLabel.setLayoutX(5);
        bodyLabel.setLayoutY(30);
        bodyLabel.setStyle("-fx-text-fill: #34495E");

        timeLabel = new Label();
        timeLabel.setText(date);
        timeLabel.setMinSize(101, 20);
        timeLabel.setPrefSize(101, 20);
        timeLabel.setAlignment(Pos.BOTTOM_LEFT);
        timeLabel.setLayoutX(5);
        timeLabel.setLayoutY(105);
        timeLabel.setStyle("-fx-text-fill: #34495E");

        upvoteButton = new Button();
        upvoteButton.setText("Upvote");
        upvoteButton.setMinSize(84, 26);
        upvoteButton.setPrefSize(84, 26);
        upvoteButton.setLayoutX(199);
        upvoteButton.setLayoutY(95);
        upvoteButton.setStyle("-fx-text-fill: #34495E");
        upvoteButton.setStyle("-fx-background-color: #F4F6F7");

        container.getChildren().add(usernameLabel);
        container.getChildren().add(upvoteLabel);
        container.getChildren().add(bodyLabel);
        container.getChildren().add(timeLabel);
        container.getChildren().add(upvoteButton);

        updateUpvotes(upvoteCount);
    }

    public void updateUpvotes(int count) {
        upvoteCount = count;
        upvoteLabel.setText(String.format("%d Upvotes", count));
    }

    public void updateUpvotes(HashMap byteInfo) {
        HashMap voteInfo = (HashMap) byteInfo.get("votes");
        usersUpvoted = (ArrayList) voteInfo.get("users");

        updateUpvotes((int) voteInfo.get("count"));
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
