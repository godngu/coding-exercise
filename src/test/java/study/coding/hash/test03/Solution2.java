package study.coding.hash.test03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Solution2 {

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

    public int solution(String[][] clothes) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = toMap(clothes);

        return 0;
    }

    private List<List<String>> toList(String[][] clothes) {
        Map<String, List<String>> map = new HashMap<>();

        for (String[] cloth : clothes) {
            String key = cloth[1];
            List<String> values = map.getOrDefault(key, new ArrayList<>());
            values.add(cloth[0]);

            map.put(key, values);
        }
        return map.values().stream().collect(Collectors.toList());
    }

    private Map<String, List<String>> toMap(String[][] clothes) {
        Map<String, List<String>> map = new HashMap<>();

        for (String[] cloth : clothes) {
            String key = cloth[1];
            List<String> values = map.getOrDefault(key, new ArrayList<>());
            values.add(cloth[0]);

            map.put(key, values);
        }
        return map;
    }

    private void temp(int digit, int index, Map<String, List<String>> map) {

        List<String> usedKeys = new ArrayList<>();

        for (String key : map.keySet()) {
            if (usedKeys.contains(key)) continue;
            List<String> values = map.get(key);
        }

    }
}
