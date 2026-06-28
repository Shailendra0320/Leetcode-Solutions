#Java Solution

##Approach 1—Sorting+Greedy

```java
//Approach-1 (Sorting + Greedy)
//T.C : O(n log n)
//S.C : O(1)

import java.util.Arrays;

class Solution {

  public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

    Arrays.sort(arr);

    arr[0] = 1;

    for (int i = 1; i < arr.length; i++) {

      arr[i] = Math.min(
          arr[i],
          arr[i - 1] + 1);
    }

    return arr[arr.length - 1];
  }}```

  ---

  ##Approach 2—

  Counting Sort/
  Frequency Array

  ```java
  // Approach-2 (Counting Sort / Frequency Array)
  // T.C : O(n)
  // S.C : O(n)

  class Solution {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        int n = arr.length;

        int[] frequency = new int[n + 1];

        for (int value : arr) {

            frequency[Math.min(value, n)]++;
        }

        int current = 0;

        for (int value = 1; value <= n; value++) {

            if (frequency[value] == 0) {

                current = Math.min(current, value - 1);

            } else {

                current = Math.min(current + frequency[value], value);
            }
        }

        return current;
    }
}```

---

##Approach 3—Sorting+

Construct New Array

```java
// Approach-3 (Sorting + Construct New Array)
// T.C : O(n log n)
// S.C : O(n)

import java.util.Arrays;

class Solution {

  public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        Arrays.sort(arr);

        int n = arr.length;

        int[] result = new int[n];

        result[0] = 1;

        for (int i = 1; i < n; i++) {

            if (arr[i] >= result[i - 1] + 1) {

                result[i] = result[i - 1] + 1;

            } else {

                result[i] = arr[i];
            }
        }

        return result[n - 1];
    }
}```
