package org.redamancy.server.service.impl;

import org.redamancy.server.base.CachedCurdService;
import org.redamancy.server.entity.Account;
import org.redamancy.server.service.IAccountCurdService;
import org.springframework.stereotype.Component;

@Component
public class AccountCurdService
        extends CachedCurdService<Long, Account>
        implements IAccountCurdService {
}
