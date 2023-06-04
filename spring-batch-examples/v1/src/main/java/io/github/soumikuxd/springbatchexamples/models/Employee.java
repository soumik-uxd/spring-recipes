package io.github.soumikuxd.springbatchexamples.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
