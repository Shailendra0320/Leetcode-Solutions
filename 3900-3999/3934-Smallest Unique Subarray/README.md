# 3934. Smallest Unique Subarray — Suffix Array, LCP & Rolling Hash

<p align="center">
  <a href="https://github.com/Shailendra0320">
    <img src="https://img.shields.io/badge/GitHub-Shailendra0320-black?logo=github" alt="GitHub Profile" />
  </a>
  <a href="https://leetcode.com/u/Shailu03/">
    <img src="https://img.shields.io/badge/LeetCode-Main-orange?logo=leetcode" alt="LeetCode Main Profile" />
  </a>
  <a href="https://leetcode.com/u/ShailendraLeetcode03/">
    <img src="https://img.shields.io/badge/LeetCode-Alternate-yellow?logo=leetcode" alt="LeetCode Alternate Profile" />
  </a>
  <a href="https://shailendra0320.github.io/shailendra-portfolio/">
    <img src="https://img.shields.io/badge/Portfolio-Visit-blue?logo=googlechrome" alt="Portfolio Website" />
  </a>
</p>

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)
![Topic](https://img.shields.io/badge/Topic-Array%20%7C%20String%20Algorithms-blue)
![Complexity](https://img.shields.io/badge/Target-O(n%20log%20n)-success)

---

## Problem Statement

You are given an integer array `nums`.

Find the minimum length of a subarray that is not identical to any other subarray in `nums`.

Return the minimum possible length of such a subarray.

Two subarrays are identical if they have the same length and the same elements in corresponding positions.

**Problem link:** [3934. Smallest Unique Subarray](https://leetcode.com/problems/smallest-unique-subarray/)

## Examples

### Example 1

```text
Input:  nums = [3,3,3]
Output: 3
```

Explanation:

- Length 1: `[3]` occurs three times.
- Length 2: `[3,3]` occurs twice.
- Length 3: `[3,3,3]` occurs once.

The smallest unique subarray has length `3`.

### Example 2

```text
Input:  nums = [2,1,2,3,3]
Output: 1
```

Explanation:

The value `[1]` appears exactly once, so the answer is `1`.

### Example 3

```text
Input:  nums = [1,1,2,2,1]
Output: 2
```

Explanation:

Every length-1 subarray is repeated. At length 2, `[1,1]`, `[1,2]`, `[2,2]`, and `[2,1]` each occur once. Therefore, the answer is `2`.

## Constraints

```text
1 <= nums.length <= 100000
1 <= nums[i] <= 100000
```

---

## Key Observations

1. A subarray is identified by its **length and ordered sequence of values**. Sorting the values or using only a frequency count does not preserve subarray identity.
2. For a fixed length `len`, there are `n - len + 1` windows. The length is valid if at least one of those windows occurs exactly once.
3. If a unique subarray of length `L` exists, it can be extended to a longer subarray while keeping that unique subarray inside it. The extended subarray cannot have another occurrence, because such an occurrence would also contain a second occurrence of the original unique subarray.
4. Consequently, feasibility is monotonic: once a length is valid, every larger length is valid. This allows binary search on the answer.
5. A rolling hash can represent each fixed-length window in constant time after preprocessing. However, a single hash is not an exact representation: collisions are possible. This README uses two independent modular hashes for the fast approach and also presents a deterministic suffix-array approach.

## What Does “Unique” Mean?

For a candidate length `L`, examine every window:

```text
nums[0 ... L-1]
nums[1 ... L]
nums[2 ... L+1]
...
nums[n-L ... n-1]
```

A window is unique if its exact sequence appears at only one starting index.

For example:

```text
nums = [1, 1, 2, 2, 1]
L = 1

[1] -> 3 occurrences
[2] -> 2 occurrences

No unique window of length 1.
```

For length 2:

```text
[1,1] -> 1 occurrence
[1,2] -> 1 occurrence
[2,2] -> 1 occurrence
[2,1] -> 1 occurrence

A unique window exists.
```

Therefore, the minimum length is `2`.

---

# Approach 1 — Suffix Array + LCP (Deterministic)

**Recommended when exact collision-free comparison is required.**

## Intuition

A suffix starting at index `i` is:

```text
nums[i], nums[i+1], ..., nums[n-1]
```

A prefix of this suffix is a subarray beginning at `i`.

We want the shortest prefix of some suffix that does not occur as a prefix of any other suffix.

A suffix array sorts all suffixes lexicographically. In sorted order, the suffixes most similar to a particular suffix are its immediate neighbors. Therefore, the maximum common-prefix length between a suffix and any other suffix is the maximum of its LCP with its previous and next suffix in suffix-array order.

Let:

- `SA[k]` be the starting index of the k-th suffix in sorted order.
- `LCP[k]` be the common-prefix length of suffixes `SA[k]` and `SA[k-1]`.
- `rank[i]` be the sorted position of suffix `i`.

For suffix `i`, its longest common prefix with another suffix is:

```text
max(LCP[rank[i]], LCP[rank[i] + 1])
```

The missing boundary values are treated as zero.

If that maximum is `common`, then the shortest prefix of suffix `i` that is not shared with another suffix has length:

```text
common + 1
```

The answer is the minimum such value over all suffixes.

## Why Only Adjacent Suffixes?

In lexicographic order, all suffixes sharing a given prefix form a contiguous interval. If another suffix shares a prefix with the current suffix, at least one of the nearest suffixes in the relevant direction shares at least that much prefix. Thus the maximum LCP is attained by a lexicographic neighbor.

## Algorithm

1. Build the suffix array of `nums`.
2. Build the inverse rank array.
3. Use Kasai's algorithm to compute adjacent LCP values in linear time.
4. For every starting index, obtain the larger of its LCP with the previous and next suffix.
5. The shortest unique prefix at that starting index is `maxLcp + 1`.
6. Return the minimum over all starting indices.

## Pseudocode

```text
build suffix array SA
build rank[SA[position]] = position
build LCP using Kasai's algorithm

answer = n

for each original suffix start i:
    p = rank[i]
    common = 0

    if p > 0:
        common = max(common, LCP[p])

    if p + 1 < n:
        common = max(common, LCP[p + 1])

    answer = min(answer, common + 1)

return answer
```

## Complexity

The doubling suffix-array construction below sorts suffixes in each round.

| Operation | Complexity |
|---|---:|
| Suffix array (comparison sort per doubling round) | `O(n log² n)` |
| Inverse rank | `O(n)` |
| Kasai LCP | `O(n)` |
| Final scan | `O(n)` |
| Auxiliary space | `O(n)` |

**Overall:** `O(n log² n)` time and `O(n)` space for the implementations below. A counting-sort suffix-array implementation can improve suffix-array construction to `O(n log n)`.

## Java — Approach 1

```java
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        Integer[] sa = new Integer[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = nums[i];
        }

        for (int len = 1; len < n; len <<= 1) {
            final int step = len;
            final int[] currentRank = rank;

            Arrays.sort(sa, (a, b) -> {
                if (currentRank[a] != currentRank[b]) {
                    return Integer.compare(currentRank[a], currentRank[b]);
                }

                int ra = a + step < n ? currentRank[a + step] : -1;
                int rb = b + step < n ? currentRank[b + step] : -1;
                return Integer.compare(ra, rb);
            });

            int[] nextRank = new int[n];
            nextRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                boolean different = rank[a] != rank[b];

                int a2 = a + len < n ? rank[a + len] : -1;
                int b2 = b + len < n ? rank[b + len] : -1;

                if (a2 != b2) {
                    different = true;
                }

                nextRank[b] = nextRank[a] + (different ? 1 : 0);
            }

            rank = nextRank;

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }

        int[] position = new int[n];
        for (int i = 0; i < n; i++) {
            position[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int common = 0;

        for (int i = 0; i < n; i++) {
            int p = position[i];

            if (p == 0) {
                common = 0;
                continue;
            }

            int j = sa[p - 1];

            while (i + common < n && j + common < n
                    && nums[i + common] == nums[j + common]) {
                common++;
            }

            lcp[p] = common;

            if (common > 0) {
                common--;
            }
        }

        int answer = n;

        for (int i = 0; i < n; i++) {
            int p = position[i];
            int best = 0;

            if (p > 0) {
                best = Math.max(best, lcp[p]);
            }

            if (p + 1 < n) {
                best = Math.max(best, lcp[p + 1]);
            }

            answer = Math.min(answer, best + 1);
        }

        return answer;
    }
}
```

## C++ — Approach 1

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();

        vector<int> sa(n), rank(n), nextRank(n);
        iota(sa.begin(), sa.end(), 0);

        for (int i = 0; i < n; i++) {
            rank[i] = nums[i];
        }

        for (int len = 1; len < n; len <<= 1) {
            sort(sa.begin(), sa.end(), [&](int a, int b) {
                if (rank[a] != rank[b]) {
                    return rank[a] < rank[b];
                }

                int ra = a + len < n ? rank[a + len] : -1;
                int rb = b + len < n ? rank[b + len] : -1;

                return ra < rb;
            });

            nextRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                pair<int, int> x = {
                    rank[a], a + len < n ? rank[a + len] : -1
                };

                pair<int, int> y = {
                    rank[b], b + len < n ? rank[b + len] : -1
                };

                nextRank[b] = nextRank[a] + (x != y);
            }

            rank.swap(nextRank);

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }

        vector<int> position(n), lcp(n);

        for (int i = 0; i < n; i++) {
            position[sa[i]] = i;
        }

        int common = 0;

        for (int i = 0; i < n; i++) {
            int p = position[i];

            if (p == 0) {
                common = 0;
                continue;
            }

            int j = sa[p - 1];

            while (i + common < n && j + common < n &&
                   nums[i + common] == nums[j + common]) {
                common++;
            }

            lcp[p] = common;

            if (common > 0) {
                common--;
            }
        }

        int answer = n;

        for (int i = 0; i < n; i++) {
            int p = position[i];
            int best = 0;

            if (p > 0) {
                best = max(best, lcp[p]);
            }

            if (p + 1 < n) {
                best = max(best, lcp[p + 1]);
            }

            answer = min(answer, best + 1);
        }

        return answer;
    }
};
```

## Python — Approach 1

```python
from typing import List


