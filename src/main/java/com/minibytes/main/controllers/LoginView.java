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

    /*
        Attempt login if button is clicked
     */
    @FXML
    protected void onLoginButtonClicked() {
        // Get username, hash password
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();

        // Attempt to login
        boolean success = handleUserSignin(cloud.Login(username, password));

        // If login was a success, then change scene
        if (success) {
            stage.setScene(
                    getScene("Main")
            );
        }
    }

    /*
       Change scene if signup button clicked
     */
    @FXML
    protected void onSignupButtonClicked() {
        stage.setScene(
                getScene("Signup")
        );
    }
}
