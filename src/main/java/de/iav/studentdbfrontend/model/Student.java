package de.iav.httpclient.model;

public record Student(
        String matriculationNumber,
        String firstName,
        String lastName,
        String email,
        String courseOfStudies

) {
}
