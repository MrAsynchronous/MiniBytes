package com.minibytes.main;

import com.minibytes.main.controllers.LandingView;
import com.minibytes.main.controllers.LoginView;
import com.minibytes.main.controllers.SignupView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class MiniBytesApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        HashMap views = new HashMap();

        // Load FXML's
        FXMLLoader landingLoader = new FXMLLoader(MiniBytesApplication.class.getResource("landing-view.fxml"));
        FXMLLoader signupLoader = new FXMLLoader(MiniBytesApplication.class.getResource("signup-view.fxml"));
        FXMLLoader loginLoader = new FXMLLoader(MiniBytesApplication.class.getResource("login-view.fxml"));

        // Construct scenes
        views.put("Landing", new Scene(landingLoader.load(), 750, 500));
        views.put("Signup", new Scene(signupLoader.load(), 750, 500));
        views.put("Login", new Scene(loginLoader.load(), 750, 500));

        // Construct view controllers
        LandingView landingView = new LandingView(stage, views);
        SignupView signupView = new SignupView(stage, views);
        LoginView loginView = new LoginView(stage, views);

        // Set scene
        stage.setScene(landingView.getScene());

        stage.setTitle("MiniBytes");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}