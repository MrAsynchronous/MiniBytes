/*
    Brandon Wilcox
    Dec 1 2021
 */

package com.minibytes.main.controllers;

import com.google.common.hash.Hashing;
import com.minibytes.main.MiniBytesApplication;
import com.minibytes.main.cloud.CloudService;
import com.minibytes.main.components.User;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SignupView extends BaseView {
    @FXML
    private TextField bioBox;

    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    public SignupView() { super(); }

    @FXML
    protected void onLoginButtonClicked() {
        stage.setScene(
                getScene("Login")
        );
    }

    @FXML
    protected void onSignupButtonClicked() {
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();
        String bio = bioBox.getText();

        // Attempt login
        HashMap response = cloud.Signup(username, password, bio);

        // Handle error case TODO: MAKE THIS A DIALOG
        if (response.get("message") != null) {
            System.out.println("Something went wrong!");

            return;
        }

        // Attempt to fetch userId
        String userId = (String) response.get("user_id");
        HashMap userData = cloud.GetUserInfo(userId);

        // Handle error case TODO: MAKE THIS A DIALOG
        if (userData.get("message") != null) {
            System.out.println(userData.get("message"));

            return;
        }

        // Get user info
        HashMap userInfo = (HashMap) userData.get("user_info");

        // Construct a new user
        User thisUser = new User(
                username,
                (String) response.get("user_id"),
                (String) userInfo.get("bio"),
                (int) userInfo.get("total_upvotes"),
                (int) userInfo.get("total_bytes")
        );

        MainView view = (MainView) MiniBytesApplication.sceneObjects.get("Main");
        view.initialize(thisUser);

        stage.setScene(
                getScene("Main")
        );
    }

}
