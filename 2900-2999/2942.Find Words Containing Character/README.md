<!-- problem:start -->

# [2942. Find Words Containing Character](https://leetcode.com/problems/find-words-containing-character)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array of strings <code>words</code> and a character <code>x</code>.</p>

<p>Return <em>an <strong>array of indices</strong> representing the words that contain the character </em><code>x</code>.</p>

<p><strong>Note</strong> that the returned array may be in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;leet&quot;,&quot;code&quot;], x = &quot;e&quot;
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> &quot;e&quot; occurs in both words: &quot;l<strong><u>ee</u></strong>t&quot;, and &quot;cod<u><strong>e</strong></u>&quot;. Hence, we return indices 0 and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;bcd&quot;,&quot;aaaa&quot;,&quot;cbc&quot;], x = &quot;a&quot;
<strong>Output:</strong> [0,2]
<strong>Explanation:</strong> &quot;a&quot; occurs in &quot;<strong><u>a</u></strong>bc&quot;, and &quot;<u><strong>aaaa</strong></u>&quot;. Hence, we return indices 0 and 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;bcd&quot;,&quot;aaaa&quot;,&quot;cbc&quot;], x = &quot;z&quot;
<strong>Output:</strong> []
<strong>Explanation:</strong> &quot;z&quot; does not occur in any of the words. Hence, we return an empty array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>x</code> is a lowercase English letter.</li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We directly traverse each string `words[i]` in the string array `words`. If `x` appears in `words[i]`, we add `i` to the answer array.

After the traversal, we return the answer array.

The time complexity is $O(L)$, where $L$ is the sum of the lengths of all strings in the array `words`. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Java

```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            if (words[i].indexOf(x) != -1) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

#### Java

```java
class Solution {
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      char[] chars = words[i].toCharArray();
      for (char c : chars) {
        if (c == x) {
          result.add(i);
          break;
        }
      }

    }
    return result;
  }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
