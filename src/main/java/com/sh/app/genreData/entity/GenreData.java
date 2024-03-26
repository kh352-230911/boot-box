//package com.sh.app.genreData.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "genre")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class GenreData {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genre_id_generator")
//    @SequenceGenerator(
//            name = "seq_genre_id_generator",
//            sequenceName = "seq_genre_id",
//            initialValue = 1,
//            allocationSize = 1
//    )
//    private Long id;
//    @Column(nullable = false)
//    private String genreList;
//
//}
