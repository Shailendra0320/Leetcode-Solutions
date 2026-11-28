
import java.util.*;

//Approach-1 (Divisibility Sieve + Sorting + Greedy)
//T.C: O(n log n)
//S.C: O(n)

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequencies of factors.
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count item types whose factors are multiples of f.
        int[] total = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int multiple = f; multiple <= maxFactor;
                 multiple += f) {
                total[f] += freq[multiple];
            }
        }

        // Store useful bonus batches: [price, gain].
        List<long[]> batches = new ArrayList<>();
        long limit = 2L * minPrice;

        for (int[] item : items) {
            int price = item[1];
            int gain = total[item[0]] - 1;

            if (gain > 0 && price < limit) {
                batches.add(new long[]{price, gain});
            }
        }

        // Sort by ascending price.
        batches.sort((a, b) -> Long.compare(a[0], b[0]));

        long answer = 0;
        long remainingBudget = budget;

        // Buy the cheapest bonus copies first.
        for (long[] batch : batches) {
            long price = batch[0];
            long gain = batch[1];

            long take = Math.min(gain, remainingBudget / price);

            answer += 2L * take;
            remainingBudget -= take * price;
        }

        // Buy regular copies using the cheapest item.
        answer += remainingBudget / minPrice;

        return (int) answer;
    }
}


//Approach-2 (Divisibility Sieve + Min-Heap Greedy)
//T.C: O(n log n)
//S.C: O(n)

class Solution2 {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count factor frequencies.
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Calculate the number of divisible item types.
        int[] total = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int multiple = f; multiple <= maxFactor;
                 multiple += f) {
                total[f] += freq[multiple];
            }
        }

        // Min-heap ordered by price.
        PriorityQueue<long[]> minHeap = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        long limit = 2L * minPrice;

        for (int[] item : items) {
            int price = item[1];
            int gain = total[item[0]] - 1;

            if (gain > 0 && price < limit) {
                minHeap.offer(new long[]{price, gain});
            }
        }

        long answer = 0;
        long remainingBudget = budget;

        // Process cheapest bonus batches first.
        while (!minHeap.isEmpty() && remainingBudget > 0) {
            long[] batch = minHeap.poll();

            long price = batch[0];
            long gain = batch[1];

            long take = Math.min(gain, remainingBudget / price);

            answer += 2L * take;
            remainingBudget -= take * price;
        }

        // Buy regular copies using the minimum price.
        answer += remainingBudget / minPrice;

        return (int) answer;
    }
}
