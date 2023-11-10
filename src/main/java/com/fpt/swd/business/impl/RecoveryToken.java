package com.fpt.swd.business.impl;

import com.fpt.swd.database.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "RecoveryToken", schema = "swd392_database")
public class RecoveryToken {
    @Id
    @GeneratedValue
    private long id;

    private String token;

    private long validUntilInEpoch;

    @OneToOne
    private User user;
}
