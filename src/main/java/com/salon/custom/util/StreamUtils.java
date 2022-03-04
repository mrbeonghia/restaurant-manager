package com.salon.custom.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamUtils {

    public static <T, K, A, V> Map<K, V> group(Collection<T> input,
                                               Function<T, K> function,
                                               Collector<? super T, A, V> downStream) {
        return input.stream().collect(Collectors.groupingBy(function, downStream));
    }

    public static <T, K> Map<K, List<T>> groupToList(Collection<T> input,
                                                     Function<T, K> function) {
        return input.stream().collect(Collectors.groupingBy(function, Collectors.toList()));
    }

    public static <T, K> Map<K, Set<T>> groupToSet(Collection<T> input,
                                                     Function<T, K> function) {
        return input.stream().collect(Collectors.groupingBy(function, Collectors.toSet()));
    }

    public static <T, K1, K2> Map<K1, Map<K2, List<T>>> groupToMap(Collection<T> input,
                                                                   Function<T, K1> function1,
                                                                   Function<T, K2> function2) {
        return input.stream().collect(Collectors.groupingBy(function1, Collectors.groupingBy(function2)));
    }

    public static <T, R> List<R> toList(Collection<T> input,
                                        Function<T, R> function) {
        return input.stream().map(function).collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSet(Collection<T> input,
                                      Function<T, R> function) {
        return input.stream().map(function).collect(Collectors.toSet());
    }

    public static <T, K> Map<K, T> toMap(Collection<T> input,
                                         Function<T, K> function) {
        return input.stream().collect(Collectors.toMap(function, Function.identity()));
    }
}
