package com.kstd.project.user.domain;

import com.kstd.project.common.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table
public class User extends BaseTimeEntity {

    @Id
    private Long id;

	@Builder.Default
    private Integer coinAmount = 0;
}
