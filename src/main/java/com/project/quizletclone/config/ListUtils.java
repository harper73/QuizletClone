package com.project.quizletclone.config;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T> List<List<T>> partition(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        int totalSize = list.size();
        for (int i = 0; i < totalSize; i += size) {
            partitions.add(new ArrayList<>(list.subList(i, Math.min(totalSize, i + size))));
        }
        return partitions;
    }
}
