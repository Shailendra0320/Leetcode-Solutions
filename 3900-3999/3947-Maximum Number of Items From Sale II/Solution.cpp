
//Approach-1 (Divisibility Sieve + Sorting + Greedy)
//T.C: O(n log n)
//S.C: O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maximumSaleItems(vector<vector<int>>& items, int budget) {
        int maxFactor = 0;
        long long minPrice = LLONG_MAX;

        for (auto& item : items) {
            maxFactor = max(maxFactor, item[0]);
            minPrice = min(minPrice, (long long)item[1]);
        }

        // Count frequencies of factors.
        vector<int> freq(maxFactor + 1, 0);

        for (auto& item : items) {
            freq[item[0]]++;
        }

        // Count item types whose factors are multiples of f.
        vector<int> total(maxFactor + 1, 0);

        for (int f = 1; f <= maxFactor; f++) {
            for (int multiple = f; multiple <= maxFactor;
                 multiple += f) {
                total[f] += freq[multiple];
            }
        }

        // Store useful bonus batches: {price, gain}.
        vector<pair<long long, long long>> batches;
        long long limit = 2LL * minPrice;

        for (auto& item : items) {
            int price = item[1];
            int gain = total[item[0]] - 1;

            if (gain > 0 && price < limit) {
                batches.push_back({price, gain});
            }
        }

        // Sort by ascending price.
        sort(batches.begin(), batches.end());

        long long answer = 0;
        long long remainingBudget = budget;

        // Buy the cheapest bonus copies first.
        for (auto& [price, gain] : batches) {
            long long take = min(gain, remainingBudget / price);

            answer += 2LL * take;
            remainingBudget -= take * price;
        }

        // Buy regular copies using the cheapest item.
        answer += remainingBudget / minPrice;

        return (int)answer;
    }
};


//Approach-2 (Divisibility Sieve + Min-Heap Greedy)
//T.C: O(n log n)
//S.C: O(n)

class Solution2 {
public:
    int maximumSaleItems(vector<vector<int>>& items, int budget) {
        int maxFactor = 0;
        long long minPrice = LLONG_MAX;

        for (auto& item : items) {
            maxFactor = max(maxFactor, item[0]);
            minPrice = min(minPrice, (long long)item[1]);
        }

        // Count factor frequencies.
        vector<int> freq(maxFactor + 1, 0);

        for (auto& item : items) {
            freq[item[0]]++;
        }

        // Count item types whose factors are multiples of f.
        vector<int> total(maxFactor + 1, 0);

        for (int f = 1; f <= maxFactor; f++) {
            for (int multiple = f; multiple <= maxFactor;
                 multiple += f) {
                total[f] += freq[multiple];
            }
        }

        // Min-heap ordered by price.
        priority_queue<
            pair<long long, long long>,
            vector<pair<long long, long long>>,
            greater<pair<long long, long long>>
        > minHeap;

        long long limit = 2LL * minPrice;

        for (auto& item : items) {
            int price = item[1];
            int gain = total[item[0]] - 1;

            if (gain > 0 && price < limit) {
                minHeap.push({price, gain});
            }
        }

        long long answer = 0;
        long long remainingBudget = budget;

        // Process cheapest bonus copies first.
        while (!minHeap.empty() && remainingBudget > 0) {
            auto [price, gain] = minHeap.top();
            minHeap.pop();

            long long take = min(gain, remainingBudget / price);

            answer += 2LL * take;
            remainingBudget -= take * price;
        }

        // Buy regular copies using the cheapest item.
        answer += remainingBudget / minPrice;

        return (int)answer;
    }
};
