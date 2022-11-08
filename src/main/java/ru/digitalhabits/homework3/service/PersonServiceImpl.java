package ru.digitalhabits.homework3.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
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
import java.util.List;
import java.util.UUID;
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
//        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public PersonFullResponse getById(int id) {
        // TODO: NotImplemented: получение информации о человеке. Если не найдено, отдавать 404:NotFound
        Person person = personDao.findById(id);
        PersonFullResponse personFullResponse = new PersonFullResponse().setId(id).setFullName(person.getName()).setAge(person.getAge()).setDepartment(person.getDepartment());
        return personFullResponse;
//        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public int create(@Nonnull PersonRequest request) {
        // TODO: NotImplemented: создание новой записи о человеке
        int id = Integer.parseInt(UUID.randomUUID().toString());
        String fullName = request.getFirstName() + request.getMiddleName() + request.getLastName();
        Department department = departmentDao.findAll()
                .stream()
                .filter(d -> d.getPersons().size() > 2)
                .findFirst()
                .get();

        DepartmentShortResponse departmentShortResponse = new DepartmentShortResponse().setId(department.getId()).setName(department.getName());

        Person person = new Person().setId(id).setName(fullName).setAge(request.getAge()).setDepartment(departmentShortResponse);
        personDao.save(person);
        return id;
//        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    @Transactional
    public PersonFullResponse update(int id, @Nonnull PersonRequest request) {
        // TODO: NotImplemented: обновление информации о человеке. Если не найдено, отдавать 404:NotFound
        Person person = personDao.findById(id);
        String fullName = request.getFirstName() + request.getMiddleName() + request.getLastName();
        person.setName(fullName);
        person.setAge(request.getAge());
        personDao.update(person);

        PersonFullResponse personFullResponse = new PersonFullResponse().setId(person.getId()).setFullName(fullName).setAge(request.getAge()).setDepartment(person.getDepartment());
        return personFullResponse;

//        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public void delete(int id) {
        // TODO: NotImplemented: удаление информации о человеке и удаление его из отдела. Если не найдено, ничего не делать
        throw new NotImplementedException();
    }


    @Override
    @Transactional
    public void addPersonToDepartment(int departmentId, int personId) {
        // TODO: NotImplemented: добавление нового человека в департамент.
        //  Если не найден человек или департамент, отдавать 404:NotFound.
        //  Если департамент закрыт, то отдавать 409:Conflict
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public void removePersonFromDepartment(int departmentId, int personId) {
        // TODO: NotImplemented: удаление человека из департамента.
        //  Если департамент не найден, отдавать 404:NotFound, если не найден человек в департаменте, то ничего не делать
        throw new NotImplementedException();
    }
}
