package study.coding.useful;

import static java.util.UUID.randomUUID;

import org.junit.jupiter.api.Test;

/**
 * 경우의 수 구하기
 */
public class Combination {

    /**
     * 백트래킹(backtracking)
     */
    @Test
    void name() {
        int[] nums = {1, 5, 7};
        int n = nums.length;
        boolean[] visited = new boolean[n];

        // 조합 가능 경우의 수 구하기
        for (int i = 1; i <= n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
            combination(nums, visited, 0, i);
        }
    }

    private void combination(int[] arr, boolean[] visited, int start, int r) {
        String id = createId();
        int n = arr.length;

        if (r == 0) {
            printA(arr, visited, n);
            return;
        }
        for (int i = start; i < n; i++) {
//            System.out.println("ID="+id);
            visited[i] = true;
            combination(arr, visited, i + 1, r - 1);
            visited[i] = false;
        }
    }

    // 배열 출력
    static void printA(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    private String createId() {
        // 앞 8자리만 사용
        return randomUUID().toString().substring(0, 8);
    }
}
