package com.fpt.swd.business.impl;

import com.fpt.swd.database.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryTokenRepository extends CrudRepository<RecoveryToken, Long> {
    RecoveryToken findByToken(String token);

    void deleteByToken(String token);

    void deleteByUser(User user);
}