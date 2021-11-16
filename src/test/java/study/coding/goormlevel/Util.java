package study.coding.goormlevel;

public class Util {


    /**
     * 공백으로 구분된 문자열을 int[]로 변환한다.
     */
    private static int[] toArray(int cnt, String input) {
        String[] arr = input.split("\\s");
        int[] nums = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        return nums;
    }

    private static String[] toStringArray(String input) {
        return input.split("\\s");
    }
}
