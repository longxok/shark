package com.cloudwalk.shark.interview.algorithm.interview;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.algorithm.interview
 * @date:2019/8/14
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 　给定一个只包含(‘, ‘)’, ‘{‘, ‘}’, ‘[’ 和‘]’的字符串，验证它是否是有效的。括号必须配对，并且要以正确的顺序。
 */
public class Test2 {
    public static void main(String args[]){

        System.out.println(isValid("[{}]"));
    }
    public static boolean isValid(String s) {

        Deque<Character> stack = new LinkedList<>();
        int index = 0;
        Character top;
        while (index < s.length()) {
            Character c = s.charAt(index);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.addFirst(c);
                    break;
                case ')':

                    if (stack.isEmpty()) {
                        return false;
                    }

                    top = stack.getFirst();
                    if (top == '(') {
                        stack.removeFirst();
                    } else if (top == '[' || top == '{') {
                        return false;
                    } else {
                        stack.addFirst(c);
                    }
                    break;
                case ']':

                    if (stack.isEmpty()) {
                        return false;
                    }

                    top = stack.getFirst();
                    if (top == '[') {
                        stack.removeFirst();
                    } else if (top == '(' || top == '{') {
                        return false;
                    } else {
                        stack.addFirst(c);
                    }
                    break;
                case '}':

                    if (stack.isEmpty()) {
                        return false;
                    }

                    top = stack.getFirst();
                    if (top == '{') {
                        stack.removeFirst();
                    } else if (top == '[' || top == '(') {
                        return false;
                    } else {
                        stack.addFirst(c);
                    }
                    break;
                default:
                    return false;
            }

            index++;
        }

        return stack.isEmpty();
    }

}
