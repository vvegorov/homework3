package ru.digitalhabits.homework3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabits.homework3.dao.DepartmentDaoImpl;
import ru.digitalhabits.homework3.domain.Department;
import ru.digitalhabits.homework3.domain.Person;
import ru.digitalhabits.homework3.model.DepartmentFullResponse;
import ru.digitalhabits.homework3.model.DepartmentRequest;
import ru.digitalhabits.homework3.model.DepartmentShortResponse;
import ru.digitalhabits.homework3.model.PersonShortResponse;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl
        implements DepartmentService {
    private final DepartmentDaoImpl departmentDao;

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentShortResponse> findAll() {
        // TODO: NotImplemented: получение краткой информации о всех департаментах
        return departmentDao.findAll()
                .stream()
                .map(department -> new DepartmentShortResponse().setId(department.getId()).setName(department.getName()))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public DepartmentFullResponse getById(int id) {
        // TODO: NotImplemented: получение подробной информации о департаменте и краткой информации о людях в нем.
        //  Если не найдено, отдавать 404:NotFound
        Department dep = departmentDao.findById(id);

        List<PersonShortResponse> personShortResponseList = Optional.ofNullable(dep.getPersons())
                .map(k -> k.stream().map(h -> new PersonShortResponse().setId(h.getId()).setFullName(h.getName())).collect(Collectors.toList()))
                .orElseGet(ArrayList<PersonShortResponse>::new);
//
        return new DepartmentFullResponse().setId(dep.getId()).setName(dep.getName()).setClosed(dep.isClosed()).setPersons(personShortResponseList);
    }

    @Override
    @Transactional
    public int create(@Nonnull DepartmentRequest request) {
        // TODO: NotImplemented: создание нового департамента
        Department dep = new Department().setName(request.getName()).setClosed(false).setPersons(null);
        departmentDao.save(dep);
        return dep.getId();
    }

    @Nonnull
    @Override
    @Transactional
    public DepartmentFullResponse update(int id, @Nonnull DepartmentRequest request) {

        // TODO: NotImplemented: обновление данных о департаменте. Если не найдено, отдавать 404:NotFound

        Department dep = departmentDao.findById(id);
        dep.setName(request.getName());
        departmentDao.update(dep);

        List<PersonShortResponse> personShortResponseList = Optional.ofNullable(dep.getPersons())
                .map(k -> k.stream().map(h -> new PersonShortResponse().setId(h.getId()).setFullName(h.getName())).collect(Collectors.toList()))
                .orElseGet(ArrayList<PersonShortResponse>::new);

        DepartmentFullResponse departmentFullResponse = new DepartmentFullResponse().setId(dep.getId()).setName(dep.getName()).setClosed(dep.isClosed()).setPersons(personShortResponseList);
        return departmentFullResponse;
    }

    @Override
    @Transactional
    public void delete(int id) {
        // TODO: NotImplemented: удаление всех людей из департамента и удаление самого департамента.
        //  Если не найдено, то ничего не делать

        Department dep = departmentDao.findById(id);
        List<Person> personList = Optional.ofNullable(dep.getPersons()).orElse(Collections.EMPTY_LIST);
        if (!personList.isEmpty()) {
            dep.setPersons(null);
            departmentDao.save(dep);
            departmentDao.delete(id);
        }
    }

    @Override
    @Transactional
    public void close(int id) {
        // TODO: NotImplemented: удаление всех людей из департамента и установка отметки на департаменте,
        //  что он закрыт для добавления новых людей. Если не найдено, отдавать 404:NotFound
        Department dep = departmentDao.findById(id);
        List<Person> personList = Optional.ofNullable(dep.getPersons()).orElse(Collections.EMPTY_LIST);
        if (!personList.isEmpty()) {
            dep.setPersons(null);
            dep.setClosed(true);
            departmentDao.save(dep);
        }
    }
}
