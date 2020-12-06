package org.redamancy.server.db;

import org.redamancy.server.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepo extends JpaRepository<Account, Long> {
}
