package com.minibytes.main;

import com.minibytes.main.cloud.CloudService;
import com.minibytes.main.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class MiniBytesApplication extends Application {
    private final String URL = "http://35.192.3.43:8080";
    private final String DEV_URL = "http://localhost:8080";

    public static HashMap sceneObjects = new HashMap();

    @Override
    public void start(Stage stage) throws IOException {
        HashMap config = new HashMap();
        HashMap scenes = new HashMap();

        CloudService cloud = new CloudService(DEV_URL);
        config.put("Cloud", cloud);
        config.put("Stage", stage);

        // Load FXML's
        FXMLLoader landingLoader = new FXMLLoader(MiniBytesApplication.class.getResource("landing-view.fxml"));
        FXMLLoader signupLoader = new FXMLLoader(MiniBytesApplication.class.getResource("signup-view.fxml"));
        FXMLLoader loginLoader = new FXMLLoader(MiniBytesApplication.class.getResource("login-view.fxml"));
        FXMLLoader mainLoader = new FXMLLoader(MiniBytesApplication.class.getResource("main-view.fxml"));

        // Construct scenes
        scenes.put("Landing", new Scene(landingLoader.load(), 750, 500));
        scenes.put("Signup", new Scene(signupLoader.load(), 750, 500));
        scenes.put("Login", new Scene(loginLoader.load(), 750, 500));
        scenes.put("Main", new Scene(mainLoader.load(), 750, 500));

        // Add scenes to config
        config.put("Scenes", scenes);

        // Get controllers from loaders
        LandingView landingView = landingLoader.getController();
        SignupView signupView = signupLoader.getController();
        LoginView loginView = loginLoader.getController();
        MainView mainView = mainLoader.getController();

        // Add controllers to hashmap
        sceneObjects.put("Landing", landingView);
        sceneObjects.put("Signup", signupView);
        sceneObjects.put("Login", loginView);
        sceneObjects.put("Main", mainView);

        // Setup dependencies
        BaseView.setup(config);

        // Set names
        landingView.setName("Landing");
        signupView.setName("Signup");
        loginView.setName("Login");
        mainView.setName("Main");

        // Set scene
        stage.getIcons().add(new Image("file:src/main/resources/Icon.png"));
        stage.setScene(landingView.getScene());
        stage.setTitle("MiniBytes");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}