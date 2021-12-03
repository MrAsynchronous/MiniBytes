/*
    Brandon Wilcox
    Dec 1 2021
 */

package com.minibytes.main.controllers;

import javafx.fxml.FXML;

public class LandingView extends BaseView {
    public LandingView() {
        super();
    }

    @FXML
    protected void onLoginButtonClicked() {
        stage.setScene(getScene("Login"));
    }

    @FXML
    protected void onSignupButtonClicked() {
        stage.setScene(getScene("Signup"));
    }

}
