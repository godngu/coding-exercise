package study.coding.goormlevel.level1;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.BooleanSignature;

//import java.io.*;
//import java.math.*;
//import java.util.*;
//import java.lang.*;

public class Main {

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        sc.nextInt();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] strings = input.split("\\s");
        int[] nums = Arrays.stream(strings).mapToInt(s -> Integer.valueOf(s)).toArray();

        System.out.println(solution(input));
        br.close();
    }

    private int[] toIntArr(String input) {
        return Arrays.stream(input.split("\\s")).mapToInt(s -> Integer.valueOf(s)).toArray();
    }

    @Test
    void test1() {
        String input = "20";
        assertThat(solution(input)).isEqualTo("1 2 4 5 10 20 ");
    }

    private static String solution(String input) {
        String result = "";
        StringBuilder sb = new StringBuilder();

        int n = Integer.valueOf(input);

        for (int i = 1; i < n+1; i++) {
            if (n % i > 0)
                continue;
            sb.append(i).append(" ");
        }
        return sb.toString();
    }

    @Test
    void 삼_육_구() {
        int n = 35;
        long sum = 0;
        for (int i=1; i<n; i++) {
            long cnt = count(i);
            System.out.println(String.format("%d : %d", i, cnt));
            sum += cnt;
        }

        System.out.println(sum);
    }

    private long count(int n) {
        String s = String.valueOf(n);
        return s.chars()
            .filter(c -> c == '3' || c == '6' || c == '9')
            .count();
    }

    @Test
    void 의좋은_형제() {
        String input = "100 100";
        String input2 = "4";

        int[] nums = toIntArr(input);
        int days = Integer.valueOf(input2);

        int jw = 0, sw = 1;

        int aa = 125/2;
        System.out.println(aa);

        for (int i = 0; i < days; i++) {
            if (i%2 == 0) {
                divide(nums, jw, sw);
            } else {
                divide(nums, sw, jw);
            }
            print(i, nums);
        }
    }

    private void divide(int[] brothers, int s, int r) {
        brothers[r] = brothers[r] + (brothers[s] - brothers[s] / 2);
        brothers[s] = brothers[s] / 2;
    }

    private void print(int i, int[] nums) {
        System.out.println(String.format("[%d] jw: %d, sw: %d", i, nums[0], nums[1]));
    }

    // 입력 수샂가 크면 타임아웃에 걸린다...
    @Test
    void 정사각형_개수() {
        String input = "1000000";
        int n = Integer.valueOf(input);

        long cnt = 0;

        for (long l = 1; l < n+1; l++) {
            for (long i = 0; i < n - l + 1; i++) {
                for (long x = 0; x < n - l + 1; x++) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    @Test
    void 정사각형_개수2() {
        String input = "1000000";
        int n = Integer.valueOf(input);

        BigInteger cnt = BigInteger.ZERO;
        for (int i = 1; i < n+1; i++) {
            cnt = cnt.add(BigInteger.valueOf(i).pow(2));
        }
        System.out.println(cnt);
    }

    @Test
    void 비트연산_기본2() {
        String input = "5 3";
        int[] nums = toIntArr(input);
        System.out.println(nums[0] >> nums[1]);
        System.out.println(nums[0] << nums[1]);
    }

    @Test
    void 리그_경기_횟수_꾸하기() {
        int n = 10;
        int sum = 0;
        for (int i = 1; i < n; i++) {
             sum += i;
        }
        System.out.println(sum);
    }

    @Test
    void 고장난_컴퓨터() {
//        String input = "6 5";// 입력한 글자수, 지워지지 않고 유지되는 시간
//        String input2 = "1 3 8 14 19 20";
//        String input = "6 1";// 입력한 글자수, 지워지지 않고 유지되는 시간
//        String input2 = "1 3 5 7 9 10";
//        String input = "5 5";// 입력한 글자수, 지워지지 않고 유지되는 시간
//        String input2 = "1 7 12 13 14";
        String input = "1 1";// 입력한 글자수, 지워지지 않고 유지되는 시간
        String input2 = "1000000000";
        int[] nums1 = toIntArr(input);
        int[] nums2 = toIntArr(input2);

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = Arrays.stream(nums2).sorted().boxed().collect(Collectors.toList());
        for (Integer integer : list)
            queue.add(integer);

        int n = nums1[0];
        int s = nums1[1];
        int cnt = 0;

        while (queue.size() > 1) {
            Integer a = queue.poll();
            Integer b = queue.peek();
            if (b-a > s) {
                cnt = 0;
            } else {
                cnt++;
            }

            if (queue.size() == 1) {
                break;
            }
        }
        cnt++;
        System.out.println(cnt);
    }

    /**
     * popooqoq
     * bvdobvd
     * banana
     */
    @Test
    void 거울단어() {
        // [중요한 점]
        // 문자열 길이가 홀수 일 수 있다.
        // 이 경우 가운데 문자열을 추출하여 별개로 거울단어 여부를 검사해야 한다.
        // 만약 중간단어가 i, o와 같이 원본과 거울단어가 맞다면 true이다.
        // 하지만 b, d, q, s, z와 같은 경우 false가 된다.
        String str = "bvdobvd";
        System.out.println(mirror(str));
    }

    private String mirror(String str) {
        char[] a = {'b', 'd', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 's', 'z', 'u', 'v', 'w', 'x'};
        char[] b = {'d', 'b', 'i', 'l', 'm', 'n', 'o', 'q', 'p', 'z', 's', 'u', 'v', 'w', 'x'};

        int size = str.length();
        boolean even = size%2 == 0;

        String half = str.substring(0, size/2);
        String mirror = str.substring(even ? size/2 : size/2 + 1, size);
        System.out.println(String.format("%s %s", half, mirror));

        char[] words = half.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (char word : words) {
            for (int j = 0; j < a.length; j++) {
                if (word == a[j]) {
                    sb.append(b[j]);
                    break;
                }
            }
        }
        String result = sb.reverse().toString();
        System.out.println(result);

        // 전체 문자열이 홀수개일 때 중간 문자열 거울단어 여부 확인
        boolean middle = false;
        if (!even) {
            char m = str.substring(size/2, size/2 + 1).charAt(0);
            System.out.println(m);
            for (int i = 0; i < a.length; i++) {
                if (m == a[i]) {
                    if (a[i] == b[i]) {
                        middle = true;
                        break;
                    }
                }
            }
        }

        return mirror.equals(result) && (even || (!even && middle)) ? "Mirror" : "Normal";
    }

    @Test
    void 진법변환() {
//        String input = "123 323";// 6
//        String input = "4576 3490";// 11
//        String input = "1234567890 1001001100101100000001011010010";// 2
        String input = "7 7";// 7
        String[] inputs = input.split("\\s");
        notation(inputs[0], inputs[1]);
    }

    private int notation(String a, String b) {
        int result = 0;

        long d = Long.valueOf(a);

        for (int i=2; i<17; i++) {
            try {
                long num = Long.parseLong(b, i);
                if (d == num) {
                    result = i;
                    break;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        System.out.println(result);
        return result;
    }

    @Test
    void 뱀이_지나간_자리() {
//        String input = "3 3";
//        String input = "3 6";
//        String input = "6 4";
        String input = "9 9";
        int[] nums = toIntArr(input);
        int x = nums[0];
        int y = nums[1];

        boolean left = true;

        for (int i = 0; i < x; i++) {
            if (i%2 == 0) {
                snakePrint(0, y, "#");
                left = !left;
            } else {
                if (left) {
                    System.out.print("#");
                    snakePrint(1, y, ".");
                } else {
                    snakePrint(0, y-1, ".");
                    System.out.print("#");
                }
            }
            System.out.println("");
        }
    }
    private void snakePrint(int begin, int end, String s) {
        IntStream.range(begin, end).forEach(n -> {
            System.out.print(s);
        });
    }

    // 몇몇 테스트에서 실패한다.
    @Test
    void 막대기() {
//        int[] nums = {6,9,7,6,4,6};// 3
        int[] nums = {5,4,3,2,1};// 5
        int first = nums[nums.length - 1];

        long count = Arrays.stream(nums).filter(n -> n > first)
            .count();
        System.out.println(count + 1);
    }

    // max값 뒤에 나오는 값들은 무시한다.
    @Test
    void 막대기2() {
//        int size = 6;
//        int[] nums = {6,9,7,6,4,6};
//        int size = 5;
//        int[] nums = {5,4,3,2,1};// 5
        int size = 5;
        int[] nums = {3, 5, 4, 3, 1, 2}; // 4

        int cnt = 1;
        int max = 0;
        int last = nums[nums.length - 1];
        for (int i = size - 2; i >= 0; i--) {
            int num = nums[i];
            if (num <= max)
                continue;

            max = num;

            if (num > last) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    /**
     * 3
     * Hello, World!
     * I'm your father.
     * What are you doing here?
     */
    @Test
    void 앵무새_꼬꼬() {
        int size = 3;
        char[] arr = {'a', 'e', 'i', 'o', 'u'};

        String[] str = {"Hello, World!", "I'm your father.", "What are you doing here?"};

        List<String> result = new ArrayList<>();
        for (String s : str) {
            int cnt = 0;
            StringBuilder sb = new StringBuilder();

            char[] chars = s.toCharArray();
            for (char c : chars) {
                for (char a : arr) {
                    if (Character.toLowerCase(c) == a) {
                        sb.append(c);
                        cnt++;
                    }
                }
            }
            result.add(cnt > 0 ? sb.toString() : "???");
        }
        result.stream().forEach(System.out::println);
    }

    @Test
    void 최소값() {
        int[] nums = {-1, -2, -3, -4, -5, 1, 2, 3, 4, 5};
        int result = Arrays.stream(nums).min().getAsInt();
        System.out.println(result);
    }

    /**
     * 공식을 알아야 한다.
     * https://blog.naver.com/PostView.nhn?blogId=lghmms&logNo=221842140003
     * 넓이가 마이너스가 나오지 않도록 Math.abs()를 반드시 해야 한다.
     */
    @Test
    void 여름의_대삼각형() {
//        int[][] n = {{0, 0}, {3, 0}, {0, 4}};
        int[][] n = {{1, 1}, {2, 2}, {2, 1}};
        int[] a = n[0];
        int[] b = n[1];
        int[] c = n[2];

        int x = 0, y = 1;
        double area = a[x] * (b[y] - c[y]) + b[x] * (c[y] - a[y]) + c[x] * (a[y] - b[y]);
        area = Math.abs(area);
        area = area / 2;
        System.out.println(String.format("%.2f", area));
    }

    /**
     * 2의 n승이면 2진수로 변환 했을때 맨 왼쪽만 1이고 나머지는 0으로 채워진다.
     * 256 -> 100000000
     * 512 -> 1000000000
     * 1024 -> 10000000000
     * 3 -> 11
     */
    @Test
    void 파손된_램() {
//        int[] nums = {256, 274, 512};
        int[] nums = {1048576, 16384, 131072, 8388608, 834814, 67108864, 8192, 1267650, 128, 129};

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            String s = Integer.toBinaryString(nums[i]);
            if (s.chars().filter(c -> '1' == c).count() > 1) {
                sb.append(i+1);
                if (i < nums.length - 1) {
                    sb.append(" ");
                }
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    // 타임아웃 오류 발생
    @Test
    void 회문() {
        // 회문=0, 유사회문=1, 일반=2
        // 0 1 1 2 2 0 1
        String[] strings = {"abba", "summuus", "xabba", "xabbay", "comcom", "comwwmoc", "comwwtmoc"};
        for (String str : strings) {
            System.out.println(palindrome(str));
        }
    }

    /**
     * 단어의 좌/우 끝에서부터 한칸식 중앙으로 이동한다.
     * 중앙으로 이동할때 까지 다른 문자가 없다면 회문이다.
     * 만약 다른 문자가 발결된 경우 유사회문을 찾아야 하는데,
     * 유사회문의 경우 한 문자만 차이가 있으므로 좌(l++) 또는 우(r--)에서 철자 하나씩 이동시켜서 비교한다.
     * 이렇게 했을때 좌우 철자를 여러번 이동해도 같은 단어가 발견되지 않는다면 유사회문이 아니다.
     */
    private int palindrome(String str) {
        int result = 0, left = 0, right = str.length() - 1;
        char[] c = str.toCharArray();

        while (left < right) {
            if (c[left] == c[right]) {
                left++;
                right--;
            } else {
                boolean checkL = palindromeSecond(c, left + 1, right);
                boolean checkR = palindromeSecond(c, left, right - 1);
                if (checkL || checkR)
                    return 1;
                else
                    return 2;
            }
        }
        return 0;
    }
    private boolean palindromeSecond(char[] c, int left, int right) {
        while (left < right) {
            if (c[left] == c[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * [런타임 에러 발생...]
     *
     * DFS(Depth-First Search, 깊이 우선 탐색)
     * 참고: https://loosie.tistory.com/175?category=982577
     * 추들이 낼 수 있는 조합을 result에 저장한다.
     * [가능 조합]
     * 1. 단일 추
     * 2. 접시 vs (추 + 추)
     * 3. (접시 + 추) vs 추
     */
    @Test
    void 양팔저울() {
        int[] weight = {1, 5, 7};
        int n = weight.length;
        int sum = Arrays.stream(weight).sum();
        boolean[][] result = new boolean[n+1][sum * n];

        dp(weight, result, 0, 0);

        int cnt = 0;
        for(int i = 1; i < sum + 1; i++) {
            if(result[n][i]) {
//                System.out.println(String.format("%b %d",result[n][i] , i));
            }
            if(!result[n][i]) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private void dp(int[] weight, boolean[][] result, int idx, int num) {
        // 이미 했던 조합은 제외
        if(result[idx][num])
            return;

        result[idx][num] = true;

        if(idx == weight.length	)
            return;

        dp(weight, result, idx+1, num + weight[idx]);
        dp(weight, result, idx+1, num);
        dp(weight, result, idx+1, Math.abs(num - weight[idx]));
    }

    /**
     * 타임아웃 오류 발생 (50점)
     */
    @Test
    void 쇼핑몰() {
        int n = 10, k = 3;
        int[][] arr = {
            {123, 4}, {21, 5}, {34, 14}, {56, 1}, {45, 7},
            {723, 5}, {55, 7}, {13, 5}, {910, 10}, {73, 3}
        };

        Queue<int[]> carts = new LinkedList<>();
        for (int[] a : arr) {
            carts.add(a);
        }

        int[][] counters = new int[k][2];
        List<Integer> finished = new ArrayList<>();

        int[] empty = {0, 0};

        while (finished.size() < n) {
            // 계산대 채우기
            for (int i = 0; i < k; i++) {
                if (counters[i][1] == 0 && !carts.isEmpty()) {
                    counters[i] = carts.poll();
                }
            }

            int min = 1;
            for (int[] counter : counters) {
                min = counter[1] < min && counter[1] > 0 ? counter[1] : min;
            }


            // 나갈때는 인덱스가 큰 순서대로 나간다.
            for (int i = k - 1; i >= 0; i--) {
                counters[i][1] = counters[i][1] - min;

                // 계산이 끝났으면
                if (counters[i][0] > 0 && counters[i][1] == 0) {
                    finished.add(counters[i][0]);// 회원 번호만 담는다.
                    counters[i] = empty; // 카운터 초기화
                }
            }
        }

        long result = 0;
        for (int i = 0; i < finished.size(); i++) {
            result += ((i+1) * finished.get(i));
        }

        assertThat(result).isEqualTo(13900);
    }

}

