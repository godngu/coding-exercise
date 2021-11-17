package study.coding.goormlevel.level5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.array;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class Level5 {

    /**
     * BFS(너비우선탐색)
     * 특정 위치를 기준으로 인접한 노드를 모두 방문하여 한 번 방문했던 노드는 방문 이력을 저장
     * BFS > DFS
     */
    @Test
    void 최단_거리_구하기() {
        int n = 5; // 10 이하의 자연수
        int[][] arr = {
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0},
            {1, 1, 1, 1, 1},
        };
        int result = bfs(n, arr, 0, 0, n-1, n-1);
        System.out.println(result);


        assertThat(result).isEqualTo(11);
    }

    private static int bfs(int n, int[][] arr, int beginX, int beginY, int destX, int destY) {
        final int PASS = 1, STOP = 0;
        final int[] UP = {0, 1}, DOWN = {0, -1}, LEFT = {1, 0}, RIGHT = {-1, 0};
        int[][] direction = {UP, DOWN, LEFT, RIGHT};

        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(beginX, beginY, PASS));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int dist = node.dist;
            visited[x][y] = true;

            // 목적지 도착
            if (x == destX && y == destY) {
                return dist;
            }

            // 다음 노드 탐색: 4방향 움직여보는 루프
            for (int[] d : direction) {
                int dX = x + d[0];
                int dY = y + d[1];
//                System.out.println(String.format("dX: %d, dY: %d", dX, dY));

                // 범위를 벗어났는지 확인
                if (dX < 0 || dX >= n || dY < 0 || dY >= n) {
                    continue;
                }
                // 방문여부 확인
                if (visited[dX][dY]) {
                    continue;
                }
                // 이동 가능여부 확인
                if (arr[dX][dY] == STOP) {
                    continue;
                }
                q.add(new Node(dX, dY, dist + 1));
            }
        }
        return -1;
    }

    static class Node {
        private int x;
        private int y;
        private int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
