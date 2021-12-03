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
        handleUserSignin(cloud.Signup(username, password, bio));

        stage.setScene(
                getScene("Main")
        );
    }

}
