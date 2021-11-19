package study.coding.programmers.hash.test04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * 테스트 1 〉	    통과 (6.11ms, 52.4MB)
 * 테스트 2 〉	    통과 (5.93ms, 53.9MB)
 * 테스트 3 〉	    통과 (8.55ms, 52MB)
 * 테스트 4 〉	    통과 (9.11ms, 52.3MB)
 * 테스트 5 〉	    통과 (13.23ms, 53.5MB)
 * 테스트 6 〉	    통과 (7.18ms, 52.9MB)
 * 테스트 7 〉	    통과 (9.95ms, 52.6MB)
 * 테스트 8 〉	    통과 (8.39ms, 52.8MB)
 * 테스트 9 〉	    통과 (6.13ms, 53MB)
 * 테스트 10 〉	통과 (7.27ms, 53MB)
 * 테스트 11 〉	통과 (6.11ms, 52.4MB)
 * 테스트 12 〉	통과 (11.48ms, 52.3MB)
 * 테스트 13 〉	통과 (14.75ms, 52.7MB)
 * 테스트 14 〉	통과 (7.36ms, 52.1MB)
 * 테스트 15 〉	통과 (9.81ms, 52.6MB)
 */
public class SolutionByInnerClass {

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

        HashMap<String, Album> albums = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            Album album = albums.getOrDefault(genre, new Album(genre));
            album.addSong(new Song(i, play));
            albums.put(genre, album);
        }

        List<Integer> ids = albums.values().stream()
            .sorted(Comparator.reverseOrder())
            .map(album -> {
                return album.getSongs().stream()
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .collect(Collectors.toList());
            })
            .flatMap(Collection::stream)
            .map(Song::getId)
            .collect(Collectors.toList());

        int[] best = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            best[i] = ids.get(i);
        }

        return best;
    }

    class Song implements Comparable<Song> {

        private int id;
        private int playCount;

        public Song(int id, int playCount) {
            this.id = id;
            this.playCount = playCount;
        }

        public int getId() {
            return id;
        }

        public int getPlayCount() {
            return playCount;
        }

        @Override
        public int compareTo(Song o) {
            if (this.playCount == o.getPlayCount()) {
                return (this.id < o.getId()) ? 1 : -1;
            } else if (this.playCount < o.getPlayCount()) {
                return -1;
            }
            return 1;
        }
    }

    class Album implements Comparable<Album> {

        private String genre;
        private List<Song> songs = new ArrayList<>();

        public Album(String genre) {
            this.genre = genre;
        }

        public List<Song> getSongs() {
            return songs;
        }

        public void addSong(Song song) {
            this.songs.add(song);
        }

        public int getTotalPlays() {
            return songs.stream()
                .mapToInt(Song::getPlayCount)
                .sum();
        }

        @Override
        public int compareTo(Album o) {
            if (getTotalPlays() == o.getTotalPlays()) {
                return 0;
            } else if (getTotalPlays() < o.getTotalPlays()) {
                return -1;
            }
            return 0;
        }
    }
}
