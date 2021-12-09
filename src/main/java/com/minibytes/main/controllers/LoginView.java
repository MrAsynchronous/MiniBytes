/*
    Brandon Wilcox
    Dec 1 2021
 */

package com.minibytes.main.controllers;

import com.google.common.hash.Hashing;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.nio.charset.StandardCharsets;

public class LoginView extends BaseView {
    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    /*
        Completely useless constructor used for JavaFx to do its thing
     */
    public LoginView() { super(); }

    @FXML
    protected void onLoginButtonClicked() {
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();

        // Attempt to login
        boolean success = handleUserSignin(cloud.Login(username, password));

        if (success) {
            stage.setScene(
                    getScene("Main")
            );
        }
    }

    @FXML
    protected void onSignupButtonClicked() {
        stage.setScene(
                getScene("Signup")
        );
    }
}
