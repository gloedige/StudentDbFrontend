package de.iav.studentdbfrontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.iav.studentdbfrontend.model.Student;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class StudentService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String STUDENT_BASE_URL = "http://localhost:8080/api/students";
    private List<Student> students;


    public StudentService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Student getStudentByMatriculationNumber(String matriculationNumber){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(STUDENT_BASE_URL + "/" + matriculationNumber))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::mapToStudent) // .thenApply(responseBody -> mapToStudent(responseBody))
                .join();
    }
    private Student mapToStudent(String json) {
        try{
            return objectMapper.readValue(json, Student.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Failed to open student!", e);
        }
    }

    public Student addStudent(Student student) {
        try {
            String requestBody = objectMapper.writeValueAsString(student);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(STUDENT_BASE_URL))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();


            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(this::mapToStudent)
                    .join();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudents(){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(STUDENT_BASE_URL))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::mapToStudentList) // .thenApply(responseBody -> mapToStudent(responseBody))
                .join();
    }
    private List<Student> mapToStudentList(String json) {
        try{
            return objectMapper.readValue(json, new TypeReference<List<Student>>() {
            });
        }catch (JsonProcessingException e){
            throw new RuntimeException("Failed to open List of students!", e);
        }
    }
}
