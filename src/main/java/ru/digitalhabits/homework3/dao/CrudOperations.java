package ru.digitalhabits.homework3.dao;

import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@NoRepositoryBean
public interface CrudOperations<T, ID> {

    @Nullable
    T findById(@Nonnull ID id);

    @Nonnull
    List<T> findAll();

    @Nonnull
    T update(@Nonnull T entity);

    @Nullable
    T delete(@Nonnull ID id);
}
