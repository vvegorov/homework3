package ru.digitalhabits.homework3.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import ru.digitalhabits.homework3.model.DepartmentShortResponse;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fullName")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "department")
    private DepartmentShortResponse department;

    public Person(String name, Integer age, DepartmentShortResponse department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }
}
