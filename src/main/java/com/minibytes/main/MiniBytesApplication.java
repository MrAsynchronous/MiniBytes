package com.minibytes.main;

import com.minibytes.main.cloud.CloudService;
import com.minibytes.main.components.ByteObject;
import com.minibytes.main.controllers.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/*
    Main initialization for MiniBytes app
 */
public class MiniBytesApplication extends Application {
    private final boolean IS_PRODUCTION = true;

    // do not touch anything below this line
    //____________________________________________________________________

    private final String PROD_URL = "http://34.135.244.23:8080"; //no ddos plz
    private final String DEV_URL = "http://localhost:8080";

    public static HashMap sceneObjects = new HashMap();

    /*
        Starts the application.  Creates FXML loaders and views
     */
    @Override
    public void start(Stage stage) throws IOException {
        HashMap config = new HashMap();
        HashMap scenes = new HashMap();

        // Create CloudService with proper URL
        CloudService cloud = new CloudService(IS_PRODUCTION, IS_PRODUCTION ? PROD_URL : DEV_URL);
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
        ByteObject.cloud = cloud;

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

        // Create an animation timer to update the MainView every 2 seconds
        AnimationTimer timer = new AnimationTimer() {
            int lastUpdate = 0;

            @Override
            public void handle(long frameRate) {
                int now = Math.abs(((int) System.currentTimeMillis()) / 1000);
                if (Math.abs(now - lastUpdate) < 2 || !mainView.initialized) {
                    return;
                }

                lastUpdate = now;
                mainView.update();
            }
        };

        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}