package study.coding.stackqueue.test04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class Solution {

    @Test
    void test1() {
        int[] priorities = {2,1,3,2};
        assertThat(solution(priorities, 2)).isEqualTo(1);
    }

    @Test
    void test2() {
        int[] priorities = {1,1,9,1,1,1};
        assertThat(solution(priorities, 0)).isEqualTo(5);
        assertThat(solution(priorities, 4)).isEqualTo(3);
    }

    @Test
    void test3() {
        int[] priorities = {3,2,4,1};
        assertThat(solution(priorities, 1)).isEqualTo(3);
    }

    @Test
    void test4() {
        int[] priorities = {1,1,1,1,1,1,1};
        assertThat(solution(priorities, 0)).isEqualTo(1);
        assertThat(solution(priorities, 3)).isEqualTo(4);
    }

    @Test
    void test5() {
        int[] priorities = {5,4,3,2,1};
        assertThat(solution(priorities, 0)).isEqualTo(1);
        assertThat(solution(priorities, 2)).isEqualTo(3);
    }

    /**
     * [1]  [2]  [3]  [4]  [5]
     * ------------------------
     * 2(0) 1(1) 2(2) 1(3) 2(4)
     * 2(0) 2(2) 1(3) 2(4) 1(1)
     * 2(0) 2(2) 2(4) 1(1) 1(3)
     */
    @Test
    void test6() {
        int[] priorities = {2,1,2,1,2};
        assertThat(solution(priorities, 0)).isEqualTo(1);
        assertThat(solution(priorities, 1)).isEqualTo(4);
        assertThat(solution(priorities, 3)).isEqualTo(5);
    }

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

        ArrayList<Print> result = new ArrayList<>();
        Queue<Print> printQueue = toQueue(priorities);

        boolean isBiggest;// 2 1 3 2

        while (true) {
            ArrayList<Print> printList = new ArrayList<>(printQueue);

            for (int i = 0; i < printList.size(); i++) {
                isBiggest = true;
                Print current = printList.get(i);

                for (int j = i+1; j < printList.size(); j++) {
                    Print next = printList.get(j);

                    if (next.isGreaterThan(current)) {
                        printQueue.add(printQueue.poll());
                        isBiggest = false;
                        break;
                    }
                }
                if (isBiggest)
                    break;
            }
            result.add(printQueue.poll());

            if (result.size() == priorities.length) break;
        }

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getLocation() == location) {
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

    static class Print {
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
        public String toString() {
            return "Print{" +
                "priority=" + priority +
                ", location=" + location +
                '}';
        }
    }
}
