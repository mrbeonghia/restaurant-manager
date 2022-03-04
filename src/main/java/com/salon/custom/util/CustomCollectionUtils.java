package com.salon.custom.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomCollectionUtils {
    public static <V> Set<V> mergeTwoSet(Set<V> set1, Set<V> set2) {
        Set<V> result = new HashSet<>();
        result.addAll(set1);
        result.addAll(set2);

        return result;
    }

    public static <V> Collection<V> mergeTwoCollection(Collection<V> c1, Collection<V> c2) {
        Collection<V> result = new HashSet<>();
        result.addAll(c1);
        result.addAll(c2);

        return result;
    }

    public static <V> boolean isTwoCollectionEquals(Collection<V> c1, Collection<V> c2) {
        return c1.containsAll(c2) && c2.containsAll(c1);
    }
}
