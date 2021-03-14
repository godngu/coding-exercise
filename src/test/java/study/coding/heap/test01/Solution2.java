package study.coding.heap.test01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

/**
 * 채점 결과
 * 정확성: 76.2
 * 효율성: 23.8
 * 합계: 100.0 / 100.0
 */
public class Solution2 {

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
        int answer = 0;

        PriorityQueue<Integer> q = toQueue(scoville);

        while (q.size() > 1) {
            answer++;

            int mixed = q.poll() + (q.poll() * 2);

            if (q.isEmpty()) {
                if (mixed < K) return -1;
                else return answer;
            }

            // 섞은 음식의 스코빌 지수가 K보다 크고, 남은 음식중 스코빌 지수가 가장 낮은 값이 K보다 클 때
            if (mixed >= K && q.peek() >= K) return answer;
            else q.add(mixed);
        }

        return -1;
    }

    private PriorityQueue<Integer> toQueue(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Arrays.stream(arr).sorted().forEach(i -> queue.add(i));
        return queue;
    }
}
