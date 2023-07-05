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
    private List<Student> students;


    public StudentService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Student getStudentByMatriculationNumber(String matriculationNumber){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/students/" + matriculationNumber))
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
                    .uri(URI.create("http://localhost:8080/api/students"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();


            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(this::mapToAddStudent)
                    .join();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private Student mapToAddStudent(String json) {
        try{
            return objectMapper.readValue(json, Student.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Failed to open Student!", e);
        }
    }

    public List<Student> getAllStudents(){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/students"))
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
