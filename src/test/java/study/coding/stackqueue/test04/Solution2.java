package study.coding.stackqueue.test04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Solution2 {

    @Test
    void test1() {
        int[] priorities = {2,1,3,2};
        int location = 2;
        assertThat(solution(priorities, location)).isEqualTo(1);
    }

    @Test
    void test2() {
        int[] priorities = {1,1,9,1,1,1};
        int location = 0;
        assertThat(solution(priorities, location)).isEqualTo(5);
    }

    /*
     * A(2) B(1) C(3) D(2)
     * 1 => A(2) : B(1) C(3) D(2) -> B(1) C(3) D(2) A(2)
     * 2 => B(1) : C(3) D(2) A(2) -> C(3) D(2) A(2) B(1)
     * 3 => C(3) : D(2) A(2) B(1) -> C(3) D(2) A(2) B(1) :그대로 (2, 3, 0, 1)
     */

    /**
     *
     * @param priorities
     *      1 ~ 9
     * @param location
     *      0, 1, 2...
     * @return
     *      1, 2, 3...
     */
    public int solution(int[] priorities, int location) {

        int answer = 0;

        Queue<Print> printQueue = toQueue(priorities);
        ArrayList<Print> printList = new ArrayList<>(printQueue);

        List<Print> sorted = printList.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        for (int i = 0; i < sorted.size(); i++) {
            if (location == sorted.get(i).getLocation()) {
                answer = i + 1;
                break;
            }
        }
        return answer;
    }

    private Queue<Print> toQueue(int[] priorities) {
        Queue<Print> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++)
            queue.add(new Print(i, priorities[i]));
        return queue;
    }

    static class Print implements Comparable<Print> {
        private final int priority;
        private final int location;

        public Print(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public int getLocation() {
            return location;
        }

        public boolean isGreaterThan(Print o) {
            return this.priority > o.getPriority();
        }

        @Override
        public int compareTo(Print o) {

            if (this.priority > o.getPriority())
                return 1;
            else
                return -1;

        }
    }
}
