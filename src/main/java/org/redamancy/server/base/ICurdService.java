package org.redamancy.server.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

public interface ICurdService<ID extends Serializable, E extends IEntity<ID>> {

    /**
     * Get all entity.
     *
     * @return get all saved entity.
     * @throws Exception
     */
    @NotNull
    List<E> all();

    /**
     * get entity by ID.
     *
     * @param id
     * @return null if not found
     * @throws Exception there are other error(s).
     */
    @Nullable
    E selectByID(ID id);

    /**
     * @param target
     * @throws Exception can not update.
     */
    void updateByID(E target) throws Exception;

    /**
     * Insert an new entity to the database.
     *
     * @param target
     * @return saved entity
     * @throws Exception
     */
    @NotNull
    E insert(E target) throws Exception;

    /**
     * delete an exist entity by id.
     *
     * @param id
     * @throws Exception
     */
    @NotNull
    void deleteByID(ID id) throws Exception;
}
