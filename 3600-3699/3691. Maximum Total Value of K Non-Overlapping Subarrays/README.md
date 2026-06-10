# Profiles

## GitHub

⭐ GitHub Repository:

https://github.com/Shailendra0320

---

## LeetCode Profiles

🔥 Main Profile:

https://leetcode.com/u/ShailendraLeetcode03/

🚀 Alternate Profile:

https://leetcode.com/u/Shailu03/

---

# 3691. Maximum Total Value of K Non-Overlapping Subarrays

# Intuition

For every subarray:

```text

Value = Maximum Element - Minimum Element

```

We need to repeatedly choose the maximum possible value exactly:

```text

k times

```

A brute force approach would require:

```text

O(n²)

```

subarray generation and would be too slow.

---

# Key Idea

We need two things:

### Fast Range Maximum Query

```text

max(nums[l...r])

```

### Fast Range Minimum Query

```text

min(nums[l...r])

```

These are answered using:

```text

Sparse Table

```

in:

```text

O(1)

```

per query.

---

# Data Structures Used

## Sparse Table

Stores:

```text

Range Maximums

Range Minimums

```

---

## Priority Queue

Stores:

```text

[value, left, right]

```

where:

```text

value =

maximum(left,right)

-

minimum(left,right)

```

The largest value always remains at the top.

---

# Sparse Table Construction

```text

nums



[5,2,9,1,7]



         ▼



Level 0



5 2 9 1 7



         ▼



Level 1



max/min for length 2



         ▼



Level 2



max/min for length 4



         ▼



Level 3



max/min for length 8

```

---

# Overall Flow

```text

Build Sparse Table



          │

          ▼



Generate Initial Intervals



[l , n-1]



          │

          ▼



Compute



max - min



          │

          ▼



Push Into Max Heap



          │

          ▼



Take Best Interval



          │

          ▼



Add Value To Answer



          │

          ▼



Shrink Interval



(l , r-1)



          │

          ▼



Push Again



          │

          ▼



Repeat k Times

```

---

# Visualization

Input

```text

nums = [5,2,9,1]

k = 3

```

---

Initial Intervals

```text

[0,3]

[1,3]

[2,3]

[3,3]

```

---

Values

```text

[0,3]



max = 9

min = 1



value = 8





[1,3]



max = 9

min = 1



value = 8





[2,3]



max = 9

min = 1



value = 8





[3,3]



max = 1

min = 1



value = 0

```

---

Priority Queue

```text

          8

         / \

        8   8

             \

              0

```

---

Take Largest

```text

Answer += 8

```

Shrink

```text

[0,3]



↓



[0,2]

```

Push back.

Repeat until:

```text

k operations

```

finish.

---

# Detailed Dry Run

Input

```text

nums = [5,2,9,1]



k = 2

```

---

Iteration 1

```text

Best Interval



[0,3]



max = 9



min = 1



value = 8

```

Answer:

```text

8

```

Push:

```text

[0,2]

```

---

Iteration 2

```text

Best Interval



[1,3]



max = 9



min = 1



value = 8

```

Answer:

```text

16

```

---

Final Answer

```text

16

```

---

# Complexity Analysis

## Sparse Table Build

```text

O(n log n)

```

---

## Range Queries

```text

O(1)

```

---

## Priority Queue Operations

Each operation:

```text

O(log n)

```

Performed:

```text

k times

```

Total:

```text

O(k log n)

```

---

# Overall Complexity

### Time Complexity

```text

O(n log n + k log n)

```

### Space Complexity

```text

O(n log n)

```

---

# Java Solution

```java





class Solution {



    int n, maxLog;



    int[][] fMax, fMin;



    int[] lg;







    private void buildSparse(int[] data) {



        n = data.length;



        maxLog = 32 - Integer.numberOfLeadingZeros(n) + 1;



        fMax = new int[n][maxLog];



        fMin = new int[n][maxLog];



        lg = new int[n + 1];







        for (int i = 2; i <= n; i++)



            lg[i] = lg[i >> 1] + 1;







        for (int i = 0; i < n; i++) {



            fMax[i][0] = data[i];



            fMin[i][0] = data[i];



        }







        for (int j = 1; j < maxLog; j++) {



            for (int i = 0; i <= n - (1 << j); i++) {



                fMax[i][j] = Math.max(fMax[i][j - 1], fMax[i + (1 << (j - 1))][j - 1]);



                fMin[i][j] = Math.min(fMin[i][j - 1], fMin[i + (1 << (j - 1))][j - 1]);



            }



        }



    }







    private int qMax(int l, int r) {



        int k = lg[r - l + 1];



        return Math.max(fMax[l][k], fMax[r - (1 << k) + 1][k]);



    }







    private int qMin(int l, int r) {



        int k = lg[r - l + 1];



        return Math.min(fMin[l][k], fMin[r - (1 << k) + 1][k]);



    }







    public long maxTotalValue(int[] nums, int k) {



        buildSparse(nums);







        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));







        for (int l = 0; l < n; l++) {



            long val = qMax(l, n - 1) - qMin(l, n - 1);



            pq.offer(new long[]{val, l, n - 1});



        }







        long ans = 0;







        for (int i = 0; i < k; i++) {



            long[] curr = pq.poll();



            long val = curr[0];



            int l = (int) curr[1];



            int r = (int) curr[2];



            ans += val;



            if (r > l) {



                long nextVal = qMax(l, r - 1) - qMin(l, r - 1);



                pq.offer(new long[]{nextVal, l, r - 1});



            }



        }







        return ans;



    }



}



```

---

# C++ Solution

```cpp

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

```
