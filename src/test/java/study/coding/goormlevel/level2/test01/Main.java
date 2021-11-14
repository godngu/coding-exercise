package study.coding.goormlevel.level2.test01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(solution(input));
    }

    private static String solution(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }
}
