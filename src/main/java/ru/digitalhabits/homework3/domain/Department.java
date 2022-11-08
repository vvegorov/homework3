package ru.digitalhabits.homework3.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import ru.digitalhabits.homework3.model.PersonShortResponse;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "closed")
    private boolean closed;

    @Column(name = "persons")
    private List<PersonShortResponse> persons;
}