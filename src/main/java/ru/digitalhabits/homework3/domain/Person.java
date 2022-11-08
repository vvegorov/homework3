package ru.digitalhabits.homework3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.digitalhabits.homework3.model.DepartmentShortResponse;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "person")
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fullName")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "department")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DepartmentShortResponse department;

    public Person(String name, Integer age, DepartmentShortResponse department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }
}
