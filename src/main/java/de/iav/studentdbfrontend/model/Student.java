package de.iav.studentdbfrontend.model;

public record Student(
        String matriculationNumber,
        String firstName,
        String lastName,
        String email,
        String courseOfStudies

) {
}
