package study.coding.goormlevel.level4;

import org.junit.jupiter.api.Test;

public class Level4 {

    /**
     * (0), 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89...
     */
    @Test
    void 피보나치수열() {
        int[] arr = new int[100];
        int result = fibo(arr, 9);
        System.out.println(String.format("result=%d", result));
    }

    private int fibo(int[] arr, int n) {
        System.out.println(n);
        if (n <= 2)
            return 1;
        if (arr[n] == 0)
            arr[n] = fibo(arr, n-1) + fibo(arr, n-2);
        return arr[n];
    }

    /**
     * 테스트 실패 (n=20, m=10 => 1 != 51)
     * DP (Dynamic Programing, 동적 계획법)
     * https://velog.io/@chelsea/1-%EB%8F%99%EC%A0%81-%EA%B3%84%ED%9A%8D%EB%B2%95Dynamic-Programming-DP
     * 처음 진행되는 연산은 기록해 두고, 이미 진행했던 연산이라면 다시 연산하는 것이 아니라 기록되어 있는 값을 가져온다.
     * dp[i] = (dp[i - 1] + dp[i - 2] * 2)
     */
    @Test
    void 타일_채우기() {
        int[] dp = new int[1001];
//        int n = 8, m = 100;
        int n = 20, m = 10;

        dp[1] = 1; dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 100;
            System.out.println(String.format("i= %d dp[i-1]= %d dp[i-2]*2= %d dp[i]= %d > %d", i, dp[i-1], dp[i-2]*2, dp[i], (dp[i - 1] + dp[i - 2] * 2)));
        }
        System.out.println(dp[n] % 100);

    }


}
