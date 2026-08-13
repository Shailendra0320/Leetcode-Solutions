# 2213. Longest Substring of One Repeating Character

## 🔗 Problem Link

**LeetCode 2213:** https://leetcode.com/problems/longest-substring-of-one-repeating-character/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Second):** https://leetcode.com/Shailu03/

---

# 📌 Problem Statement

You are given a string `s`, a string `queryCharacters`, and an integer array `queryIndices`.

For every query, the character at the given index is changed:

````text
s[queryIndices[i]] = queryCharacters[i]

After each update, return the length of the longest substring consisting of only one repeating character.

The answer must be returned as an array where:

answer[i]

represents the longest repeating substring after applying the i-th query.

💡 Intuition

A straightforward approach would be to update the string and scan the entire string after every query.

For example:

Update Character
       ↓
Scan Entire String
       ↓
Find Longest Repeating Substring
       ↓
Store Answer

If the string has n characters and there are q queries, this approach can take:

O(n × q)

time.

This becomes inefficient when both n and q are large.

Instead, we use a Segment Tree.

The Segment Tree maintains information about different parts of the string so that:

A single character can be updated efficiently.
The longest repeating substring can be recalculated efficiently.
The answer for the entire string is always available at the root.
🌳 Segment Tree Approach

For every Segment Tree node, we store information about the segment represented by that node.

Each node maintains:

prefLen
sufLen
maxLen
rangeLen
leftChar
rightChar

These values are sufficient to merge two adjacent segments efficiently.

🧩 Meaning of Each Value
rangeLen

The total length of the segment represented by the node.

For example:

"aaabb"

has:

rangeLen = 5
prefLen

The length of the longest repeating prefix of the segment.

For:

"aaabb"

the longest repeating prefix is:

"aaa"

Therefore:

prefLen = 3
sufLen

The length of the longest repeating suffix of the segment.

For:

"aaabb"

the longest repeating suffix is:

"bb"

Therefore:

sufLen = 2
maxLen

The longest substring consisting of the same character anywhere inside the segment.

For:

"aaabb"

we have:

maxLen = 3
leftChar

The first character of the segment.

rightChar

The last character of the segment.

🔍 Key Observation

When two adjacent segments are merged, the longest repeating substring may cross the boundary between them.

Consider:

Left Segment  = "aaab"
Right Segment = "bbcc"

The left segment ends with:

b

and the right segment starts with:

bb

Since the boundary characters are equal, they can be combined:

"b" + "bb" = "bbb"

Therefore, the repeating substring crossing the boundary has length:

sufLen[left] + prefLen[right]

This is the key reason we maintain both prefLen and sufLen.

🚀 Approach

The solution uses a Segment Tree with point updates.

For every node, we maintain:

1. Length of the segment
2. First character
3. Last character
4. Longest repeating prefix
5. Longest repeating suffix
6. Longest repeating substring

When two nodes are merged, we calculate these values from their children.

This allows us to update one character without scanning the complete string.

🔗 Merging Two Nodes

Suppose we have:

        Parent
       /      \
    Left      Right

First, we calculate the basic information:

leftChar[parent] = leftChar[left]

rightChar[parent] = rightChar[right]

rangeLen[parent] =
    rangeLen[left] + rangeLen[right]
📏 Calculating prefLen

Initially:

prefLen[parent] = prefLen[left]

The prefix can extend into the right segment if:

rightChar[left] == leftChar[right]

and the complete left segment consists of the same character:

prefLen[left] == rangeLen[left]

Then:

prefLen[parent] =
    rangeLen[left] + prefLen[right]
📏 Calculating sufLen

Initially:

sufLen[parent] = sufLen[right]

The suffix can extend into the left segment if:

rightChar[left] == leftChar[right]

and the complete right segment consists of the same character:

sufLen[right] == rangeLen[right]

Then:

sufLen[parent] =
    rangeLen[right] + sufLen[left]
