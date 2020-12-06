package org.redamancy.server.base;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.List;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:53
 **/
public abstract class CachedCurdService<ID extends Serializable, E extends IEntity<ID>>
        extends CurdServiceBase<ID, E> {
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private RedisTemplate redisTemplate;

    @Nullable
    @Override
    public E selectByID(ID id) {
        E result = get(id);
        if (result == null) {
            result = super.selectByID(id);
            put(result);
        }
        return result;
    }

    @NotNull
    @Override
    public E insert(E target) throws Exception {
        E e = super.insert(target);
        put(e);
        return e;
    }

    @Override
    public void updateByID(E target) throws Exception {
        super.updateByID(target);
        put(target);
    }

    @NotNull
    @Override
    public void deleteByID(ID id) throws Exception {
        super.deleteByID(id);
        delete(id);
    }

    protected E delete(ID id) {
        final RedisTemplate template = getRedisTemplate();
        final String key = keyFor(id);
        E deleted = null;
        if (template.hasKey(key)) {
            try {
                deleted = (E) template.opsForValue().get(key);
            } catch (Exception e) {
            }
            template.delete(deleted);
        }
        return deleted;
    }

    @Nullable
    protected E put(E e) {
        final RedisTemplate template = getRedisTemplate();
        final String key = keyFor(e.getId());
        final ValueOperations ops = template.opsForValue();
        E result = null;
        if (template.hasKey(key)) {
            try {
                result = (E) ops.get(key);
            } catch (Exception ignored) {
            }
        }
        ops.set(key, e);
        return result;
    }

    @Nullable
    protected E get(ID id) {
        final RedisTemplate template = getRedisTemplate();
        final String key = keyFor(id);
        final ValueOperations ops = template.opsForValue();
        try {
            return (E) ops.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    protected String keyFor(ID id) {
        return "NightVoyager#" + this.getClass().getName() + "#" + id;
    }
}
