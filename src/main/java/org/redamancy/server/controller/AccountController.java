package org.redamancy.server.controller;

import org.jetbrains.annotations.NotNull;
import org.redamancy.server.base.CachedCurdService;
import org.redamancy.server.base.CurdControllerBase;
import org.redamancy.server.base.IEntity;
import org.redamancy.server.entity.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:34
 **/
@RestController
@RequestMapping("account")
public class AccountController extends CurdControllerBase<Long, Account> {
    @Override
    public Object delete(Long aLong) throws Exception {
        return prevent();
    }
}