package Slidingwindow;

import java.util.HashMap;

public class Problem594 {

    public static int findLHS(int[] numbs) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : numbs) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Integer key : hm.keySet()) {
            if (hm.containsKey(key + 1)) {
                res = Math.max(res, hm.get(key) + hm.get(key + 1));
            }
        }

        return res;
    }

    //https://leetcode.com/problems/longest-harmonious-subsequence/submissions/1587472289/
    public static void main(String[] args) {
        int[] numbs = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println(findLHS(numbs));
    }
}