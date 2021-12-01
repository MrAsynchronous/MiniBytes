module com.minibytes.minibytes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires guava;

    opens com.minibytes.main to javafx.fxml;
    opens com.minibytes.main.controllers to javafx.fxml;

    exports com.minibytes.main;
    exports com.minibytes.main.controllers;
}