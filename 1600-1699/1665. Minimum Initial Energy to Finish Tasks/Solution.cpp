class Solution {
public:

    bool isPossible(vector<vector<int>>& tasks, int energy) {

        for (auto& task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            if (energy < minimum) {
                return false;
            }

            energy -= actual;
        }

        return true;
    }

    int minimumEffort(vector<vector<int>>& tasks) {

        sort(tasks.begin(), tasks.end(),
            [](vector<int>& a, vector<int>& b) {

                int diff1 = a[1] - a[0];
                int diff2 = b[1] - b[0];

                return diff2 < diff1;
            });

        int l = 0;
        int r = 1e9;

        int ans = INT_MAX;

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
};