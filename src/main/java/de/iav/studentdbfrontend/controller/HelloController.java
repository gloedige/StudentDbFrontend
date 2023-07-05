package de.iav.studentdbfrontend;

import de.iav.studentdbfrontend.service.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    private StudentService studentService;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(studentService.getAllStudents().toString());
    }
}