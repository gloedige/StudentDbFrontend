module de.iav.studentdbfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens de.iav.studentdbfrontend to javafx.fxml;
    opens de.iav.studentdbfrontend.model to com.fasterxml.jackson.databind;
    opens de.iav.studentdbfrontend.service to com.fasterxml.jackson.databind;

    exports de.iav.studentdbfrontend;
    exports de.iav.studentdbfrontend.model;
    exports de.iav.studentdbfrontend.service;
    exports de.iav.studentdbfrontend.dto;
    opens de.iav.studentdbfrontend.dto to com.fasterxml.jackson.databind;
    exports de.iav.studentdbfrontend.controller;
    opens de.iav.studentdbfrontend.controller to javafx.fxml;

}