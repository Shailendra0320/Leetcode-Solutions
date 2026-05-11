# 2553. Separate the Digits in an Array

## Approach

Convert all numbers into a string and then convert every character into digit.

## Time Complexity

- O(n)

## Space Complexity

- O(n)

---

## Java Solution

```java
class Solution {
    public int[] separateDigits(int[] nums) {
        String s = "";

        for (int i = 0; i < nums.length; i++) {
            s = s + nums[i];
        }

        int[] result = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            result[i] = s.charAt(i) - '0';
        }

        return result;
    }
}
```

---

## C++ Solution

```cpp
class Solution {
public:
    vector<int> separateDigits(vector<int>& nums) {
        string s = "";

        for (int i = 0; i < nums.size(); i++) {
            s += to_string(nums[i]);
        }

        vector<int> result(s.length());

        for (int i = 0; i < s.length(); i++) {
            result[i] = s[i] - '0';
        }

        return result;
    }
};
```