class Solution:
    def smallestUniqueSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        sa = list(range(n))
        rank = nums[:]
        length = 1

        while length < n:
            sa.sort(
                key=lambda i: (
                    rank[i],
                    rank[i + length] if i + length < n else -1
                )
            )

            new_rank = [0] * n

            for k in range(1, n):
                a = sa[k - 1]
                b = sa[k]

                prev_pair = (
                    rank[a],
                    rank[a + length] if a + length < n else -1
                )
                curr_pair = (
                    rank[b],
                    rank[b + length] if b + length < n else -1
                )

                new_rank[b] = new_rank[a] + (prev_pair != curr_pair)

            rank = new_rank

            if rank[sa[-1]] == n - 1:
                break

            length <<= 1

        position = [0] * n

        for k, start in enumerate(sa):
            position[start] = k

        lcp = [0] * n
        common = 0

        for i in range(n):
            p = position[i]

            if p == 0:
                continue

            j = sa[p - 1]

            while (
                i + common < n
                and j + common < n
                and nums[i + common] == nums[j + common]
            ):
                common += 1

            lcp[p] = common

            if common:
                common -= 1

        answer = n

        for i in range(n):
            p = position[i]
            best = 0

            if p > 0:
                best = max(best, lcp[p])

            if p + 1 < n:
                best = max(best, lcp[p + 1])

            answer = min(answer, best + 1)

        return answer
