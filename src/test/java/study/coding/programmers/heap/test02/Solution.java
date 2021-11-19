package study.coding.programmers.heap.test02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * 실패....
 */
public class Solution {

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
        Queue<Job> q = toQueue(jobs);

        int sum = 0;
        int processing = 0;
        int pastBegin = 0;
        boolean isFirst = true;
        int totalRunning = 0;

        while (!q.isEmpty()) {
            Job job = q.poll();

            int begin = job.begin;
            int running = job.running;

            if (isFirst) {
                begin = 0;
                totalRunning += begin;
                isFirst = false;
            }

            if (begin > totalRunning) {
                totalRunning = begin + running;
                processing = running;
            } else {
                totalRunning += running;
                processing += (running - begin + pastBegin);
            }

            pastBegin = job.begin;

            sum += processing;
        }

        return sum / jobs.length;
    }

    /**
     * 가장 처음 시작하는 job을 찾아야 한다.
     * running으로 정렬을 하게 되면 가장 처음 시작하는 Job이 중간에 위치하게 되어 문제가 된다.
     */
    private Queue<Job> toQueue(int[][] arr) {
        List<Job> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new Job(arr[i][0], arr[i][1]));
        }

        Comparator<Job> comparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.begin > o2.begin)
                    return 1;
                else if (o1.begin == o2.begin)
                    return 0;
                else
                    return -1;
            }
        };

        Queue<Job> q = new LinkedList<>();
        List<Job> sorted = list.stream().sorted(comparator).collect(Collectors.toList());
        for (Job job : sorted) {
            q.add(job);
        }
        Job firstJob = q.poll();

        PriorityQueue<Job> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {
            pq.add(q.poll());
        }

        q.add(firstJob);
        while (!pq.isEmpty()) {
            q.add(pq.poll());
        }


        return q;
    }


    static class Job implements Comparable<Job> {
        private final int begin;
        private final int running;

        public Job(int begin, int running) {
            this.begin = begin;
            this.running = running;
        }

        @Override
        public int compareTo(Job o) {

            if (this.running > o.running)
                return 1;
            else if (this.running == o.running)
                return 0;
            else
                return -1;
        }

        @Override
        public String toString() {
            return "Process{" +
                "begin=" + begin +
                ", running=" + running +
                '}';
        }
    }
}
