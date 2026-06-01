class Solution {
public:

    int minimumCost(vector<int>& prices) {

        sort(prices.begin(), prices.end());

        int totalCost = 0;

        int candyCount = 0;

        for (int position = prices.size() - 1; position >= 0; position--) {

            if (candyCount % 3 != 2) {

                totalCost += prices[position];
            }

            candyCount++;
        }

        return totalCost;
    }
};