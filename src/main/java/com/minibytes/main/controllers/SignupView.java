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

/*
    Handles the buttons and labels for the SignupView
 */
public class SignupView extends BaseView {
    @FXML
    private TextField bioBox;

    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;

    public SignupView() { super(); }

    /*
        Change scene when loginbutton is clicked
     */
    @FXML
    protected void onLoginButtonClicked() {
        stage.setScene(
                getScene("Login")
        );
    }

    /*
        Change scene when signupbutton is clicked
     */
    @FXML
    protected void onSignupButtonClicked() {
        // Fetch username and password, hash password
        String username = usernameBox.getText();
        String password = Hashing.sha256()
                .hashString(passwordBox.getText(), StandardCharsets.UTF_8)
                .toString();
        String bio = bioBox.getText();

        // Attempt login
        boolean succcess = handleUserSignin(cloud.Signup(username, password, bio));

        // If login was a success, change scene
        if (succcess) {
            stage.setScene(
                    getScene("Main")
            );
        }
    }

}
