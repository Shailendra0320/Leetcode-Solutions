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

# 3020. Find the Maximum Number of Elements in Subset

## Tags

```text
HashMap
Greedy
Math
Simulation
Array
Java
C++
```

---

# Intuition

We need to construct the **longest valid subset** having the form

```text
x

x

x²

x⁴

x⁸

...

center

...

x⁸

x⁴

x²

x

x
```

Notice that

- every element before the center needs **two copies**
- the center needs **one copy**
- each next value is the square of the previous value

Therefore the problem becomes building the **longest square-chain**.

---

# Key Observation

Suppose

```text
x = 2
```

Then the chain becomes

```text
2

4

16

256

...
```

If every value exists at least twice

```text
2
2

4
4

16
16
```

we may continue.

Otherwise

the chain stops.

---

# Special Case

The number

```text
1
```

is unique because

```text
1² = 1
```

Therefore

```text
1

1

1

1
```

can never move to another value.

Only an

```text
odd
```

number of ones can form a palindrome.

Hence

```text
answer

=

largest odd frequency of 1
```

---

# Approaches

1. Frequency HashMap (Optimal)
2. Chain Simulation
3. Greedy Frequency Expansion

---

# Approach 1 — Frequency HashMap (Optimal)

## Idea

Store

```text
number

↓

frequency
```

inside a HashMap.

For every unique value

```text
x
```

try building

```text
x

↓

x²

↓

x⁴

↓

...
```

If a value occurs at least

```text
2
```

times

it contributes

```text
+2
```

Otherwise

the chain stops.

The last value contributes

```text
+1
```

because it becomes the center.

---

# Flowchart

```text
Build Frequency Map

          │

          ▼

Take One Number

          │

          ▼

Build Square Chain

x

↓

x²

↓

x⁴

↓

...

          │

          ▼

Frequency >=2 ?

      YES │ NO

          ▼

Add 2

Continue

          │

          ▼

Stop

          │

          ▼

Add Center

          │

          ▼

Update Answer
```

---

# Visualization

Example

```text
nums

2

2

4

4

16

16

256
```

Chain

```text
2

↓

4

↓

16

↓

256
```

Contribution

```text
2
2

+

4
4

+

16
16

+

256

=

7
```

---

# Approach 2 — Chain Simulation

## Idea

Instead of counting while traversing,

first explicitly build

```text
chain

=

[x

x²

x⁴

...]
```

Store every value inside a list.

Then traverse the list

and calculate

```text
2

for every pair

+

1

for center
```

This approach is easier to understand,

although slightly longer.

---

# Flowchart

```text
Choose Number

        │

        ▼

Generate Chain

        │

        ▼

Store

x

x²

x⁴

...

        │

        ▼

Traverse Chain

        │

        ▼

Pair ?

YES → +2

NO

↓

Center +1

↓

Update Answer
```

---

# Visualization

Chain

```text
3

↓

9

↓

81

↓

6561
```

List

```text
[3

9

81

6561]
```

Now compute answer

from the list.

---

# Approach 3 — Greedy Frequency Expansion

## Idea

We don't explicitly store the chain.

Instead

start from

```text
x
```

and greedily continue while

```text
frequency >= 2
```

Immediately update

```text
length += 2
```

Move to

```text
x²
```

and repeat.

This avoids storing any extra chain.

---

# Flowchart

```text
Take x

     │

     ▼

Exists ?

     │

     ▼

Frequency >=2 ?

YES

↓

length +=2

↓

x=x²

↓

Continue

NO

↓

Center +1

↓

Answer
```

---

# Dry Run

Input

```text
nums

=

[2

2

4

4

16

16

256]
```

Frequency

```text
2

→2

4

→2

16

→2

256

→1
```

Chain

```text
2

↓

4

↓

16

↓

256
```

Contribution

```text
2

+

2

+

2

+

1

=

7
```

Answer

```text
7
```

---

# Memory Visualization

Frequency Map

```text
2

↓

2

4

↓

2

16

↓

2

256

↓

1
```

Chain Traversal

```text
2

↓

4

↓

16

↓

256
```

Current Length

```text
0

↓

2

↓

4

↓

6

↓

7
```

---

# Complexity Analysis

---

## 🚀 Approach 1 — Frequency HashMap

