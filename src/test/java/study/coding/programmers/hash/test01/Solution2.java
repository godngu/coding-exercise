package study.coding.programmers.hash.test01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

/**
 * 채점 결과
 * 정확성: 50.0
 * 효율성: 50.0
 * 합계: 100.0 / 100.0
 */
public class Solution2 {

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

    private String solution(String[] participant, String[] completion) {

        Map<String, Integer> participantMap = toMap(participant);

        for (String c : completion) {
            if (participantMap.containsKey(c)) {
                participantMap.replace(c, participantMap.get(c) - 1);
            }
        }

        for (Entry<String, Integer> entry : participantMap.entrySet()) {
            if (entry.getValue() > 0) {
                return entry.getKey();
            }
        }
        return "";
    }

    private Map<String, Integer> toMap(String[] participants) {
        Map<String, Integer> map = new HashMap<>();
        for (String participant : participants) {
            if (map.containsKey(participant)) {
                map.put(participant, map.get(participant) + 1);
                continue;
            }
            map.put(participant, 1);
        }
        return map;
    }
}
