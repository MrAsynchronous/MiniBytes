package com.minibytes.main.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class SignupView {
    private static Stage stage;

    private static Scene signupView;
    private static Scene loginView;

    public SignupView() {

    }

    public SignupView(Stage stage, HashMap views) throws IOException {
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

        System.out.println("Signup!");

    }

}
