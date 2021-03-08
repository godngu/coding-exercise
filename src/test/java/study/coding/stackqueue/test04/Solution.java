package study.coding.stackqueue.test04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Solution {

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
     * 3 => C(3) : D(2) A(2) B(1) -> C(3) D(2) A(2) B(1) :그대로
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

        for (int i = 0; i < printList.size(); i++) {
            for (int j = i+1; j < printList.size(); j++) {
                if (printList.get(j).isGreaterThan(printList.get(i))) {
                    printQueue.add(printQueue.poll());
                    break;
                }
            }
        }

        List<Print> collect = printQueue.stream().collect(Collectors.toList());

        for (int i = 0; i < collect.size(); i++) {
            if (location == collect.get(i).getLocation()) {
                answer = i;
                break;
            }
        }
        return answer + 1;
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
    }
}