```

---

# Approach 2 — Binary Search + Double Rolling Hash

## Intuition

Instead of constructing a suffix array, test whether a particular length `L` contains at least one unique window.

For a fixed length:

1. Compute the rolling hash of the first window.
2. Slide the window one position at a time.
3. Count how many times each hash pair occurs.
4. If any pair has frequency exactly one, the length is feasible.

Because feasibility is monotonic, binary search finds the minimum feasible length.

## Rolling Hash

For a sequence:

```text
[a, b, c]
```

A polynomial hash can be represented as:

```text
a * base² + b * base + c
```

When the window slides, remove the outgoing element's contribution and append the incoming element.

To reduce collision probability, this implementation calculates two hashes with different bases and moduli. A pair of hashes is used as the map key.

**Important:** Double hashing is highly collision-resistant in practice, but it is still probabilistic. It is not a mathematical guarantee of exact equality. Use Approach 1 when deterministic collision-free behavior is required.

## Binary Search

Let `check(L)` mean that at least one subarray of length `L` has a hash frequency of one.

```text
left = 1
right = n

while left < right:
    mid = (left + right) // 2

    if check(mid):
        right = mid
    else:
        left = mid + 1

return left
```

## Complexity

- Each feasibility check processes `O(n)` windows.
- Binary search performs `O(log n)` checks.
- Each hash update takes constant time.

**Time:** `O(n log n)` expected.

**Space:** `O(n)` for the frequency map and prefix hashes.

## Java — Approach 2

```java
import java.util.*;

