// Approach-1 (Brute Force: Check Every Log for Each Query)
// T.C: O(q * m)
// S.C: O(min(n, m))

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> countServers(int n, vector<vector<int>>& logs, int x,
                             vector<int>& queries) {
        int q = queries.size();
        vector<int> answer(q);

        for (int i = 0; i < q; i++) {
            int start = queries[i] - x;
            int end = queries[i];

            unordered_set<int> activeServers;

            for (auto& log : logs) {
                int server = log[0];
                int time = log[1];

                if (time >= start && time <= end) {
                    activeServers.insert(server);
                }
            }

            answer[i] = n - activeServers.size();
        }

        return answer;
    }
};

// Approach-2 (Offline Queries + Sorting + Sliding Window)
// T.C: O(m log m + q log q)
// S.C: O(n + m + q)

class Solution2 {
public:
    vector<int> countServers(int n, vector<vector<int>>& logs, int x,
                             vector<int>& queries) {
        int m = logs.size();
        int q = queries.size();

        vector<vector<int>> sortedLogs = logs;

        sort(sortedLogs.begin(), sortedLogs.end(),
             [](const vector<int>& a, const vector<int>& b) {
                 return a[1] < b[1];
             });

        vector<pair<int, int>> sortedQueries;

        for (int i = 0; i < q; i++) {
            sortedQueries.push_back({queries[i], i});
        }

        sort(sortedQueries.begin(), sortedQueries.end());

        vector<int> frequency(n + 1, 0);
        vector<int> answer(q);

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (auto& query : sortedQueries) {
            int time = query.first;
            int index = query.second;

            int start = time - x;

            while (right < m && sortedLogs[right][1] <= time) {
                int server = sortedLogs[right][0];

                if (frequency[server] == 0) {
                    activeServers++;
                }

                frequency[server]++;
                right++;
            }

            while (left < right && sortedLogs[left][1] < start) {
                int server = sortedLogs[left][0];

                frequency[server]--;

                if (frequency[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            answer[index] = n - activeServers;
        }

        return answer;
    }
};