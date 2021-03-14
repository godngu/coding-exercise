package study.coding.heap.test01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class SolutionBest {

    @Test
    void test01() {
        // given
        int[] scoville = {1,2,3,9,10,12};
        int k = 7;

        // when
        int result = solution(scoville, k);

        // then
        assertThat(result).isEqualTo(2);
    }

    public int solution(int[] scoville, int K) {

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i : scoville) q.add(i);

        int count = 0;
        while (q.size() > 1 && q.peek() < K) {
            q.add(q.poll() + q.poll() * 2);
            count++;
        }

        return q.peek() < K ? -1 : count;
    }
}
