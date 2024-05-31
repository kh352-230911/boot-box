//package com.sh.app.api.service;
//
//import com.sh.app.genre.dto.GenreDto;
//import com.sh.app.genre.dto.GenreResponse;
//import com.sh.app.genre.entity.Genre;
//import com.sh.app.genre.repository.GenreRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Service
//@Transactional
//@Slf4j
//public class GenreService {
//    private static final String GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list";
//
//    @Value("${api_tmdb_key}")
//    private String tmdbApiKey;
//
//    private RestTemplate restTemplate = new RestTemplate();
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private GenreRepository genreRepository;
//
//    public void fetchAndStoreGenre() {
//        fetchAndStoreGenres();
//    }
//
//    private void fetchAndStoreGenres() {
//        genreRepository.deleteAll();
//        String url = UriComponentsBuilder
//                .fromHttpUrl(GENRE_URL)
//                .queryParam("api_key", tmdbApiKey)
//                .queryParam("language", "ko-KR")
//                .toUriString();
//
//        GenreResponse genreResponse = restTemplate.getForObject(url, GenreResponse.class);
////        log.debug("genreResponse = {}", genreResponse);
//        if (genreResponse != null) {
//            try {
//                for (GenreDto genreDto : genreResponse.getGenreDtos()) {
//                    Genre genre = convertToGenre(genreDto);
//                    genreRepository.save(genre);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private Genre convertToGenre(GenreDto genreDto) {
//        return modelMapper.map(genreDto, Genre.class);
//    }
//}
