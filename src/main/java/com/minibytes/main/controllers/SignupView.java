package com.minibytes.main.controllers;

import com.google.common.hash.Hashing;
import com.minibytes.main.cloud.CloudService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SignupView {
    private static CloudService cloud;
    private static Stage stage;

    private static Scene signupView;
    private static Scene loginView;

    @FXML
    private TextField bioBox;

    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    public SignupView() {

    }

    public SignupView(Stage stage, HashMap views, CloudService cloud) {
        this.cloud = cloud;
        this.stage = stage;

        this.signupView = (Scene) views.get("Signup");
        this.loginView = (Scene) views.get("Login");
    }

    public Scene getScene() { return signupView; }

    @FXML
    protected void onLoginButtonClicked() {
        stage.setScene(loginView);
    }

    @FXML
    protected void onSignupButtonClicked() {
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();
        String bio = bioBox.getText();

        System.out.println(String.format("Attempting signup user: %s", username));

        // Attempt login
        HashMap response = cloud.Signup(username, password, bio);

        System.out.println(response);

    }

}
