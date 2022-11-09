package ru.digitalhabits.homework3.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabits.homework3.domain.Department;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentDaoImpl
        implements DepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Department save(Department dep) {
        entityManager.persist(dep);
        return dep;
    }

    @Nullable
    @Override
    public Department findById(@Nonnull Integer id) {
        // TODO: NotImplemented

        return Optional.ofNullable(entityManager.find(Department.class, id)).orElseThrow(
                EntityNotFoundException::new);
//        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    public List<Department> findAll() {
        // TODO: NotImplemented
        TypedQuery<Department> query = entityManager.createQuery("SELECT t FROM department t", Department.class);
        return query.getResultList();
//        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    public Department update(@Nonnull Department department) {
        // TODO: NotImplemented
        entityManager.merge(department);
        return department;
//        throw new NotImplementedException();
    }

    @Nullable
    @Override
    public Department delete(@Nonnull Integer id) {
        // TODO: NotImplemented
        Department department = findById(id);
        if (department != null) {
            entityManager.remove(department);
        }

        return department;
//        throw new NotImplementedException();
    }
}
