package Slidingwindow;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Problem3169 {

    public static int countDaysSolution1(int days, int[][] meetings) {
        TreeMap<Integer, Integer> dayMap = new TreeMap<>();
        int prefixSum = 0, freeDays = 0, previousDay = days;
        boolean hasGap = false;

        for (int[] meeting : meetings) {
            // Set first day of meetings
            previousDay = Math.min(previousDay, meeting[0]);

            // Process start and end of meeting
            dayMap.put(meeting[0], dayMap.getOrDefault(meeting[0], 0) + 1);
            dayMap.put(
                    meeting[1] + 1,
                    dayMap.getOrDefault(meeting[1] + 1, 0) - 1
            );
        }

        // Add all days before the first day of meetings
        freeDays += (previousDay - 1);
        for (Map.Entry<Integer, Integer> day : dayMap.entrySet()) {
            int currentDay = day.getKey();

            // Add current range of days without a meeting
            if (prefixSum == 0) {
                freeDays += (currentDay - previousDay);
            }
            prefixSum += day.getValue();
            previousDay = currentDay;
        }

        // Add all days after the last day of meetings
        freeDays += days - previousDay + 1;
        return freeDays;
    }

    public static int countDaysSolution2(int days, int[][] meetings) {
        int freeDays = 0, start = 0, end = 0;
        int currentDay = 1;

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        for (int[] meeting : meetings) {
            start = meeting[0];
            end = meeting[1];

            if (currentDay < start)
                freeDays += start - currentDay;

            currentDay = Math.max(currentDay, end + 1);
        }
        if (currentDay <= days)
            freeDays += days - currentDay + 1;

        return freeDays;
    }

    public static void main(String[] args) {
        //https://leetcode.com/problems/count-days-without-meetings/?envType=daily-question&envId=2025-03-24
        // to see how IntelliJ IDEA suggests fixing it.

        int[][] meetings = {{5, 7}, {1, 3}, {9, 10}};
        int days = 10;
        System.out.println(countDaysSolution2(days, meetings));
    }

}