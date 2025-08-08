package com.kstd.project.user.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table
public class UserCoin extends BaseTimeEntity {

    @Id
    private Long id;

    private Long userId;

    private Integer coinAmount;
}
