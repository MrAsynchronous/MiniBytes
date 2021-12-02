/*
    Brandon Wilcox
    Dec 1 2021
 */

package com.minibytes.main.controllers;

import com.google.common.hash.Hashing;

import com.minibytes.main.MiniBytesApplication;
import com.minibytes.main.components.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class LoginView extends BaseView {
    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    /*
        Completely useless constructor used for JavaFx to do its thing
     */
    public LoginView() { super(); }

    @FXML
    protected void onLoginButtonClicked() {
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();

        // Attempt to login
        HashMap response = cloud.Login(username, password);

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
        view.setupLabels(thisUser);

        stage.setScene(
              getScene("Main")
        );
    }

    @FXML
    protected void onSignupButtonClicked() {
        stage.setScene(
                getScene("Signup")
        );
    }
}
