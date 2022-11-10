package ru.digitalhabits.homework3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabits.homework3.dao.DepartmentDaoImpl;
import ru.digitalhabits.homework3.dao.PersonDaoImpl;
import ru.digitalhabits.homework3.domain.Department;
import ru.digitalhabits.homework3.domain.Person;
import ru.digitalhabits.homework3.model.DepartmentShortResponse;
import ru.digitalhabits.homework3.model.PersonFullResponse;
import ru.digitalhabits.homework3.model.PersonRequest;
import ru.digitalhabits.homework3.model.PersonShortResponse;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl
        implements PersonService {

    private final PersonDaoImpl personDao;
    private final DepartmentDaoImpl departmentDao;


    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<PersonShortResponse> findAll() {
        // TODO: NotImplemented: получение информации о всех людях во всех отделах
        return personDao.findAll()
                .stream()
                .map(person -> new PersonShortResponse().setId(person.getId()).setFullName(person.getName()))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public PersonFullResponse getById(int id) {
        // TODO: NotImplemented: получение информации о человеке. Если не найдено, отдавать 404:NotFound
        Person person = personDao.findById(id);
        DepartmentShortResponse departmentShortResponse = Optional.ofNullable(person.getDepartment()).map(p -> new DepartmentShortResponse().setId(p.getId()).setName(p.getName())).orElseGet(DepartmentShortResponse::new);

        return new PersonFullResponse().setId(id).setFullName(Objects.requireNonNull(person).getName()).setAge(person.getAge()).setDepartment(departmentShortResponse);
    }

    @Override
    @Transactional
    public int create(@Nonnull PersonRequest request) {
        // TODO: NotImplemented: создание новой записи о человеке
        String fullName = request.getFirstName() + request.getMiddleName() + request.getLastName();

        Person person = new Person().setName(fullName).setAge(request.getAge()).setDepartment(null);
        personDao.save(person);
        return person.getId();
    }

    @Nonnull
    @Override
    @Transactional
    public PersonFullResponse update(int id, @Nonnull PersonRequest request) {
        // TODO: NotImplemented: обновление информации о человеке. Если не найдено, отдавать 404:NotFound
        Person person = personDao.findById(id);
        String fullName = request.getFirstName() + " " + request.getMiddleName() + " " + request.getLastName();
        Objects.requireNonNull(person).setName(fullName);
        person.setAge(request.getAge());
        personDao.update(person);
        DepartmentShortResponse departmentShortResponse = Optional.ofNullable(person.getDepartment()).map(p -> new DepartmentShortResponse().setId(p.getId()).setName(p.getName())).orElseGet(DepartmentShortResponse::new);

        return new PersonFullResponse().setId(person.getId()).setFullName(fullName).setAge(request.getAge()).setDepartment(departmentShortResponse);
    }

    @Override
    @Transactional
    public void delete(int id) {
        // TODO: NotImplemented: удаление информации о человеке и удаление его из отдела. Если не найдено, ничего не делать
        Person person = personDao.findById(id);
        if (!person.getDepartment().getName().isEmpty()) {
            Department dep = departmentDao.findById(person.getDepartment().getId());
            dep.getPersons().remove(id);
            departmentDao.save(dep);
            personDao.delete(id);
        }
    }


    @Override
    @Transactional
    public void addPersonToDepartment(int departmentId, int personId) {
        // TODO: NotImplemented: добавление нового человека в департамент.
        //  Если не найден человек или департамент, отдавать 404:NotFound.
        //  Если департамент закрыт, то отдавать 409:Conflict
        Person person = personDao.findById(personId);
        Department dep = departmentDao.findById(departmentId);
        if (dep.isClosed()) {
            throw new IllegalStateException();
        }
        List<Person> personList = Optional.ofNullable(dep.getPersons()).orElse(new ArrayList<Person>());

        if (!personList.contains(person)) {
            personList.add(person);
            dep.setPersons(personList);
//            departmentDao.save(dep);
            person.setDepartment(dep);
            personDao.save(person);
        }
    }

    @Override
    @Transactional
    public void removePersonFromDepartment(int departmentId, int personId) {
        // TODO: NotImplemented: удаление человека из департамента.
        //  Если департамент не найден, отдавать 404:NotFound, если не найден человек в департаменте, то ничего не делать
        Person person = personDao.findById(personId);
        Department dep = departmentDao.findById(departmentId);
        List personList = Optional.ofNullable(Objects.requireNonNull(dep).getPersons()).orElse(Collections.EMPTY_LIST);
        if (!personList.isEmpty()) {
            personList.remove(person);
            dep.setPersons(personList);
        }
    }
}
