package de.iav.studentdbfrontend.controller;

import de.iav.studentdbfrontend.model.Student;
import de.iav.studentdbfrontend.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField matriculationNumber;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> coursesComboBox = new ComboBox<>();
    private final StudentService studentService = new StudentService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        coursesComboBox.getItems().add("Math");
        coursesComboBox.getItems().add("Mechanical Engineer");
        coursesComboBox.getItems().add("Biology");
        coursesComboBox.getItems().add("Physics");
        //System.out.println("element 0:" + isActiveChoiceBox.getItems().get(0));
    }


    protected void registerStudentToDb(Student student){
        studentService.addStudent(student);
    }
    @FXML
    protected void clearForm(){
        matriculationNumber.clear();
        firstName.clear();
        lastName.clear();
        email.clear();
        coursesComboBox.setValue("");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(studentService.getAllStudents().toString());
    }
    @FXML
    public void registerStudentToDb(ActionEvent event) {
        Student student = new Student(matriculationNumber.getText(), firstName.getText(), lastName.getText(), email.getText(), coursesComboBox.getValue());
        studentService.addStudent(student);

    }
}