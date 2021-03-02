package study.coding.hash.test03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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

    public int solution(String[][] clothes) {

        List<Set<String>> list = toList(clothes);
        List<List<String>> result = new ArrayList<>();

        for (int digit = 1; digit <= list.size(); digit++) {
            result.add(fillByDigit(digit, list));
        }

        printResult(result);
        return result.stream().mapToInt(List::size).sum();
    }

    private List<String> fillByDigit(int digit, List<Set<String>> list) {
        List<String> result = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < list.size(); rowIndex++) {
//            if (digit == 1) {
//            if (isFirstDigit(digit)) {
//                Set<String> firstDigits = list.get(rowIndex);
//                result.addAll(concatLast("", firstDigits));
//            }
//
//            if (digit == 2 && rowIndex < digit) {
//                Set<String> firstDigits = list.get(rowIndex);
//                for (String first : firstDigits) {
//                    concatString(digit, rowIndex, list, first, result);
//                }
//            }

            if (isLastDigit(digit, list) && rowIndex < digit) {
                Set<String> firstDigits = list.get(rowIndex);
                for (String first : firstDigits) {
                    concatStringTemp(digit, rowIndex, list, first, result);
                }
            }
        }
        System.out.println("result.size() = " + result.size());
        return result;
    }

    private void concatStringTemp(int digit, int parentRowIndex, List<Set<String>> list, String prefix, List<String> result) {
        for (int i = parentRowIndex+1; i < list.size(); i++) {
            Set<String> clothes = list.get(i);

//            for (String cloth : clothes) {
//                concatString(digit, i, list, cloth, result);
//            }

            System.out.println("i = " + i);

            if (i == 1) {
                System.out.println("prefix if = " + prefix);
                for (String cloth : clothes) {
                    System.out.println("prefix2 = " + prefix + cloth);
                    concatStringTemp(digit, i+1, list, prefix + cloth, result);
                }
            } else {
                System.out.println("prefix else = " + prefix);
                result.addAll(concatLast(prefix, clothes));
            }
        }
    }

    private void concatString(int digit, int parentIndex, List<Set<String>> list, String prefix, List<String> result) {

        for (int i = parentIndex+1; i < list.size(); i++) {
            Set<String> strings = list.get(i);
            result.addAll(concatLast(prefix, strings));
        }
    }

    private List<String> concatLast(String prefix, Set<String> targets) {
        List<String> result = new ArrayList<>();
        for (String target : targets) {
            System.out.println(prefix + target);
            result.add(prefix + target);
        }
        return result;
    }

    private boolean isFirstDigit(int digit) {
        return digit == 1;
    }

    private boolean isLastDigit(int digit, List<Set<String>> list) {
        return digit == list.size();
    }

    private void printResult(List<List<String>> result) {
        for (List<String> strings : result) {
            String collect = strings.stream().collect(Collectors.joining(","));
            System.out.println("collect = " + collect);
        }
    }

    private Map<String, Set<String>> toMap(String[][] clothes) {
        Map<String, Set<String>> clothesMap = new HashMap<>();
        for (String[] cloth : clothes) {
            String key = cloth[1];

            Set<String> clothSet = clothesMap.get(key);
            if (clothSet == null) {
                clothSet = new HashSet<>();
                clothesMap.put(key, clothSet);
            }
            clothSet.add(cloth[0]);
        }
        return clothesMap;
    }

    private List<Set<String>> toList(String[][] arr) {

        List<Set<String>> list = new ArrayList<>();
        Map<String, Set<String>> map = toMap(arr);
        for (Set<String> value : map.values()) {
            list.add(value);
        }
        return list;
    }

}