### ⏱️ Time Complexity

```text
O(n × log log M)
```

where

```text
n = Number of elements

M = Maximum value in the array
```

### 📖 Explanation

1. Traverse the array once to build the frequency map.

```text
O(n)
```

2. For every distinct number, construct its square chain.

```text
x
↓

x²
↓

x⁴
↓

x⁸
↓

x¹⁶
↓

...
```

Since every squaring operation grows extremely fast, the chain length is only

```text
O(log log M)
```

Therefore,

```text
Overall Time

=

O(n × log log M)
```

### 💾 Space Complexity

```text
O(n)
```

because the frequency of every distinct number is stored in a HashMap.

---

## 🚀 Approach 2 — Chain Simulation

### ⏱️ Time Complexity

```text
O(n × log log M)
```

### 📖 Explanation

- Build the frequency map.
- Explicitly create the square chain.

Example

```text
2

↓

4

↓

16

↓

256

↓

65536
```

Each chain is very short because numbers increase exponentially.

Hence,

```text
Time

=

O(n × log log M)
```

### 💾 Space Complexity

```text
O(n)
```

Extra memory is used for

- Frequency HashMap
- Temporary chain list

---

## 🚀 Approach 3 — Greedy Frequency Expansion

### ⏱️ Time Complexity

```text
O(n × log log M)
```

### 📖 Explanation

Instead of storing the complete chain,

we greedily move

```text
x

↓

x²

↓

x⁴

↓

...
```

until the chain breaks.

Since the chain length is bounded by

```text
log log M
```

the complexity remains

```text
O(n × log log M)
```

### 💾 Space Complexity

```text
O(n)
```

Only the frequency HashMap is maintained.

---

# 📊 Complexity Comparison

|                  Approach                   |   Time Complexity    | Space Complexity |
| :-----------------------------------------: | :------------------: | :--------------: |
|     **Approach 1** — Frequency HashMap      | **O(n × log log M)** |     **O(n)**     |
|      **Approach 2** — Chain Simulation      | **O(n × log log M)** |     **O(n)**     |
| **Approach 3** — Greedy Frequency Expansion | **O(n × log log M)** |     **O(n)**     |

---

# 🔍 Why is the Chain Length only `log log M`?

Suppose

```text
x = 2
```

The square chain becomes

```text
2

↓

4

↓

16

↓

256

↓

65536

↓

4294967296
```

Notice that

```text
Exponent

1

↓

2

↓

4

↓

8

↓

16

↓

32
```

The exponent **doubles every step**.

Therefore, after only a few squaring operations, the value becomes extremely large.

Mathematically,

```text
Chain Length

≈ log₂(log₂(M))
```

which is commonly written as

```text
O(log log M)
```

---

# 🎯 Final Complexity

```text
Time  : O(n × log log M)

Space : O(n)
```

This makes the solution highly efficient, even for very large input values, because each square chain contains only a handful of elements.

# Java Solutions

## Approach 1 — Frequency HashMap

```
//Approach-1 (Frequency HashMap)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {

    public int maximumLength(int[] nums) {

        Map<Long, Integer> count = new HashMap<>();

        for (int num : nums) {

            count.put(
                (long) num,
                count.getOrDefault((long) num, 0) + 1
            );
        }

        int answer = 1;

        if (count.containsKey(1L)) {

            int ones = count.get(1L);

            answer = Math.max(
                answer,
                (ones % 2 == 1) ? ones : ones - 1
            );
        }

        for (long value : count.keySet()) {

            if (value == 1) {
                continue;
            }

            int length = 0;

            long current = value;

            while (
                count.containsKey(current)
                &&
                count.get(current) >= 2
            ) {

                length += 2;

                current *= current;
            }

            if (count.containsKey(current)) {

                length++;

            } else {

                length--;
            }

            answer = Math.max(answer, length);
        }

        return answer;
    }
}
```

## Approach 2 — Chain Simulation

