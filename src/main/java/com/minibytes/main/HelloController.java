package com.minibytes.main;

import com.google.common.hash.Hashing;
import com.minibytes.main.cloud.CloudService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class HelloController {
    CloudService cloud;
    private final String URL = "http://35.192.3.43:8080";
    private final String DEV_URL = "http://localhost:8080";

    @FXML
    private Label result;
    @FXML
    private TextField usernameBox;
    @FXML
    private PasswordField passwordBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label welcomeText;
    @FXML
    private ListView bytelist;

    public HelloController() {
        this.cloud = new CloudService(DEV_URL);
    }

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();

        HashMap response = cloud.Login(username, password);
        System.out.println(response);
    }
}