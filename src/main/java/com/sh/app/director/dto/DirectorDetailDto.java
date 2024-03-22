package com.sh.app.director.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorDetailDto {
    private Long id;

    private Long directorId;

    private String directorName;
}
