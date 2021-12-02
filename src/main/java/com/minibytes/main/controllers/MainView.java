/*
    Brandon Wilcox
    Dec 1 2021
 */

package com.minibytes.main.controllers;

import com.minibytes.main.components.ByteObject;
import com.minibytes.main.components.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.HashMap;

public class MainView extends BaseView{
    @FXML
    private Label accountNameLabel;

    @FXML
    private Label biographyLabel;

    @FXML
    private Label totalBytesLabel;

    @FXML
    private Label totalUpvotesLabel;

    @FXML
    private ScrollPane mainScroll;

    public MainView() { super(); }

    @FXML
    public void initialize(User user) {

        // Setup labels
        accountNameLabel.setText(user.getUsername());
        biographyLabel.setText(user.getBio());

        totalBytesLabel.setText(
                String.format("Total Bytes: %d", user.getTotalBytes())
        );

        totalUpvotesLabel.setText(
                String.format("Total Upvotes: %d", user.getTotalUpvotes())
        );

        // Setup feed
        HashMap response = cloud.GetByteFeed();
        if (response.get("message") != null) {
            System.out.println("Something went wrong!");

            return;
        }

        // Update list
        ArrayList bytes = (ArrayList) response.get("bytes");
        refreshByteList(bytes);
    }

    public void refreshByteList(ArrayList bytes) {
        for (int i = 0; i < bytes.size() - 1; i++) {
            HashMap byteInfo = (HashMap) bytes.get(i);
            ByteObject newByte = new ByteObject(byteInfo);

            System.out.println(newByte.getBody());
        }
    }
}
