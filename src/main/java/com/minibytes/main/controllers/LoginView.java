package com.minibytes.main.controllers;

import com.google.common.hash.Hashing;
import com.minibytes.main.cloud.CloudService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class LoginView {
    private static CloudService cloud;
    private static Stage stage;

    private static Scene signupView;
    private static Scene loginView;

    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    public LoginView() {

    }

    public LoginView(Stage stage, HashMap views, CloudService cloud) {
        this.cloud = cloud;
        this.stage = stage;

        this.signupView = (Scene) views.get("Signup");
        this.loginView = (Scene) views.get("Login");
    }

    public Scene getScene() { return loginView; }

    @FXML
    protected void onLoginButtonClicked() {
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();

        System.out.println(String.format("Attempting login to user: %s", username));

        // Attempt login
        HashMap response = cloud.Login(username, password);

        System.out.println(response);
    }

    @FXML
    protected void onSignupButtonClicked() {
        stage.setScene(signupView);
    }

}