🏆 Calculating maxLen

The longest repeating substring can be in one of three places:

Case 1 — Inside the left segment
maxLen[left]
Case 2 — Inside the right segment
maxLen[right]
Case 3 — Crossing the boundary

If:

rightChar[left] == leftChar[right]

then:

sufLen[left] + prefLen[right]

can form a repeating substring.

Therefore:

maxLen[parent] =
    max(
        maxLen[left],
        maxLen[right]
    )

and if the boundary characters match:

maxLen[parent] =
    max(
        maxLen[parent],
        sufLen[left] + prefLen[right]
    )
🌱 Building the Segment Tree

For a leaf node representing a single character:

prefLen  = 1
sufLen   = 1
maxLen   = 1
rangeLen = 1

and:

leftChar  = character
rightChar = character

For internal nodes:

Build the left child.
Build the right child.
Merge both children.

The complete Segment Tree can be built in:

O(n)

time.

🔄 Update Operation

For every query:

queryIndices[i]

we change the character to:

queryCharacters[i]

The update starts from the root and moves toward the leaf corresponding to the target index.

At the leaf, we update:

leftChar
rightChar

and reset:

prefLen = 1
sufLen = 1
maxLen = 1

After updating the leaf, all affected parent nodes are recalculated using the merge operation.

Only one path from the root to the leaf is affected.

Therefore, each update takes:

O(log n)

time.

📝 Algorithm
Convert the string s into a character array.
Create the Segment Tree arrays.
Build the Segment Tree from the original string.
Process every query one by one.
Find the index from queryIndices[i].
Replace the character with queryCharacters[i].
Update the corresponding leaf node.
Recalculate all affected parent nodes.
Store maxLen[1] as the answer for the current query.
Return the complete answer array.

The root node represents the entire string, therefore:

maxLen[1]

always contains the longest repeating substring after the current update.

🌳 Flowchart
                         Start
                           │
                           ▼
                    Read Input String
                           │
                           ▼
                 Build Segment Tree
                           │
                           ▼
                    Process Query
                           │
                           ▼
                 Update Character
                           │
                           ▼
                    Find Leaf Node
                           │
                           ▼
              Recalculate Parent Nodes
                           │
                           ▼
                    Update Root
                           │
                           ▼
                 ans[i] = maxLen[1]
                           │
                           ▼
                   More Queries?
                    /         \
                  Yes          No
                   │            │
                   ▼            ▼
             Next Query    Return Answers
📖 Example
Example

Consider:

s = "babacc"

Suppose a query changes the character at some index.

The character at that position is updated in the corresponding Segment Tree leaf.

The affected nodes are then recalculated.

The root node represents the complete updated string.

Therefore:

maxLen[1]

gives the length of the longest substring containing the same character.

🔄 Dry Run

Consider:

s = "aaabb"

The Segment Tree initially stores:

Longest repeating substring = "aaa"
Length = 3

Now suppose we update:

index = 3
new character = 'a'

The string becomes:

"aaaa b"

The updated longest repeating substring is:

"aaaa"

Therefore:

maxLen[1] = 4

Only the path corresponding to index 3 needs to be updated.

There is no need to scan the complete string.

🧠 Why This Approach Works

Every segment can be summarized using only:

Left Character
Right Character
Prefix Length
Suffix Length
Maximum Repeating Length
Segment Length

When two adjacent segments are combined, this information is enough to calculate the result for the parent segment.

Therefore, the complete string does not need to be scanned after every update.

The Segment Tree dynamically maintains the required information.

⚠️ Important Edge Cases
1. All Characters Are the Same
s = "aaaa"

The answer is:

4
2. All Characters Are Different
s = "abcd"

The answer is:

1
3. Updating a Character With the Same Character

If:

s[index] == queryCharacters[i]

the string does not actually change, so the answer remains the same.

4. Repeating Substring Crosses the Boundary

When:

rightChar[left] == leftChar[right]

