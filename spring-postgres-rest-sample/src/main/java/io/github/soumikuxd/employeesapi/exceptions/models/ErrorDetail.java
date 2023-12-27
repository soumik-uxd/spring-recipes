package io.github.soumikuxd.employeesapi.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter @Setter
public class ErrorDetail {
    private Date date;
    private String message;
    private String details;
}
