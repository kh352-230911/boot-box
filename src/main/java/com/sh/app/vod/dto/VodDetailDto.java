package com.sh.app.vod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VodDetailDto {
    private Long id;

    private String vodName;

    private String vodUrl;

    private String type;
}
