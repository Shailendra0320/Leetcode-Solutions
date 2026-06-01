class Solution {
  public int minimumCost(int[] prices) {
    Arrays.sort(prices);

    int totalCost = 0;
    int candyCount = 0;

    for (int position = prices.length - 1; position >= 0; position--) {
      if (candyCount % 3 != 2) {
        totalCost += prices[position];
      }
      candyCount++;
    }

    return totalCost;
  }
}