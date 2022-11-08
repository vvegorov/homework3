package ru.digitalhabits.homework3.dao;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.digitalhabits.homework3.domain.Department;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartmentDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentDao departmentDao;

    private int id;

    @Before("")
    public void setUp(){
        // given
        id = Integer.parseInt(UUID.randomUUID().toString());
        Department dep = new Department().setId(id).setName("test1").setClosed(false).setPersons(null);

        entityManager.persist(dep);
    }

    @Test
    void findById() {
        // TODO: NotImplemented

        Department dep = departmentDao.findById(id);

        // then
        assertThat(dep.getName()).isEqualTo("test1");
    }

    @Test
    void findAll() {

        List<Department> dep = departmentDao.findAll();

        // then
        assertThat(dep).hasSize(1);
        // TODO: NotImplemented
    }

    @Test
    void update() {
        // TODO: NotImplemented
    }

    @Test
    void delete() {
        // TODO: NotImplemented
    }
}