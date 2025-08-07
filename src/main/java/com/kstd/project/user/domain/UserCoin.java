package com.kstd.project.user.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class UserCoin extends BaseTimeEntity {

    @Id
    private Long id;

    private Long userId;

    private Long coinPageId;
}
