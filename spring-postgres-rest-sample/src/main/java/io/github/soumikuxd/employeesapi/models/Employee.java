package io.github.soumikuxd.employeesapi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@ApiModel(description = "Employee details")
@Entity
@Table(name = "employees")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id of the employee")
    private long id;

    @ApiModelProperty(notes = "First name of the employee")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "Last name of the employee")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "Email address of the employee")
    @Column(name = "email")
    private String email;
}
