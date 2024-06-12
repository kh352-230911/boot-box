package com.sh.app.location.dto;


import lombok.Data;

//0604 예매 테이블에서 id와 지역명만 간단히 가져오는 dto(기존 locationDto 대체)
@Data
public class LocationResDto {
    private Long id;
    private String location_name;
}
