package com.sh.app.genre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDetailDto {
    private Long id;

    private Long genreId;

    private String genreName;
}
