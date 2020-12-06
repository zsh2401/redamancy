package org.redamancy.server.entity;

import lombok.Data;
import org.redamancy.server.base.IEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Account implements IEntity<Long> {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;
}
