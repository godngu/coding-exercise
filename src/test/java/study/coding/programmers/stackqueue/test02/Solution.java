package study.coding.programmers.stackqueue.test02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * 이 문제는 삽질을 굉장히 많이 했다... 문제의 의도를 제대로 파악 했어야 한다.
 * [실패 했던 이유]
 *      두 번째 루프를 끝까지 돌렸기 때문이다.
 * [설명]
 *      "가격이 떨어지지 않은 기간은 몇 초인지"
 *      즉 가격이 떨어진 시점에 두 번째 루프를 중단하고 그 시점까지의 초를 셋어야 했다.
 */
public class Solution {

    @Test
    void test1() {
        int[] prices = {1, 2, 3, 2, 3};
        int[] result = solution(prices);
        assertThat(result[0]).isEqualTo(4);
        assertThat(result[1]).isEqualTo(3);
        assertThat(result[2]).isEqualTo(1);
        assertThat(result[3]).isEqualTo(1);
        assertThat(result[4]).isEqualTo(0);
    }

    @Test
    void test2() {
        int[] prices = {1, 2, 3, 2, 3, 1};
        int[] result = solution(prices);
        assertThat(result[0]).isEqualTo(5);
        assertThat(result[1]).isEqualTo(4);
        assertThat(result[2]).isEqualTo(1);
        assertThat(result[3]).isEqualTo(2);
        assertThat(result[4]).isEqualTo(1);
        assertThat(result[5]).isEqualTo(0);
    }

    @Test
    void test3() {
        int[] prices = {5,8,6,2,4,1};
        int[] result = solution(prices);
        assertThat(result[0]).isEqualTo(3);
        assertThat(result[1]).isEqualTo(1);
        assertThat(result[2]).isEqualTo(1);
        assertThat(result[3]).isEqualTo(2);
        assertThat(result[4]).isEqualTo(1);
        assertThat(result[5]).isEqualTo(0);
    }

    public int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[length];

        for (int i = 0; i < length; i++) {

            int seconds = 0;
            for (int j = i+1; j < length; j++) {
                seconds++;
                if (prices[i] > prices[j]) break;
            }
            answer[i] = seconds;
        }

        return answer;
    }
}
