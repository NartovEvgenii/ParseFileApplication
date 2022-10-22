package ru.nartov.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArrayParsingService {

    public Integer getMAXElementFromArray(Integer[] array) {
        int max = Integer.MIN_VALUE;
        for (Integer elem : array) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    public Integer getMINElementFromArray(Integer[] array) {
        int min = Integer.MAX_VALUE;
        for (int elem : array) {
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }

    public Integer getMedianFromArray(Integer[] array) {
        Arrays.sort(array);
        return array[array.length / 2];
    }

    public Integer getAverageFromArray(Integer[] array) {
        Integer sum = 0;
        for (Integer elem : array) {
            sum += elem;
        }
        return sum / array.length;
    }

    public List<List<Integer>> getIncreaseSeqFromArray(Integer[] array) {
        List<Integer> startIndexes = new ArrayList<>();
        int maxLength = 0;
        int currentLength = 0;
        for (int i = 0; i < array.length; i++) {
            currentLength++;
            if (i + 1 == array.length || array[i + 1] < array[i]) {
                if (maxLength == currentLength) {
                    startIndexes.add(i - maxLength + 1);
                } else if (maxLength < currentLength) {
                    maxLength = currentLength;
                    startIndexes.clear();
                    startIndexes.add(i - maxLength + 1);
                }
                currentLength = 0;
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Integer startIndex : startIndexes) {
            result.add(Arrays.asList(getSubArrayFromArray(array, startIndex, maxLength)));
        }
        return result;
    }

    public List<List<Integer>> getDecreaseSeqFromArray(Integer[] array) {
        List<Integer> startIndexes = new ArrayList<>();
        int maxLength = 0;
        int currentLength = 0;
        for (int i = 0; i < array.length; i++) {
            currentLength++;
            if (i + 1 == array.length || array[i + 1] > array[i]) {
                if (maxLength == currentLength) {
                    startIndexes.add(i - maxLength + 1);
                } else if (maxLength < currentLength) {
                    maxLength = currentLength;
                    startIndexes.clear();
                    startIndexes.add(i - maxLength + 1);
                }
                currentLength = 0;
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Integer startIndex : startIndexes) {
            result.add(Arrays.asList(getSubArrayFromArray(array, startIndex, maxLength)));
        }
        return result;
    }

    private Integer[] getSubArrayFromArray(Integer[] inputArr, int from, int len) {
        Integer[] resultArray = new Integer[len];
        for (int i = from, j = 0; i < from + len; i++, j++) {
            resultArray[j] = inputArr[i];
        }
        return resultArray;
    }
}
