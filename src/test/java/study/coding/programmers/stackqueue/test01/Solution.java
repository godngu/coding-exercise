package study.coding.programmers.stackqueue.test01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class Solution {

    @Test
    void test01() {
        int bridgeLength = 2;
        int weight = 10;
        int[] truckWeights = {7};
        assertThat(solution(bridgeLength, weight, truckWeights)).isEqualTo(3);
    }

    @Test
    void test02() {
        int bridgeLength = 2;
        int weight = 10;
        int[] truckWeights = {7, 4};
        assertThat(solution(bridgeLength, weight, truckWeights)).isEqualTo(5);
    }

    @Test
    void test03() {
        int bridgeLength = 2;
        int weight = 10;
        int[] truckWeights = {7, 4, 5};
        assertThat(solution(bridgeLength, weight, truckWeights)).isEqualTo(6);
    }

    @Test
    void test04() {
        int bridgeLength = 2;
        int weight = 10;
        int[] truckWeights = {7,4,5,6};
        assertThat(solution(bridgeLength, weight, truckWeights)).isEqualTo(8);
    }

    @Test
    void test05() {
        int bridgeLength = 100;
        int weight = 100;
        int[] truckWeights = {10};
        assertThat(solution(bridgeLength, weight, truckWeights)).isEqualTo(101);
    }

    @Test
    void test06() {
        int bridgeLength = 100;
        int weight = 100;
        int[] truckWeights = {10,10,10,10,10,10,10,10,10,10};
        assertThat(solution(bridgeLength, weight, truckWeights)).isEqualTo(110);
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int seconds = 0;


        Queue<Integer> truckWeights = toQueue(truck_weights);// 대기중인 트럭의 무게 큐
        Queue<Integer> bridge = new LinkedList<>();         // 다리를 건너고 있는 트럭 큐
        Queue<Integer> beginSeconds = new LinkedList<>();   // 다리를 건너기 시작한 시점의 경과 시간
        int totalWeightAboveBridge = 0;

        while (true) {
            seconds++;

            if (!beginSeconds.isEmpty()) {
                // 다리를 건너고 있는 맨 앞의 트럭이 다리를 다 건넜다면 다리 위 전체 무게를 차감한다.
                if (seconds - beginSeconds.peek() == bridge_length) {
                    beginSeconds.poll();
                    totalWeightAboveBridge -= bridge.poll();
                }
            }

            if (!truckWeights.isEmpty()) {
                // 다리를 건너려는 트럭과 현재 다리를 건너고 있는 총 무게를 계산하여 다리를 건널지를 판단한다.
                Integer next = truckWeights.peek();
                if (totalWeightAboveBridge + next <= weight) {
                    Integer truckWeight = truckWeights.poll();
                    bridge.add(truckWeight);
                    beginSeconds.add(seconds);
                    totalWeightAboveBridge += truckWeight;
                }
            }

            // 대기중인 트럭이 없고, 다리위의 트럭도 없다면 루프를 끝낸다.
            if (allPassed(truckWeights, bridge)) break;
        }
        return seconds;
    }

    private boolean allPassed(Queue<Integer> truckWeights, Queue<Integer> bridge) {
        return (truckWeights.size() + bridge.size()) == 0;
    }

    private Queue<Integer> toQueue(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int n : arr) q.add(n);
        return q;
    }
}
