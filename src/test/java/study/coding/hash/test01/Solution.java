package study.coding.hash.test01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 채점 결과
 * 정확성: 50.0
 * 효율성: 0.0
 * 합계: 50.0 / 100.0
 */
public class Solution {

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
        assertThat("vinko").isEqualTo(solution(participant, completion));
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

    private String solution(String[] participants, String[] completions) {

        List<Completion> completionList = toList(completions);

        for (String participant : participants) {
            if (!isCompleted(participant, completionList)) {
                return participant;
            }
        }

        return null;
    }

    private boolean isCompleted(String participant, List<Completion> completionList) {
        for (Completion completion : completionList) {
            if (participant.equals(completion.getName()) && !completion.isChecked()) {
                completion.doCheck();
                return true;
            }
        }
        return false;
    }

    private List<Completion> toList(String[] arr) {
        List<Completion> list = new ArrayList<>();
        for (String s : arr) {
            list.add(new Completion(s));
        }
        return list;
    }

    static class Completion {

        private boolean checked;
        private String name;

        public Completion(String name) {
            this.checked = false;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public boolean isChecked() {
            return checked;
        }

        public void doCheck() {
            this.checked = true;
        }
    }
}
