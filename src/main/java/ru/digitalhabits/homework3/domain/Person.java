package ru.digitalhabits.homework3.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    //    @Column(name = "department")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne
    @JoinColumn(name = "department_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Department department;

    public Person(String name, Integer age, Department department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }
}
