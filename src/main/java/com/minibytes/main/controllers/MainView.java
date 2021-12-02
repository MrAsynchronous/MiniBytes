/*
    Brandon Wilcox
    Dec 1 2021
 */

package com.minibytes.main.controllers;

import com.minibytes.main.components.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

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
    public void setupLabels(User user) {

        accountNameLabel.setText(user.getUsername());
        biographyLabel.setText(user.getBio());
        totalBytesLabel.setText(
                String.format("Total Bytes: %d", user.getTotalBytes())
        );

        totalUpvotesLabel.setText(
                String.format("Total Upvotes: %d", user.getTotalUpvotes())
        );

    }
}
