package study.coding.programmers.hash.test04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * 테스트 1 〉	    통과 (7.05ms, 53MB)
 * 테스트 2 〉	    통과 (7.47ms, 52.6MB)
 * 테스트 3 〉	    통과 (8.01ms, 52.6MB)
 * 테스트 4 〉	    통과 (10.57ms, 52.6MB)
 * 테스트 5 〉	    통과 (5.40ms, 53MB)
 * 테스트 6 〉	    통과 (7.57ms, 54.4MB)
 * 테스트 7 〉	    통과 (9.66ms, 52MB)
 * 테스트 8 〉	    통과 (9.03ms, 52.5MB)
 * 테스트 9 〉	    통과 (7.33ms, 52.5MB)
 * 테스트 10 〉	통과 (5.64ms, 53MB)
 * 테스트 11 〉	통과 (5.04ms, 52.6MB)
 * 테스트 12 〉	통과 (8.34ms, 53.1MB)
 * 테스트 13 〉	통과 (9.96ms, 53.5MB)
 * 테스트 14 〉	통과 (6.33ms, 52.9MB)
 * 테스트 15 〉	통과 (4.86ms, 53.5MB)
 */
public class Solution {

    @Test
    void name() {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] result = solution(genres, plays);
        assertThat(result[0]).isEqualTo(4);
        assertThat(result[1]).isEqualTo(1);
        assertThat(result[2]).isEqualTo(3);
        assertThat(result[3]).isEqualTo(0);
    }

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        // genre<id, play>
        HashMap<String, HashMap<Integer, Integer>> genreMap = new HashMap<>();
        HashMap<String, Integer> sumOfGenre = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            HashMap<Integer, Integer> playMap = genreMap.getOrDefault(genre, new HashMap<>());
            playMap.put(i, play);

            genreMap.put(genre, playMap);

            // 장르별 합계
            if (sumOfGenre.containsKey(genre)) {
                sumOfGenre.replace(genre, sumOfGenre.get(genre) + play);
            } else {
                sumOfGenre.put(genre, play);
            }
        }

        // 속한 노래가 많이 재생된 장르 정렬
        List<String> sortedGenres = genrePlayDesc(sumOfGenre);
        List<Integer> bestPlay = new ArrayList<>();
        for (String sortedGenre : sortedGenres) {
            bestPlay.addAll(bestPlays(genreMap.get(sortedGenre)));
        }

        int[] arr = new int[bestPlay.size()];
        for (int i = 0; i < bestPlay.size(); i++) {
            arr[i] = bestPlay.get(i);
        }

        return arr;
    }

    private List<String> genrePlayDesc(HashMap<String, Integer> sumOfGenre) {
        return sumOfGenre.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Entry::getKey)
            .collect(Collectors.toList());

    }

    private List<Integer> bestPlays(HashMap<Integer, Integer> playMap) {
        return playMap.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Entry::getKey)
            .limit(2)
            .collect(Collectors.toList());
    }

}
