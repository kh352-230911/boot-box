package com.sh.app.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDetailDto {
    private Long id;

    private String type;

    private String imageUrl;

    private String name;

    private Long price;

    private String description;

    private Integer expirationPeriod;
}
