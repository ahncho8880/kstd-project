package com.kstd.project.coin.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table
public class Coin extends BaseTimeEntity {

    @Id
    private Long id;

    private Integer totalAmount;

	private Integer limitedAmount;

    private Integer remainingAmount;
}
