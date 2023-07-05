package de.iav.studentdbfrontend.controller;

import de.iav.studentdbfrontend.service.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    private final StudentService studentService = new StudentService();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(studentService.getAllStudents().toString());
    }
}