package ru.digitalhabits.homework3.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabits.homework3.dao.DepartmentDaoImpl;
import ru.digitalhabits.homework3.domain.Department;
import ru.digitalhabits.homework3.model.DepartmentFullResponse;
import ru.digitalhabits.homework3.model.DepartmentRequest;
import ru.digitalhabits.homework3.model.DepartmentShortResponse;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl
        implements DepartmentService {
    private DepartmentDaoImpl departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }


    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentShortResponse> findAll() {
        // TODO: NotImplemented: получение краткой информации о всех департаментах
        return departmentDao.findAll()
                .stream()
                .map(department -> new DepartmentShortResponse().setId(department.getId()).setName(department.getName()))
                .collect(Collectors.toList());

//        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public DepartmentFullResponse getById(int id) {
        // TODO: NotImplemented: получение подробной информации о департаменте и краткой информации о людях в нем.
        //  Если не найдено, отдавать 404:NotFound
        Department dep = departmentDao.findById(id);
        DepartmentFullResponse departmentFullResponse = new DepartmentFullResponse().setId(dep.getId()).setName(dep.getName()).setClosed(dep.isClosed()).setPersons(dep.getPersons());
        return departmentFullResponse;

//        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public int create(@Nonnull DepartmentRequest request) {
        // TODO: NotImplemented: создание нового департамента
        int id = Integer.parseInt(UUID.randomUUID().toString());
        Department dep = new Department().setId(id).setName(request.getName()).setClosed(false).setPersons(null);
        departmentDao.save(dep);
        return id;
//        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    @Transactional
    public DepartmentFullResponse update(int id, @Nonnull DepartmentRequest request) {

        // TODO: NotImplemented: обновление данных о департаменте. Если не найдено, отдавать 404:NotFound

        Department dep = departmentDao.findById(id);
        dep.setName(request.getName());
        departmentDao.update(dep);

        DepartmentFullResponse departmentFullResponse = new DepartmentFullResponse().setId(dep.getId()).setName(dep.getName()).setClosed(dep.isClosed()).setPersons(dep.getPersons());
        return departmentFullResponse;

//        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public void delete(int id) {
        // TODO: NotImplemented: удаление всех людей из департамента и удаление самого департамента.
        //  Если не найдено, то ничего не делать
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public void close(int id) {
        // TODO: NotImplemented: удаление всех людей из департамента и установка отметки на департаменте,
        //  что он закрыт для добавления новых людей. Если не найдено, отдавать 404:NotFound
        throw new NotImplementedException();
    }
}
