package study.coding.programmers.heap.test01;

import java.util.*;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 채점 결과
 * 정확성: 76.2
 * 효율성: 0.0
 * 합계: 76.2 / 100.0
 */
public class Solution {

    @Test
    void test01() {
        int[] scoville = {1,2,3,9,10,12};
        int k = 7;
        Assertions.assertThat(solution(scoville, k)).isEqualTo(2);
    }

    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> queue = toSortedQueue(scoville);

        while (queue.size() > 1) {
            answer++;

            queue.add(queue.poll() + (queue.poll() * 2));

            // PriorityQueue 특징에 의해 값이 새로 삽입된 후 peek(min) 값을 K와 비교한다.
            if (queue.peek() >= K) {
                return answer;
            } else {
                queue = sort(queue);
            }
        }
        return -1;
    }

    private PriorityQueue<Integer> sort(PriorityQueue<Integer> source) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        List<Integer> list = source.stream().sorted().collect(Collectors.toList());
        for (Integer integer : list) {
            priorityQueue.add(integer);
        }
        return priorityQueue;
    }

    private PriorityQueue<Integer> toSortedQueue(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        List<Integer> list = Arrays.stream(arr).sorted().boxed().collect(Collectors.toList());
        for (Integer integer : list)
            queue.add(integer);
        return queue;
    }

}
