package com.minibytes.main.controllers;

import com.minibytes.main.cloud.CloudService;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class BaseView {
    public static CloudService cloud;
    public static HashMap scenes;
    public static Stage stage;

    private String viewName;

    public BaseView() { }

    public static void setup(HashMap config) {
        cloud = (CloudService) config.get("Cloud");
        scenes = (HashMap) config.get("Scenes");
        stage = (Stage) config.get("Stage");
    }

    public void setName(String viewName) {
        this.viewName = viewName;
    }

    public Scene getScene() {
        return (Scene) scenes.get(viewName);
    }

    public Scene getScene(String sceneName) {
        return (Scene) scenes.get(sceneName);
    }
}
