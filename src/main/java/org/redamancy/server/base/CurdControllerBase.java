package org.redamancy.server.base;

import lombok.AccessLevel;
import lombok.Getter;
import org.redamancy.server.exception.RestfulException;
import org.redamancy.server.style.restful.Restful;
import org.redamancy.server.style.restful.RestfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author zsh2401
 * @program redamancy
 * @create 2020-12-06 23:44
 **/
public class CurdControllerBase<ID extends Serializable, E extends IEntity<ID>> {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private ICurdService<ID, E> curdService;

    protected final RestfulResponse prevent() throws RestfulException {
        throw RestfulException.notAcceptable("operation prevented");
    }

    protected final RestfulResponse notFound() throws RestfulException {
        throw RestfulException.notFound("operation prevented");
    }

    @GetMapping
    @Restful
    public Object all() {
        return getCurdService().all();
    }

    @GetMapping("{id}")
    @Restful
    public Object getById(@PathVariable ID id) throws Exception {
        E entity = getCurdService().selectByID(id);
        if (entity == null) {
            return notFound();
        } else {
            return entity;
        }
    }

    @PutMapping
    @Restful
    public Object insert(@RequestBody E entity) throws Exception {
        return getCurdService().insert(entity);
    }

    @PostMapping
    @Restful
    public Object update(@RequestBody E entity) throws Exception {
        getCurdService().updateByID(entity);
        return 0;
    }

    @DeleteMapping("{id}")
    @Restful
    public Object delete(@PathVariable ID id) throws Exception {
        getCurdService().deleteByID(id);
        return RestfulResponse.ok();
    }
}
