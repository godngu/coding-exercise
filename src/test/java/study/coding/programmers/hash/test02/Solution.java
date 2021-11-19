package study.coding.programmers.hash.test02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 채점 결과
 * 정확성: 84.6
 * 효율성: 15.4
 * 합계: 100.0 / 100.0
 */
public class Solution {

    @Test
    void test1() {
        String[] phoneBook = {"119", "97674223", "1195524421"};
        Assertions.assertThat(solution(phoneBook)).isFalse();
    }

    @Test
    void test2() {
        String[] phoneBook = {"123", "456", "789"};
        Assertions.assertThat(solution(phoneBook)).isTrue();
    }

    @Test
    void test3() {
        String[] phoneBook = {"12", "123", "1235", "567", "88"};
        Assertions.assertThat(solution(phoneBook)).isFalse();
    }

    public boolean solution(String[] phone_book) {
        boolean answer = true;

        for (int i = 0; i < phone_book.length; i++) {
            String prefix = phone_book[i];

            for (int j = 0; j < phone_book.length; j++) {
                String phoneNumber = phone_book[j];
                if (i == j) continue;

                if (phoneNumber.startsWith(prefix)) {
                    return false;
                }
            }
        }
        return answer;
    }
}
