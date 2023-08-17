package io.github.soumikuxd.csv2topic.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
