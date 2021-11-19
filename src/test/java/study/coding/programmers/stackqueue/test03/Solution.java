package study.coding.programmers.stackqueue.test03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class Solution {

    @Test
    void test1() {
        // given
        int[] progresses = {93,30,55};
        int[] speeds = {1,30,5};

        // when
        int[] result = solution(progresses, speeds);

        // then
        assertThat(result[0]).isEqualTo(2);
        assertThat(result[1]).isEqualTo(1);
    }

    @Test
    void test2() {
        // given
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};

        // when
        int[] result = solution(progresses, speeds);

        // then
        assertThat(result[0]).isEqualTo(1);
        assertThat(result[1]).isEqualTo(3);
        assertThat(result[2]).isEqualTo(2);
    }

    /**
     * remainPercent(남은 업무률) = 100% - 진척률 = 100 - 93 = 7
     * remainDay(남은 업무 기간) = 남은 업무률 / 개발속도 = 7 / 1 = 7일
     *      만약 70 / 30 = 2일이 되지만 10%가 남으므로 나머지가 있을 경우 +1일을 해준다.
     * 날짜를 꺼내서 다음 날짜와 비교하고 더 작다면 같이 배포가 가능하므로 스택에 담고 +1씩 해준다.
     */
    public int[] solution(int[] progresses, int[] speeds) {

        Stack<Integer> stack = new Stack<>();
        int temp = 0;

        for (int i = 0; i < progresses.length; i++) {
            int remainPercent = 100 - progresses[i];
            int remainDay = remainPercent / speeds[i];
            if (remainPercent % speeds[i] != 0) remainDay++;

            if (remainDay > temp) {
                temp = remainDay;
                stack.add(1);
                continue;
            }
            stack.add(stack.pop() + 1);
        }

        return new ArrayList<>(stack).stream().mapToInt(i -> i).toArray();
    }

}