```
//Approach-2 (Chain Simulation)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {

    public int maximumLength(int[] nums) {

        Map<Long, Integer> frequency = new HashMap<>();

        Set<Long> values = new HashSet<>();

        for (int num : nums) {

            long value = num;

            frequency.put(
                value,
                frequency.getOrDefault(value, 0) + 1
            );

            values.add(value);
        }

        int answer = 1;

        if (frequency.containsKey(1L)) {

            int ones = frequency.get(1L);

            answer = Math.max(
                answer,
                (ones % 2 == 1) ? ones : ones - 1
            );
        }

        for (long start : values) {

            if (start == 1) {
                continue;
            }

            List<Long> chain =
                new ArrayList<>();

            long current = start;

            while (values.contains(current)) {

                chain.add(current);

                current *= current;
            }

            int length = 0;

            for (
                int index = 0;
                index < chain.size();
                index++
            ) {

                long value =
                    chain.get(index);

                if (
                    index < chain.size() - 1
                ) {

                    if (
                        frequency.get(value) >= 2
                    ) {

                        length += 2;

                    } else {

                        break;
                    }

                } else {

                    length++;
                }
            }

            answer =
                Math.max(answer, length);
        }

        return answer;
    }
}
```

## Approach 3 — Greedy Expansion

```
//Approach-3 (Greedy Frequency Expansion)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {

    public int maximumLength(int[] nums) {

        Map<Long, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.merge((long) num, 1, Integer::sum);
        }

        int oneCount = count.getOrDefault(1L, 0);

        int answer =
            (oneCount & 1) == 1
            ? oneCount
            : oneCount - 1;

        count.remove(1L);

        for (long start : count.keySet()) {

            int length = 0;

            long current = start;

            while (
                count.containsKey(current)
                &&
                count.get(current) > 1
            ) {

                length += 2;

                current *= current;
            }

            answer = Math.max(
                answer,
                length +
                (
                    count.containsKey(current)
                    ? 1
                    : -1
                )
            );
        }

        return answer;
    }
}
```

# C++ Solutions

## Approach 1 — Frequency HashMap

```
//Approach-1 (Frequency HashMap)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {
public:

    int maximumLength(vector<int>& nums) {

        unordered_map<long long,int> count;

        for(int num : nums){

            count[(long long)num]++;
        }

        int answer = 1;

        if(count.count(1)){

            int ones = count[1];

            answer =
                max(
                    answer,
                    ones % 2 ? ones : ones - 1
                );
        }

        for(auto &[value,freq] : count){

            if(value == 1)
                continue;

            int length = 0;

            long long current = value;

            while(
                count.count(current)
                &&
                count[current] >= 2
            ){

                length += 2;

                current *= current;
            }

            if(count.count(current))
                length++;
            else
                length--;

            answer =
                max(answer,length);
        }

        return answer;
    }
};

```

## Approach 2 — Chain Simulation

```
//Approach-2 (Chain Simulation)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {
public:

    int maximumLength(vector<int>& nums) {

        unordered_map<long long,int> frequency;

        unordered_set<long long> values;

        for(int num : nums){

            frequency[num]++;

            values.insert(num);
        }

        int answer = 1;

        if(frequency.count(1)){

            int ones = frequency[1];

            answer =
                max(
                    answer,
                    ones % 2 ? ones : ones - 1
                );
        }

        for(long long start : values){

            if(start == 1)
                continue;

            vector<long long> chain;

            long long current = start;

            while(values.count(current)){

                chain.push_back(current);

                current *= current;
            }

            int length = 0;

            for(int i=0;i<chain.size();i++){

                if(i+1<chain.size()){

                    if(frequency[chain[i]]>=2){

                        length+=2;

                    }else{

                        break;
                    }

                }else{

                    length++;
                }
            }

            answer=max(answer,length);
        }

        return answer;
    }
};

```

## Approach 3 — Greedy Expansion

```
//Approach-3 (Greedy Frequency Expansion)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {
public:

    int maximumLength(vector<int>& nums) {

        unordered_map<long long,int> count;

        for(int num : nums){

            count[(long long)num]++;
        }

        int oneCount = count[1];

        int answer =
            oneCount & 1
            ? oneCount
            : oneCount - 1;

        count.erase(1);

        for(auto &[start,freq] : count){

            int length = 0;

            long long current = start;

            while(
                count.count(current)
                &&
                count[current] > 1
            ){

                length += 2;

                current *= current;
            }

            answer =
                max(
                    answer,
                    length +
                    (
                        count.count(current)
                        ? 1
                        : -1
                    )
                );
        }

        return answer;
    }
};

```
