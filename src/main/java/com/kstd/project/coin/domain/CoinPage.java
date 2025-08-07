package com.kstd.project.coin.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class CoinPage extends BaseTimeEntity {

    @Id
    private Long id;

    private Long coinId;

    private CoinType page;
}
