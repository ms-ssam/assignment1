package com.example.lesson_api.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VersionComparator implements Comparator<String> {
    private String precedingVersion;

    public static VersionComparator of(String precedingOperand) {
        return new VersionComparator(precedingOperand);
    }

    /**
     * precedingVersion이 followingVersion보다 크거나 같은지 비교
     * @param followingVersion
     * @return
     */
    public boolean greaterThanOrEquals(String followingVersion) {
        return this.compare(precedingVersion, followingVersion) >= 0;
    }

    /**
     * precedingVersion이 followingVersion보다 작은지 비교
     * @param followingVersion
     * @return
     */
    public boolean lessThan(String followingVersion) {
        return this.compare(precedingVersion, followingVersion) < 0;
    }

    @Override
    public int compare(String precedingVersion, String followingVersion) {
        List<Integer> precedingVersionTermList = Arrays.stream(precedingVersion.split("\\."))
                .map(Integer::parseInt)
                .toList();

        List<Integer> followingVersionTermList = Arrays.stream(followingVersion.split("\\."))
                .map(Integer::parseInt)
                .toList();

        boolean versionFormMatched = precedingVersionTermList.size() != followingVersionTermList.size();

        if (versionFormMatched) {
            throw new IllegalArgumentException("Version form is not matched");
        }

        List<Integer> versionCompareResultList = IntStream.range(0, precedingVersionTermList.size())
                .mapToObj(i -> Pair.of(precedingVersionTermList.get(i), followingVersionTermList.get(i)))
                .map(this::compareVersionTerm)
                .toList();

        for (Integer result : versionCompareResultList) {
            if (result != 0) {
                return result;
            }
        }

        // early return이 없는 경우에는 버전이 같은 것
        return 0;
    }

    private Integer compareVersionTerm(Pair<Integer, Integer> versionTermPair) {
        int precedingTerm = versionTermPair.getFirst();
        int followingTerm = versionTermPair.getSecond();

        return Integer.compare(precedingTerm, followingTerm);
    }
}
