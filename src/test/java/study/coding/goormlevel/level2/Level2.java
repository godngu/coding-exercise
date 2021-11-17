package study.coding.goormlevel.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;

public class Level2 {

    private String[] toStringArr(String input) {
        return input.split("\\s");
    }

    private int[] toIntArr(String input) {
        return Arrays.stream(input.split("\\s")).mapToInt(s -> Integer.valueOf(s)).toArray();
    }

    @Test
    void 약수_최적화() {
        int n = 12;// 입력값
        divisor(n);
    }

    /**
     * 약수 최적화
     */
    private List<Integer> divisor(int n) {
        int sqrt = (int) Math.sqrt(n);
        List<Integer> list = new ArrayList<>();// 약수 수집

        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {   // 약수 중 작은 수 저장
                list.add(i);
                if (n / i != i) {// 약수 중 큰 수 저장
                    list.add(n / i);
                }
            }
        }
//        list.sort(Comparator.naturalOrder()); // 정렬
        return list;
    }

    /**
     * 홀수는 없다.
     */
    @Test
    void 완전수() {
        int[] nums = {10, 1000};
        int begin = nums[0], end = nums[1];

        for (int n = begin; n <= end; n++) {
            if (n%2 != 0)
                continue;
            if (isPerfectNumber(n)) {
                System.out.print(String.format("%d ", n));
            }
        }
    }

    private boolean isPerfectNumber(int n) {
        List<Integer> list = divisor(n);
        int sum = list.stream().mapToInt(i -> i).sum();
        return sum - n == n;
    }

    @Test
    void 계산기() {
        String input = "10 / 5";
        String[] arr = input.split("\\s");
        String symbol = arr[1];
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[2]);

        switch (symbol) {
            case "+":
                System.out.println(a+b);
                break;
            case "-":
                System.out.println(a-b);
                break;
            case "*":
                System.out.println(a*b);
                break;
            case "/":
                double c = (double) a;
                double _a = (double) a;
                double _b = (double) b;
                System.out.println(String.format("%.2f", _a / _b));
                break;
            default:
                throw new IllegalArgumentException("잘못된 연산기호!");
        }
    }

    /**
     * 타임아웃....
     */
    @Test
    void 방_탈출하기() {
        int[] n = {4, -6, 9, -2, 0, -7, -9, -3, -1, -5};
        int[] m = {4, 10, 5, 9, -8, -10, -8, 4, 10, -5};

        Map<Integer, Boolean> check = new HashMap<>();

        for (int a : m) {
            if (check.getOrDefault(a, null) != null) {
                if (check.getOrDefault(a, null))
                    System.out.println(1);
                else
                    System.out.println(0);
                continue;
            }

            boolean has = false;
            for (int b : n) {
                if (a == b) {
                    has = true;
                    break;
                }
            }

            check.put(a, has);
            if (has) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    /**
     * 타임아웃 3개 (72점)
     */
    @Test
    void 방_탈출하기2() {

        String input2 = "4 -6 9 -2 0 -7 -9 -3 -1 -5";
        String input4 = "4 10 5 9 -8 -10 -8 4 10 -5";

        Map<String, Boolean> map = new HashMap<>();

        Set<String> n = new HashSet<>();
        StringTokenizer st = new StringTokenizer(input2);
        while (st.hasMoreTokens())
            n.add(st.nextToken());

        StringTokenizer st2 = new StringTokenizer(input4);
        String s = "";
        boolean exists;

        while (st2.hasMoreTokens()) {
            s = st2.nextToken();

            if (map.containsKey(s)) {
                if (map.get(s)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
                continue;
            }

            exists = n.contains(s);
            map.put(s, exists);
            if (exists) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            n.remove(s);
        }
    }

    /**
     * [퀵 정렬] + [이진 탐색]
     * 탐색 대상을 우선 정렬해야 한다. 그래야 2진 탐색이 가능하다.
     * 정렬 방법은 버블정렬도 있는데 시간복잡도(O(n^2))가 높아 별반 차이가 없다.
     *
     * Arrays.sort()의 내장 정렬 함수는 퀵 정렬 이다.
     * 입력값을 배열로 변경하는 Stream 로직은 효율이 좋지 않다.
     */
    @Test
    void 방_탈출하기3() {
        String input1 = "5";
        String input2 = "1 7 9 5 2";
        String input3 = "10";
        String input4 = "1 2 3 4 5 6 7 8 9 10";

        int nSize = Integer.parseInt(input1);
        int mSize = Integer.parseInt(input3);

        int[] n = toIntArrayFromSpaceDelimiter(nSize, input2);
        int[] m = toIntArrayFromSpaceDelimiter(mSize, input4);

        Arrays.sort(n);

        for (int key : m) {
            int result = binarySearch(n, key, 0, n.length - 1);
            System.out.println(result);
        }
    }

    private static int[] toIntArrayFromSpaceDelimiter(int cnt, String input) {
        String[] arr = input.split("\\s");
        int[] nums = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        return nums;
    }

    private int binarySearch(int[] arr, int key, int low, int high) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (key == arr[mid]) {
                return 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0; // 탐색 실패
    }

    @Test
    void 소희와_버스() {
//        int n = 3, t = 7;
//        int[][] buses = {{2,2}, {2,3}, {2,4}};
        int n = 2, t = 5;
        int[][] buses = {{6,1}, {3,2}};

        int idx = 0, nearTime = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] bus = buses[i];

            int k = 0;
            while (true) {
                int time = bus[0] + bus[1] * k++;
                if (time >= t) {
                    if (time < nearTime) {
                        idx = i;
                        nearTime = time;
                    }
                    break;
                }
            }
        }
        System.out.println(String.format("idx=%d time=%d", idx+1, nearTime));
    }
}
