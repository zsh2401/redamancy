package org.redamancy.server.base;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:47
 **/
public abstract class CurdServiceBase<ID extends Serializable, E extends IEntity<ID>> implements ICurdService<ID, E> {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private JpaRepository<E, ID> repository;

    @NotNull
    @Override
    public List<E> all() {
        return getRepository().findAll();
    }

    @Nullable
    @Override
    public E selectByID(ID id) {
        final Optional<E> result = getRepository().findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public void updateByID(E target) throws Exception {
        final JpaRepository<E, ID> repository = getRepository();
        if (repository.existsById(target.getId())) {
            repository.save(target);
        } else {
            throw new NoSuchElementException();
        }
    }

    @NotNull
    @Override
    public E insert(E target) throws Exception {
        return getRepository().save(target);
    }

    @NotNull
    @Override
    public void deleteByID(ID id) throws Exception {
        getRepository().deleteById(id);
    }
}
