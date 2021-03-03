package study.coding.hash.test01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class SolutionBest {

    @Test
    void test1() {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        assertThat(solution(participant, completion)).isEqualTo("leo");
    }

    @Test
    void test2() {
        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion = {"josipa", "filipa", "marina", "nikola"};
        assertThat(solution(participant, completion)).isEqualTo("vinko");
    }

    @Test
    void test3() {
        String[] participant = {"mislav", "mislav", "stanko", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        assertThat(solution(participant, completion)).isEqualTo("mislav");
    }

    @Test
    void test4() {
        String[] participant = {"mislav", "mislav", "stanko", "ana"};
        String[] completion = {"mislav", "ana", "mislav"};
        assertThat(solution(participant, completion)).isEqualTo("stanko");
    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) - 1);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }
}
