package ru.digitalhabits.homework3.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabits.homework3.domain.Person;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonDaoImpl
        implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Person save(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Nullable
    @Override
    public Person findById(@Nonnull Integer id) {
        // TODO: NotImplemented
        return Optional.ofNullable(entityManager.find(Person.class, id)).orElseThrow(
                EntityNotFoundException::new);
    }

    @Nonnull
    @Override
    public List<Person> findAll() {
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM person p", Person.class);
        return query.getResultList();
    }

    @Nonnull
    @Override
    public Person update(@Nonnull Person person) {
        // TODO: NotImplemented
        entityManager.merge(person);
        return person;
    }

    @Nullable
    @Override
    public Person delete(@Nonnull Integer id) {
        // TODO: NotImplemented
        Person person = findById(id);
        if (person != null) {
            entityManager.remove(person);
        }
        return person;
    }
}
