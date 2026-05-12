class Solution {

    public boolean isPossible(int[][] tasks, int energy) {

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            if (energy < minimum) {
                return false;
            }

            energy -= actual;
        }

        return true;
    }

    public int minimumEffort(int[][] tasks) {

        Arrays.sort(tasks, (a, b) -> {
            int diff1 = a[1] - a[0];
            int diff2 = b[1] - b[0];

            return diff2 - diff1;
        });

        int l = 0;
        int r = (int)1e9;

        int ans = Integer.MAX_VALUE;

        while (l <= r) {

            int mid = l + (r - l) / 2;

            if (isPossible(tasks, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }
}


/*
class Solution {

    public int minimumEffort(int[][] tasks) {

        Arrays.sort(tasks, (a, b) ->
            (b[1] - b[0]) - (a[1] - a[0])
        );

        int energy = 0;
        int current = 0;

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            if (current < minimum) {

                energy += (minimum - current);

                current = minimum;
            }

            current -= actual;
        }

        return energy;
    }
}
*/