class Solution2 {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 100_019L;
    private static final long BASE2 = 100_043L;

    private long[] prefix1, prefix2;
    private long[] power1, power2;

    private void buildHash(int[] nums) {
        int n = nums.length;

        prefix1 = new long[n + 1];
        prefix2 = new long[n + 1];
        power1 = new long[n + 1];
        power2 = new long[n + 1];

        power1[0] = 1;
        power2[0] = 1;

        for (int i = 0; i < n; i++) {
            power1[i + 1] = power1[i] * BASE1 % MOD1;
            power2[i + 1] = power2[i] * BASE2 % MOD2;

            long value = nums[i] + 1L;

            prefix1[i + 1] =
                (prefix1[i] * BASE1 + value) % MOD1;

            prefix2[i + 1] =
                (prefix2[i] * BASE2 + value) % MOD2;
        }
    }

    private long getHash(long[] prefix, long[] power,
                         int left, int right, long mod) {
        int len = right - left;

        return (prefix[right] -
                prefix[left] * power[len] % mod + mod) % mod;
    }

    private boolean hasUnique(int[] nums, int len) {
        Map<Long, Integer> frequency = new HashMap<>();
        int n = nums.length;

        for (int left = 0; left + len <= n; left++) {
            int right = left + len;

            long h1 = getHash(prefix1, power1, left, right, MOD1);
            long h2 = getHash(prefix2, power2, left, right, MOD2);

            long key = h1 * MOD2 + h2;
            frequency.put(key, frequency.getOrDefault(key, 0) + 1);
        }

        for (int count : frequency.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }

    public int smallestUniqueSubarray(int[] nums) {
        buildHash(nums);

        int left = 1;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (hasUnique(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
```

## C++ — Approach 2

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution2 {
    using ll = long long;

    static constexpr ll MOD1 = 1000000007LL;
    static constexpr ll MOD2 = 1000000009LL;
    static constexpr ll BASE1 = 100019LL;
    static constexpr ll BASE2 = 100043LL;

    vector<ll> p1, p2, pow1, pow2;

    void buildHash(const vector<int>& nums) {
        int n = nums.size();

        p1.assign(n + 1, 0);
        p2.assign(n + 1, 0);
        pow1.assign(n + 1, 1);
        pow2.assign(n + 1, 1);

        for (int i = 0; i < n; i++) {
            pow1[i + 1] = pow1[i] * BASE1 % MOD1;
            pow2[i + 1] = pow2[i] * BASE2 % MOD2;

            ll value = nums[i] + 1LL;

            p1[i + 1] = (p1[i] * BASE1 + value) % MOD1;
            p2[i + 1] = (p2[i] * BASE2 + value) % MOD2;
        }
    }

    ll getHash(const vector<ll>& pref, const vector<ll>& power,
               int left, int right, ll mod) {
        int len = right - left;

        return (pref[right] -
                pref[left] * power[len] % mod + mod) % mod;
    }

    bool hasUnique(const vector<int>& nums, int len) {
        unordered_map<ll, int> frequency;
        int n = nums.size();

        for (int left = 0; left + len <= n; left++) {
            int right = left + len;

            ll h1 = getHash(p1, pow1, left, right, MOD1);
            ll h2 = getHash(p2, pow2, left, right, MOD2);

            ll key = h1 * MOD2 + h2;
            frequency[key]++;
        }

        for (auto& [hashValue, count] : frequency) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }

public:
    int smallestUniqueSubarray(vector<int>& nums) {
        buildHash(nums);

        int left = 1;
        int right = nums.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (hasUnique(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
};
```

## Python — Approach 2

```python
from typing import List


class Solution2:
    MOD1 = 1_000_000_007
    MOD2 = 1_000_000_009
    BASE1 = 100_019
    BASE2 = 100_043

    def smallestUniqueSubarray(self, nums: List[int]) -> int:
        n = len(nums)

        prefix1 = [0] * (n + 1)
        prefix2 = [0] * (n + 1)
        power1 = [1] * (n + 1)
        power2 = [1] * (n + 1)

        for i, value in enumerate(nums):
            power1[i + 1] = power1[i] * self.BASE1 % self.MOD1
            power2[i + 1] = power2[i] * self.BASE2 % self.MOD2

            x = value + 1

            prefix1[i + 1] = (
                prefix1[i] * self.BASE1 + x
            ) % self.MOD1

            prefix2[i + 1] = (
                prefix2[i] * self.BASE2 + x
            ) % self.MOD2

        def get_hash(prefix, power, left, right, mod):
            length = right - left

            return (
                prefix[right] -
                prefix[left] * power[length]
            ) % mod

        def has_unique(length):
            frequency = {}

            for left in range(n - length + 1):
                right = left + length

                h1 = get_hash(
                    prefix1, power1, left, right, self.MOD1
                )
                h2 = get_hash(
                    prefix2, power2, left, right, self.MOD2
                )

                key = (h1, h2)
                frequency[key] = frequency.get(key, 0) + 1

            return any(count == 1 for count in frequency.values())

        left, right = 1, n

        while left < right:
            mid = (left + right) // 2

            if has_unique(mid):
                right = mid
            else:
                left = mid + 1

        return left
```

---

# Dry Run

Consider:

```text
nums = [1, 1, 2, 2, 1]
```

## Check length 1

| Starting index | Subarray | Frequency |
|---:|---|---:|
| 0 | `[1]` | 3 |
| 1 | `[1]` | 3 |
| 2 | `[2]` | 2 |
| 3 | `[2]` | 2 |
| 4 | `[1]` | 3 |

No subarray of length 1 is unique.

Therefore:

```text
check(1) = false
```

## Check length 2

| Starting index | Subarray | Frequency |
|---:|---|---:|
| 0 | `[1,1]` | 1 |
| 1 | `[1,2]` | 1 |
| 2 | `[2,2]` | 1 |
| 3 | `[2,1]` | 1 |

A unique subarray exists.

Therefore:

```text
check(2) = true
```

The minimum valid length is:

```text
Answer = 2
```

---

# Correctness Proof

## Approach 1 — Suffix Array + LCP

For a suffix beginning at index `i`, let `M(i)` be its maximum LCP with any other suffix.

The lexicographic-neighbor property of suffix arrays guarantees that `M(i)` is the maximum of the LCP values with the previous and next suffix in sorted order.

Every prefix of length at most `M(i)` is shared by another suffix, so none of those prefixes is unique.

The prefix of length `M(i) + 1` differs from every other suffix at some position within that prefix. It therefore occurs only at index `i`.

Thus, the shortest unique subarray beginning at `i` has length `M(i) + 1`. Taking the minimum over all starting indices returns the globally shortest unique subarray.

## Approach 2 — Binary Search + Double Rolling Hash

For a fixed length, the frequency map counts every window representation. If a hash pair has frequency one, the corresponding window is treated as unique.

If a unique window of length `L` exists, any longer window containing it can occur at most once: a second occurrence of the longer window would imply a second occurrence of the original length-`L` window.

Therefore, feasibility is monotonic, and binary search returns the minimum feasible length.

The proof for the hash-based implementation is probabilistic because distinct sequences can theoretically collide under both hashes. The suffix-array approach avoids this limitation.

---

# Edge Cases

| Input | Output | Reason |
|---|---:|---|
| `[7]` | `1` | The only subarray is unique |
| `[3,3,3]` | `3` | Only the full array is unique |
| `[1,2,3]` | `1` | Every value occurs once |
| `[1,1]` | `2` | Length 1 repeats |
| `[1,1,2,2,1]` | `2` | All length-2 windows are unique |
| `[5,5,5,5]` | `4` | Only the complete array is unique |

---

# Common Mistakes

1. **Counting only individual values:** the task asks about complete contiguous sequences, not just element frequencies.
2. **Ignoring order:** `[1,2]` and `[2,1]` are different subarrays.
3. **Using a set without frequencies:** a set cannot distinguish one occurrence from multiple occurrences.
4. **Binary searching without proving monotonicity:** the extension argument is essential.
5. **Using one rolling hash as if it were exact:** hash collisions can produce an incorrect answer.
6. **Using only 64-bit overflow hashing:** arithmetic modulo `2^64` is vulnerable to constructed collisions.
7. **Forgetting the final window:** the last valid starting index is `n - len`.
8. **Mixing LCP indexing conventions:** in this README, `lcp[k]` is the LCP of `sa[k]` and `sa[k-1]`.

## Hash Collision Warning

A rolling hash is a compact fingerprint, not a proof of equality.

There have been reported collision cases for single-modulus and 64-bit-overflow solutions to this problem. Double hashing greatly reduces practical collision risk, but it remains probabilistic. The suffix-array solution compares the original values and is deterministic.

---

# Approach Comparison

| Feature | Suffix Array + LCP | Binary Search + Double Hash |
|---|---|---|
| Main technique | Suffix ordering | Binary search + hashing |
| Time | `O(n log² n)` in supplied implementations | Expected `O(n log n)` |
| Space | `O(n)` | `O(n)` |
| Collision risk | None | Extremely small, but nonzero |
| Implementation | More involved | More straightforward |
| Best use | Exact correctness | Fast practical solution |

## Final Recommendation

Use **Approach 1 — Suffix Array + LCP** when you want a deterministic solution that cannot fail because of hash collisions.

Approach 2 is a concise and efficient practical alternative. Its double hash is much safer than a single hash, but it is still probabilistic.

---

## VS Code Folder Structure

```text
C:\Leetcode_Solutions
│
└── 3900-3999
    │
    └── 3934-Smallest Unique Subarray
        │
        ├── README.md
        ├── Solution.java
        ├── Solution.cpp
        └── Solution.py
```

## PowerShell — Create Folder and Files

```powershell
cd C:\Leetcode_Solutions

cd "3900-3999"

mkdir "3934-Smallest Unique Subarray"

cd "3934-Smallest Unique Subarray"

ni README.md
ni Solution.java
ni Solution.cpp
ni Solution.py

code .
```

## GitHub Upload Commands

Run these commands from the repository root:

```powershell
cd C:\Leetcode_Solutions

git status

git add "3900-3999/3934-Smallest Unique Subarray"

git status

git commit -m "Add solutions for LeetCode 3934"

git push origin main
```

## References

- [LeetCode 3934 — Smallest Unique Subarray](https://leetcode.com/problems/smallest-unique-subarray/)
- [Doocs — LeetCode 3934 Explanation](https://leetcode.doocs.org/en/lc/3934/)
- [Suffix Array](https://en.wikipedia.org/wiki/Suffix_array)
- [Kasai's Algorithm](https://en.wikipedia.org/wiki/LCP_array)
- [Rolling Hash](https://cp-algorithms.com/string/string-hashing.html)
- [Binary Search](https://cp-algorithms.com/num_methods/binary_search.html)