we must consider:

sufLen[left] + prefLen[right]

This is essential for correctly calculating maxLen.

⏱️ Complexity Analysis

Let:

n = s.length()

and:

q = queryIndices.length
Build
O(n)
Each Update
O(log n)
All Queries
O(n + q log n)
Space Complexity
O(n)

The Segment Tree uses approximately:

4 × n

nodes.

🎯 Key Takeaways
✅ Use a Segment Tree for dynamic character updates.
✅ Store the first and last characters of every segment.
✅ Store the longest repeating prefix and suffix.
✅ Store the longest repeating substring inside every segment.
✅ Merge two segments using their boundary characters.
✅ A point update takes O(log n).
✅ The root's maxLen always contains the answer for the complete string.
✅ Overall complexity is:
Time Complexity  : O(n + q log n)
Space Complexity : O(n)
# Java Solution

## Approach 1 — Segment Tree

```java
class Solution {

    int[] prefLen, sufLen, maxLen, rangeLen;
    char[] leftChar, rightChar;
    char[] str;

    void build(
        int node,
        int start,
        int end
    ) {

        if (start == end) {

            prefLen[node] =
                sufLen[node] =
                maxLen[node] =
                rangeLen[node] = 1;

            leftChar[node] =
                rightChar[node] =
                str[start];

            return;
        }

        int mid =
            (start + end) / 2;

        build(
            2 * node,
            start,
            mid
        );

        build(
            2 * node + 1,
            mid + 1,
            end
        );

        merge(
            node,
            start,
            end
        );
    }

    void merge(
        int node,
        int start,
        int end
    ) {

        int left =
            2 * node;

        int right =
            2 * node + 1;

        leftChar[node] =
            leftChar[left];

        rightChar[node] =
            rightChar[right];

        rangeLen[node] =
            rangeLen[left] +
            rangeLen[right];

        prefLen[node] =
            prefLen[left];

        if (
            rightChar[left] ==
            leftChar[right] &&
            prefLen[left] ==
            rangeLen[left]
        ) {

            prefLen[node] =
                rangeLen[left] +
                prefLen[right];
        }

        sufLen[node] =
            sufLen[right];

        if (
            rightChar[left] ==
            leftChar[right] &&
            sufLen[right] ==
            rangeLen[right]
        ) {

            sufLen[node] =
                rangeLen[right] +
                sufLen[left];
        }

        maxLen[node] =
            Math.max(
                maxLen[left],
                maxLen[right]
            );

        if (
            rightChar[left] ==
            leftChar[right]
        ) {

            maxLen[node] =
                Math.max(
                    maxLen[node],
                    sufLen[left] +
                    prefLen[right]
                );
        }
    }

    void update(
        int node,
        int start,
        int end,
        int idx,
        char ch
    ) {

        if (start == end) {

            str[idx] =
                ch;

            leftChar[node] =
                rightChar[node] =
                ch;

            prefLen[node] =
                sufLen[node] =
                maxLen[node] = 1;

            return;
        }

        int mid =
            (start + end) / 2;

        if (idx <= mid) {

            update(
                2 * node,
                start,
                mid,
                idx,
                ch
            );

        } else {

            update(
                2 * node + 1,
                mid + 1,
                end,
                idx,
                ch
            );
        }

        merge(
            node,
            start,
            end
        );
    }

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        int n =
            s.length();

        int k =
            queryIndices.length;

        str =
            s.toCharArray();

        prefLen =
            new int[4 * n];

        sufLen =
            new int[4 * n];

        maxLen =
            new int[4 * n];

        rangeLen =
            new int[4 * n];

        leftChar =
            new char[4 * n];

        rightChar =
            new char[4 * n];

        build(
            1,
            0,
            n - 1
        );

        int[] ans =
            new int[k];

        for (
            int i = 0;
            i < k;
            i++
        ) {

            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] =
                maxLen[1];
        }

        return ans;
    }
}
````
