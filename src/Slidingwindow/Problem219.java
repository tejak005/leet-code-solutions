package Slidingwindow;

import java.util.HashMap;

public class Problem219 {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int lastSeenIndex = hm.getOrDefault(nums[i], -1);
            if (lastSeenIndex != -1 && (i - lastSeenIndex) <= k)
                return true;

            hm.put(nums[i], i);

        }
        return false;
    }

    public static void main(String[] args) {
        // https://leetcode.com/problems/contains-duplicate-ii/description/
        int[] nums = {1, 2, 3, 1, 2, 3};
        int k = 2;
        System.out.println(containsNearbyDuplicate(nums, k));
        int[] nums1 = {1, 0, 1, 1};
        int k1 = 1;
        System.out.println(containsNearbyDuplicate(nums1, k1));
        int[] nums2 = {1, 2, 3, 1};
        int k2 = 3;
        System.out.println(containsNearbyDuplicate(nums2, k2));
    }
}