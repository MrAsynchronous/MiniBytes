package com.minibytes.main.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class LoginView {
    private static Stage stage;

    private static Scene signupView;
    private static Scene loginView;

    public LoginView() {

    }

    public LoginView(Stage stage, HashMap views) throws IOException {
        this.stage = stage;

        this.signupView = (Scene) views.get("Signup");
        this.loginView = (Scene) views.get("Login");
    }

    public Scene getScene() { return loginView; }

    @FXML
    protected void onLoginButtonClicked() {

        System.out.println("Login!");

    }

    @FXML
    protected void onSignupButtonClicked() {
        stage.setScene(signupView);
    }

}
