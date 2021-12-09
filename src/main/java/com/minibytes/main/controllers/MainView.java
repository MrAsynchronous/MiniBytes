/*
    Brandon Wilcox
    Dec 1 2021
 */

package com.minibytes.main.controllers;

import com.minibytes.main.components.ByteObject;
import com.minibytes.main.components.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.HashMap;

public class MainView extends BaseView{
    public HashMap<String, ByteObject> displayedBytes = new HashMap<>();
    public ArrayList bytes = new ArrayList();
    public static boolean initialized = false;
    private User user;

    @FXML
    private Label accountNameLabel;

    @FXML
    private Label biographyLabel;

    @FXML
    private Label totalBytesLabel;

    @FXML
    private Label totalUpvotesLabel;

    @FXML
    private GridPane byteView;

    @FXML
    private Button postByteButton;

    @FXML
    private TextArea byteBody;

    public MainView() { super(); }

    @FXML
    public synchronized void initialize(User user) {
        this.user = user;

        // Setup labels
        accountNameLabel.setText(user.getUsername());
        biographyLabel.setText(user.getBio());

        // Setup byteView initial grid
        byteView.getRowConstraints().set(0, new RowConstraints(128));

        // Setup post byte
        postByteButton.setOnAction(event -> {
            String body = byteBody.getText();
            if (body.length() > 128 || body.trim().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Something went wrong!");
                alert.setHeaderText(null);
                alert.setContentText("You're Byte content is too long!");

                alert.showAndWait();

                return;
            }

            HashMap response = cloud.PostByte(user.getUserId(), body);
            if (response.get("message") != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Something went wrong!");
                alert.setHeaderText(null);
                alert.setContentText((String) response.get("message"));

                alert.showAndWait();

                return;
            }

            update();
        });

        initialized = true;
    }

    public void update() {
        updateStatLabels();
        refreshByteList();

        HashMap response = cloud.GetUserInfo(user.getUserId());
        if (response.get("message") != null) {
            return;
        }

        HashMap userInfo = (HashMap) response.get("user_info");
        user.updateInfo(userInfo);
    }

    public void updateStatLabels() {
        totalBytesLabel.setText(
                String.format("Total Bytes: %d", user.getTotalBytes())
        );

        totalUpvotesLabel.setText(
                String.format("Total Upvotes: %d", user.getTotalUpvotes())
        );
    }

    private void insertRows(int count) {
        for (Node child : byteView.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(child);
            GridPane.setRowIndex(child, rowIndex == null ? count : count + rowIndex);
        }
    }

    public void refreshByteList() {
        HashMap result = cloud.GetByteFeed();
        ArrayList bytes = (ArrayList) result.get("bytes");

        ArrayList newBytes = new ArrayList();

        // Only add new bytes to newBytes
        for (int i = 0; i < bytes.size(); i++) {
            HashMap byteInfo = (HashMap) bytes.get(i);
            String byteId = (String) byteInfo.get("_id");

            ByteObject existingByte = displayedBytes.get(byteId);

            if (existingByte == null) {
                newBytes.add(byteInfo);
            } else {
                existingByte.updateUpvotes(byteInfo);
                existingByte.updateDate();
            }
        }

        // Check for no new bytes!
        if (newBytes.size() == 0) {
            return;
        }

        // Add rows
        insertRows(newBytes.size());

        for (int i = 0; i < newBytes.size(); i++) {
            HashMap byteInfo = (HashMap) newBytes.get(i);

            // Create new byte
            ByteObject newByte = new ByteObject(byteInfo);
            displayedBytes.put(newByte.getByteId(), newByte);

            newByte.upvoteButton.setOnAction(event -> {
                // Upvote byte
                HashMap response = cloud.Upvote(user.getUserId(), newByte.getByteId());
                newByte.updateUpvotes((int) response.get("upvote_count"));
            });

            // Add byte to grid
            byteView.add(newByte.container, 0, i);

            // Update size
            if (i > 0) {
                byteView.getRowConstraints().add(new RowConstraints(128));
            }
        }

        byteView.autosize();
    }
}
