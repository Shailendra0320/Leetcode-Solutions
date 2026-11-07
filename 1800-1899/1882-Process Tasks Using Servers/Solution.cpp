// Approach-1 (Brute Force: Linear Search for Available Server)
// T.C: O(n * m)
// S.C: O(m)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> assignTasks(vector<int>& servers, vector<int>& tasks) {
        int n = tasks.size();
        int m = servers.size();

        vector<long long> availableAt(m, 0);
        vector<int> answer(n);

        for (int i = 0; i < n; i++) {
            long long startTime = i;
            int chosen = -1;

            for (int s = 0; s < m; s++) {
                if (availableAt[s] <= startTime) {
                    if (chosen == -1 ||
                        servers[s] < servers[chosen] ||
                        (servers[s] == servers[chosen] && s < chosen)) {
                        chosen = s;
                    }
                }
            }

            if (chosen == -1) {
                startTime = *min_element(
                    availableAt.begin(), availableAt.end()
                );

                for (int s = 0; s < m; s++) {
                    if (availableAt[s] <= startTime) {
                        if (chosen == -1 ||
                            servers[s] < servers[chosen] ||
                            (servers[s] == servers[chosen] && s < chosen)) {
                            chosen = s;
                        }
                    }
                }
            }

            answer[i] = chosen;
            availableAt[chosen] = startTime + tasks[i];
        }

        return answer;
    }
};

// Approach-2 (Two Min Heaps: Available and Busy Servers)
// T.C: O((n + m) * log m)
// S.C: O(m)

class Solution2 {
public:
    vector<int> assignTasks(vector<int>& servers, vector<int>& tasks) {
        int n = tasks.size();
        int m = servers.size();

        priority_queue<
            pair<int, int>,
            vector<pair<int, int>>,
            greater<pair<int, int>>
        > available;

        priority_queue<
            tuple<long long, int, int>,
            vector<tuple<long long, int, int>>,
            greater<tuple<long long, int, int>>
        > busy;

        for (int i = 0; i < m; i++) {
            available.push({servers[i], i});
        }

        vector<int> answer(n);
        long long currentTime = 0;

        for (int i = 0; i < n; i++) {
            currentTime = max(currentTime, (long long)i);

            while (!busy.empty() && get<0>(busy.top()) <= currentTime) {
                auto [finishTime, weight, serverIndex] = busy.top();
                busy.pop();

                available.push({weight, serverIndex});
            }

            if (available.empty()) {
                currentTime = get<0>(busy.top());

                while (!busy.empty() && get<0>(busy.top()) <= currentTime) {
                    auto [finishTime, weight, serverIndex] = busy.top();
                    busy.pop();

                    available.push({weight, serverIndex});
                }
            }

            auto [weight, serverIndex] = available.top();
            available.pop();

            answer[i] = serverIndex;

            busy.push({
                currentTime + tasks[i],
                weight,
                serverIndex
            });
        }

        return answer;
    }
};