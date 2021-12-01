package com.minibytes.main.controllers;

import com.minibytes.main.MiniBytesApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class LandingView {
    private static Stage stage;

    private static Scene landingView;
    private static Scene signupView;
    private static Scene loginView;

    public LandingView() {

    }

    public LandingView(Stage stage, HashMap views) throws IOException {
        this.stage = stage;

        this.landingView = (Scene) views.get("Landing");
        this.signupView = (Scene) views.get("Signup");
        this.loginView = (Scene) views.get("Login");
    }

    public Scene getScene() { return landingView; }

    @FXML
    protected void onLoginButtonClicked() {
        stage.setScene(loginView);
    }

    @FXML
    protected void onSignupButtonClicked() {
        stage.setScene(signupView);
    }

}
