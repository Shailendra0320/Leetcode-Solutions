import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        final int LEFT_BLOCK  = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        final int MID_BLOCK   = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        final int RIGHT_BLOCK = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        Map<Integer, Integer> rowToReservedMask = new HashMap<>();

        for (int[] reservation : reservedSeats) {
            int row = reservation[0];
            int seat = reservation[1];

            int currentMask = rowToReservedMask.getOrDefault(row, 0);
            currentMask |= (1 << seat);
            rowToReservedMask.put(row, currentMask);
        }

        long totalGroups = (long) (n - rowToReservedMask.size()) * 2;

        for (int reservedMask : rowToReservedMask.values()) {
            boolean isLeftFree = (reservedMask & LEFT_BLOCK) == 0;
            boolean isMidFree = (reservedMask & MID_BLOCK) == 0;
            boolean isRightFree = (reservedMask & RIGHT_BLOCK) == 0;

            if (isLeftFree && isRightFree) {
                totalGroups += 2;
            } else if (isLeftFree || isMidFree || isRightFree) {
                totalGroups += 1;
            }
        }

        return (int) totalGroups;
    }
}
