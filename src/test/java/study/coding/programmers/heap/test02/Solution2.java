package study.coding.programmers.heap.test02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

/**
 * SJF 알고리즘 구현 문제이다.
 */
public class Solution2 {

    @Test
    void test01() {
        // given
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(9);
    }

    @Test
    void test02() {
        // given
        int[][] jobs = {{0, 10}, {2,10}, {9,10}, {15,2}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(14);
    }

    @Test
    void test03() {
        // given
        int[][] jobs = {{0, 10}, {2,12}, {9,19}, {15,17}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(25);
    }

    @Test
    void test04() {
        // given
        int[][] jobs = {{0, 1}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void test05() {
        // given
        int[][] jobs = {{1000, 1000}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(1000);
    }

    @Test
    void test06() {
        // given
        int[][] jobs = {{0,1}, {0,1}, {0,1}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void test07() {
        // given
        int[][] jobs = {{0,1}, {0,1}, {0,1}, {0,1}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void test08() {
        // given
        int[][] jobs = {{0,1}, {1000,1000}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(500);
    }

    @Test
    void test09() {
        // given
        int[][] jobs = {{100,100}, {1000,1000}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(550);
    }

    @Test
    void test10() {
        // given
        int[][] jobs = {{10,10}, {30,10}, {50,2}, {51,2}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void test11() {
        // given
        // 정렬 되어야 할 순서: {0,3}, {2,6}, {1,9}, {30,3}
        int[][] jobs = {{0,3}, {1,9}, {2,6}, {30,3}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(7);
    }

    @Test
    void test12() {
        // given
        int[][] jobs = {{2, 2}, {3,2}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void test13() {
        // given
        int[][] jobs = {{0, 2}, {1,2}};

        // when
        int result = solution(jobs);

        // then
        assertThat(result).isEqualTo(2);
    }

    public int solution(int[][] jobs) {

        LinkedList<Job> waiting = new LinkedList<>();
        PriorityQueue<Job> workingQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.workingTime));

        for (int[] job : jobs) {
            waiting.offer(new Job(job[0], job[1]));
        }

        Collections.sort(waiting, Comparator.comparingInt(o -> o.requestTime));

        int currentTime = waiting.peek().requestTime;
        int sum = 0;
        int processingTime = 0;
        int completedCount = 0;

        while (completedCount < jobs.length) {
            // 요청 시간이 현재시간 이하인 Job을 작업큐(workingQueue)로 옮긴다.
            while (!waiting.isEmpty() && waiting.peek().requestTime <= currentTime) {
                // pollFirst(): LinkedList의 첫번째 요소를 반환하면서 제거한다.
                workingQueue.offer(waiting.pollFirst());
            }

            if (!workingQueue.isEmpty()) {
                Job job = workingQueue.poll();
                currentTime += job.workingTime;
                processingTime = currentTime - job.requestTime;
                sum += processingTime;
                completedCount++;
            } else {
                currentTime++;
            }
        }

        return sum / completedCount;
    }

    class Job {
        private final int requestTime;
        private final int workingTime;

        public Job(int requestTime, int workingTime) {
            this.requestTime = requestTime;
            this.workingTime = workingTime;
        }
    }
}
