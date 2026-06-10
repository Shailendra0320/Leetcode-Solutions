class Solution {
public:

    int n, maxLog;

    vector<vector<int>> sparseMax;
    vector<vector<int>> sparseMin;

    vector<int> logTable;

    void buildSparse(vector<int>& nums) {

        n = nums.size();

        maxLog = 32 - __builtin_clz(n) + 1;

        sparseMax.assign(n, vector<int>(maxLog));
        sparseMin.assign(n, vector<int>(maxLog));

        logTable.assign(n + 1, 0);

        for (int i = 2; i <= n; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }

        for (int i = 0; i < n; i++) {
            sparseMax[i][0] = nums[i];
            sparseMin[i][0] = nums[i];
        }

        for (int j = 1; j < maxLog; j++) {

            for (int i = 0; i + (1 << j) <= n; i++) {

                sparseMax[i][j] =
                    max(
                        sparseMax[i][j - 1],
                        sparseMax[i + (1 << (j - 1))][j - 1]
                    );

                sparseMin[i][j] =
                    min(
                        sparseMin[i][j - 1],
                        sparseMin[i + (1 << (j - 1))][j - 1]
                    );
            }
        }
    }

    int queryMax(int left, int right) {

        int k = logTable[right - left + 1];

        return max(
            sparseMax[left][k],
            sparseMax[right - (1 << k) + 1][k]
        );
    }

    int queryMin(int left, int right) {

        int k = logTable[right - left + 1];

        return min(
            sparseMin[left][k],
            sparseMin[right - (1 << k) + 1][k]
        );
    }

    long long maxTotalValue(vector<int>& nums, int k) {

        buildSparse(nums);

        priority_queue<vector<long long>> pq;

        for (int left = 0; left < n; left++) {

            long long value =
                queryMax(left, n - 1)
                - queryMin(left, n - 1);

            pq.push({value, left, n - 1});
        }

        long long answer = 0;

        for (int operation = 0; operation < k; operation++) {

            auto current = pq.top();
            pq.pop();

            long long value = current[0];
            int left = current[1];
            int right = current[2];

            answer += value;

            if (right > left) {

                long long nextValue =
                    queryMax(left, right - 1)
                    - queryMin(left, right - 1);

                pq.push({nextValue, left, right - 1});
            }
        }

        return answer;
    }
};