package com.cloudwalk.shark.interview.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.jdk8
 * @date:2019/8/23
 */
public class CollectionStream {
    public static void main(String args[]){
        List<String> strings = Arrays.asList("abc", "dd", "bc", "efg", "abcd","", "jkl");
        List<String> filters = strings.stream().filter(s -> s.length() == 3).collect(Collectors.toList());
        filters.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        filters.forEach(System.out::println);
        System.out.println("***************");
//        ("asd")->{System.out::println};
        strings.stream().filter(s -> s.length()==3).collect(Collectors.toList()).forEach(System.out::println);

        Random random = new Random();


        random.ints().limit(10).forEach(System.out::println);

    }


    interface MathOperation {
        int operation(int a, int b);
    }
}
