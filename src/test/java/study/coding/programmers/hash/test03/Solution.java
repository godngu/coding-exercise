package study.coding.programmers.hash.test03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class Solution {

    @Test
    void test1() {
        String[][] clothes = {
            {"yellow_hat", "headgear"},
            {"blue_sunglasses", "eyewear"},
            {"green_turban", "headgear"}};
        assertThat(solution(clothes)).isEqualTo(5);
    }

    @Test
    void test2() {
        String[][] clothes = {
            {"crow_mask", "face"},
            {"blue_sunglasses", "face"},
            {"smoky_makeup", "face"}};
        assertThat(solution(clothes)).isEqualTo(3);
    }

    @Test
    void test3() {
        String[][] clothes = {
            {"A", "01"}, {"B", "01"},
            {"a", "02"}, {"b", "02"},
            {"#", "03"}, {"$", "03"}
        };
        assertThat(solution(clothes)).isEqualTo(26);
    }

    /**
     * (A옷 가지수 +1)*(B옷 가지수 +1)*...(n옷 가지수 +1) -1(아무것도 선택하지 않은 경우 제외)
     * +1: 옷을 선택하지 않은 경우를 포함하기 위함
     *
     * @param clothes
     * @return
     */
    public int solution(String[][] clothes) {

        Map<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes) {
            String key = cloth[1];
            if (map.containsKey(key)) {
                map.replace(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        int answer = 1;
        for (Integer value : map.values()) {
            answer *= (value + 1);
        }

        answer -= 1;
        System.out.println(answer);
        return answer;
    }

}
