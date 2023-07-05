package de.iav.studentdbfrontend.dto;

public record NewStudentDto(
        String firstName,
        String lastName,
        String email,
        String courseOfStudies
) {
}
