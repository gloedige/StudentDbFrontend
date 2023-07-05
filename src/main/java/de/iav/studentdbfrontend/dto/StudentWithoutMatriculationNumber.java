package de.iav.studentdbfrontend.dto;

public record StudentWithoutMatriculationNumber(
        String firstName,
        String lastName,
        String email,
        String courseOfStudies
) {
}
