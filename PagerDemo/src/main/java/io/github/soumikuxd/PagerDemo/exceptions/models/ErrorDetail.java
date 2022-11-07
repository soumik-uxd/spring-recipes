package io.github.soumikuxd.PagerDemo.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetail {
    private Date date;
    private String errorCode;
    private String message;
    private String details;
}
