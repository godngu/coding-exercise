package study.coding.stackqueue.test02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Solution2 {

    @Test
    void test1() {
        int[] prices = {1, 2, 3, 2, 3};
        assertThat(solution(prices)[0]).isEqualTo(4);
        assertThat(solution(prices)[1]).isEqualTo(3);
        assertThat(solution(prices)[2]).isEqualTo(1);
        assertThat(solution(prices)[3]).isEqualTo(1);
        assertThat(solution(prices)[4]).isEqualTo(0);
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        return answer;
    }

}
