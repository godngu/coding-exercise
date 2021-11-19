package study.coding.history;

import java.util.Stack;

/**
 * Stack을 이용하여 열렸던 괄호가 잘 닫혔는지 확인하기.
 */
public class Main {

    public static boolean isValid(String brackets) {

        if (brackets == null || "".equals(brackets)) {
            throw new UnsupportedOperationException("Unsupported Operation");
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < brackets.length(); i++) {
            char ch = brackets.charAt(i);

            if (ch == '[' || ch == '{' || ch == '(') {
                stack.push(ch);
                continue;
            }

            if (ch == ']') {
                char top = stack.peek();
                if (top != '[') {
                    return false;
                }
                stack.pop();
            }

            if (ch == '}') {
                char top = stack.peek();
                if (top != '{') {
                    return false;
                }
                stack.pop();
            }

            if (ch == ')') {
                char top = stack.peek();
                if (top != '(') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }


    public static void main(String[] args) {
        System.out.println(Main.isValid("([{}])"));
        System.out.println(Main.isValid("([)]"));
        System.out.println(Main.isValid("([{}}])"));
        System.out.println(Main.isValid("([[{}])"));
    }
}
