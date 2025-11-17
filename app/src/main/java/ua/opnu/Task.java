package ua.opnu;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Task {
    public static void main(String[] args) {

    }
    private static final int LENGTH_FOR_MARK = 4;

    public void removeShorterStrings(List<String> list) {
        for (int i = 0; i < list.size() - 1; i += 2) {
            String first = list.get(i);
            String second = list.get(i + 1);

            if (first.length() <= second.length()) {
                list.remove(i);
                i--;
            } else {
                list.remove(i + 1);
            }
        }
    }

    public void stutter(List<String> list) {
        for (int i = 0; i < list.size(); i += 2) {
            list.add(i + 1, list.get(i));
        }
    }

    public void switchPairs(List<String> list) {
        for (int i = 0; i < list.size() - 1; i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    public void removeDuplicates(List<String> list) {
        for (int i = 0; i < list.size() - 1;) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i + 1);
            } else {
                i++;
            }
        }
    }

    public void markLength4(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == LENGTH_FOR_MARK) {
                list.add(i, "****");
                i++;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return true;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            Integer val = queue.remove();
            queue.add(val);
            stack.push(val);
        }

        boolean palindrome = true;

        for (int i = 0; i < size; i++) {
            Integer val = queue.remove();
            Integer reverse = stack.pop();

            if (!val.equals(reverse)) {
                palindrome = false;
            }

            queue.add(val);
        }

        return palindrome;
    }

    public void reorder(Queue<Integer> queue) {
        ArrayDeque<Integer> negativeStack = new ArrayDeque<>();
        ArrayDeque<Integer> positiveQueue = new ArrayDeque<>();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            Integer val = queue.remove();
            if (val < 0) {
                negativeStack.push(val);
            } else {
                positiveQueue.add(val);
            }
        }

        while (!negativeStack.isEmpty()) {
            queue.add(negativeStack.pop());
        }

        while (!positiveQueue.isEmpty()) {
            queue.add(positiveQueue.remove());
        }
    }

    public void rearrange(Queue<Integer> queue) {
        Queue<Integer> evens = new LinkedList<>();
        Queue<Integer> odds = new LinkedList<>();

        while (!queue.isEmpty()) {
            Integer val = queue.remove();
            if (val % 2 == 0) {
                evens.add(val);
            } else {
                odds.add(val);
            }
        }

        while (!evens.isEmpty()) {
            queue.add(evens.remove());
        }

        while (!odds.isEmpty()) {
            queue.add(odds.remove());
        }
    }

    public int maxLength(Set<String> set) {
        int max = 0;

        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }

        return max;
    }

    public void removeEvenLength(Set<String> set) {
        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String s = it.next();
            if (s.length() % 2 == 0) {
                it.remove();
            }
        }
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set = new HashSet<>(list1);
        Set<Integer> common = new HashSet<>();

        for (Integer val : list2) {
            if (set.contains(val)) {
                common.add(val);
            }
        }

        return common.size();
    }

    public boolean isUnique(Map<String, String> map) {
        Set<String> seen = new HashSet<>();

        for (String value : map.values()) {
            if (!seen.add(value)) {
                return false;
            }
        }

        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value1 = entry.getValue();
            Integer value2 = map2.get(key);

            if (value2 != null && value1.equals(value2)) {
                result.put(key, value1);
            }
        }

        return result;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> reversed = new HashMap<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            reversed.put(entry.getValue(), entry.getKey());
        }

        return reversed;
    }

    public int rarest(Map<String, Integer> map) {
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Map is empty");
        }

        Map<Integer, Integer> freq = new HashMap<>();

        for (Integer value : map.values()) {
            Integer current = freq.get(value);
            if (current == null) {
                freq.put(value, Integer.valueOf(1));
            } else {
                freq.put(value, Integer.valueOf(current.intValue() + 1));
            }
        }

        int minCount = Integer.MAX_VALUE;
        int rarestValue = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int value = entry.getKey().intValue();
            int count = entry.getValue().intValue();

            if (count < minCount) {
                minCount = count;
                rarestValue = value;
            } else if (count == minCount && value < rarestValue) {
                rarestValue = value;
            }
        }

        return rarestValue;
    }

    public int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        int maxCount = 0;

        for (Integer num : list) {
            Integer current = freq.get(num);
            if (current == null) {
                freq.put(num, Integer.valueOf(1));
            } else {
                freq.put(num, Integer.valueOf(current.intValue() + 1));
            }

            int newCount = freq.get(num).intValue();
            if (newCount > maxCount) {
                maxCount = newCount;
            }
        }

        return maxCount;
    }
}
