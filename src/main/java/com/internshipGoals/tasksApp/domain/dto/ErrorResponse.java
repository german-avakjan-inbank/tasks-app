package com.internshipGoals.tasksApp.domain.dto;

public record ErrorResponse(
    int status,
    String message,
    String details
) {

}
