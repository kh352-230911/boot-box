package com.sh.app.util;

import java.util.HashMap;
import java.util.Map;

public class GenreNormalization {
    private static final Map<String, String> genreMap = new HashMap<>();

    static {
        genreMap.put("코메디", "코미디");
        genreMap.put("어드벤처", "모험");
        genreMap.put("뮤지컬", "음악");
        genreMap.put("멜로/로맨스", "로맨스");
        // 필요에 따라 추가 매핑 정보를 여기에 추가할 수 있습니다.
    }

    public static String normalizeGenreName(String genreName) {
        return genreMap.getOrDefault(genreName, genreName);
    }
}
