package Slidingwindow;

import java.util.*;


public class Problem3318 {

    public static int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < res.length; i++) {
            int sum = 0;
            Set<Integer> set = new HashSet<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
                set.add(nums[j]);
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            }

            if (set.size() < x) res[i] = sum;
            else {
                PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
                    if (Objects.equals(map.get(a), map.get(b))) return b - a;
                    return map.get(b) - map.get(a);
                });
                pq.addAll(set);
                int ct = x;
                while (ct-- > 0) {
                    int top = pq.remove();
                    res[i] += (top * map.get(top));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        //https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/description/
        int[] nums = {1, 1, 2, 2, 3, 4, 2, 3};
        int x = 2, k = 6;
        System.out.println(Arrays.toString(findXSum(nums, k, x)));
    }
}