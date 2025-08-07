package com.kstd.project.user.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class User extends BaseTimeEntity {

    @Id
    private Long id;

    private UUID employeeId;

    private int coinAmount;
}
