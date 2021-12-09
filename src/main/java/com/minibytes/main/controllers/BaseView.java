package com.minibytes.main.controllers;

import com.minibytes.main.MiniBytesApplication;
import com.minibytes.main.cloud.CloudService;
import com.minibytes.main.components.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;

/*
    BaseView is a janky way of sharing memory between all of the views using public vars
 */
public class BaseView {
    public static CloudService cloud;
    public static HashMap scenes;
    public static Stage stage;

    private String viewName;

    public BaseView() { }

    /*
        Write certain things to shared memory
     */
    public static void setup(HashMap config) {
        cloud = (CloudService) config.get("Cloud");
        scenes = (HashMap) config.get("Scenes");
        stage = (Stage) config.get("Stage");
    }

    // Set's the name the view
    public void setName(String viewName) {
        this.viewName = viewName;
    }

    // Returns the scene of the view
    public Scene getScene() {
        return (Scene) scenes.get(viewName);
    }

    // Returns the scene with a given name
    public Scene getScene(String sceneName) {
        return (Scene) scenes.get(sceneName);
    }

    // Handles user sign in  / sign up
    public boolean handleUserSignin(HashMap signinResponse) {
        // Handle error case
        if (signinResponse.get("message") != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong!");
            alert.setHeaderText(null);
            alert.setContentText((String) signinResponse.get("message"));

            alert.showAndWait();

            return false;
        }

        // Attempt to fetch userId
        String userId = (String) signinResponse.get("user_id");
        HashMap userData = cloud.GetUserInfo(userId);

        // Handle error case
        if (userData.get("message") != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong!");
            alert.setHeaderText(null);
            alert.setContentText((String) signinResponse.get("message"));

            alert.showAndWait();

            return false;
        }

        // Get user info
        HashMap userInfo = (HashMap) userData.get("user_info");

        // Construct a new user
        User thisUser = new User(
                (String) userInfo.get("name"),
                userId,
                (String) userInfo.get("bio"),
                (int) userInfo.get("total_upvotes"),
                (int) userInfo.get("total_bytes")
        );

        // Initialize core
        MainView view = (MainView) MiniBytesApplication.sceneObjects.get("Main");
        view.initialize(thisUser);

        return true;
    }
}
