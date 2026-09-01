# 3310. Remove Methods From Project

## 🔗 Problem Link

**LeetCode:** https://leetcode.com/problems/remove-methods-from-project/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

---

# 📌 Problem Statement

You are maintaining a project containing `n` methods numbered from:

```text
0 to n - 1
```

You are given:

- an integer `n`
- an integer `k`
- a 2D array `invocations`

where:

```text
invocations[i] = [a, b]
```

means:

```text
method a invokes method b
```

Method `k` is known to contain a bug.

Therefore:

```text
method k
```

and every method that is directly or indirectly invoked by `k` is considered **suspicious**.

We want to remove all suspicious methods.

However, a group of methods can be removed only when:

```text
no method outside the group invokes any method inside the group
```

If this condition is violated, we must remove **nothing**.

Return all remaining methods.

The answer may be returned in any order.

---

# 🧾 Constraints

```text
1 <= n <= 10^5

0 <= k <= n - 1

0 <= invocations.length <= 2 * 10^5

invocations[i] = [a, b]

0 <= a, b <= n - 1

a != b

All invocation pairs are distinct.
```

Let:

```text
m = invocations.length
```

We need an efficient:

```text
O(n + m)
```

solution.

---

# 💡 Intuition

The problem is naturally represented as a **directed graph**.

Each method is a node.

An invocation:

```text
a -> b
```

means there is a directed edge from method `a` to method `b`.

The suspicious methods are exactly the nodes reachable from:

```text
k
```

using outgoing edges.

So the first part of the problem is simply:

```text
Find all nodes reachable from k
```

using DFS or BFS.

But finding the suspicious methods is only half of the problem.

We must also determine whether the suspicious group can actually be removed.

Suppose:

```text
u -> v
```

and:

```text
u = non-suspicious
v = suspicious
```

Then `u` is outside the suspicious group but invokes a suspicious method.

Therefore, removing the suspicious group would break that invocation.

So:

```text
non-suspicious -> suspicious
```

means:

```text
Cannot remove suspicious methods.
```

This gives the complete idea:

```text
1. Find all suspicious methods reachable from k.
2. Check whether any edge comes from a non-suspicious method
   to a suspicious method.
3. If such an edge exists:
       return all methods.
4. Otherwise:
       remove the suspicious methods.
```

---

# 🔍 Graph Interpretation

Consider:

```text
invocations = [
    [1,2],
    [0,1],
    [3,2]
]
```

The graph is:

```text
0 → 1 → 2
    ↑
    suspicious starting from 1

3 ─────→ 2
```

If:

```text
k = 1
```

then:

```text
1
↓
2
```

are suspicious.

But:

```text
0 → 1
3 → 2
```

come from non-suspicious methods.

Therefore, the suspicious methods cannot be removed.

Return:

```text
[0,1,2,3]
```

---

# 🔑 Key Observation

There is exactly one condition that prevents deletion:

```text
non-suspicious method
        |
        v
 suspicious method
```

In graph terms:

```text
Outside → Suspicious
```

If even one such edge exists, the entire suspicious group must remain.

Therefore, after identifying suspicious methods, we only need to scan all invocation edges and search for:

```text
!suspicious[a] && suspicious[b]
```

If found:

```text
return all methods
```

Otherwise:

```text
return all non-suspicious methods
```

---

# 🌳 Overall Strategy

```text
                       Start
                         |
                         v
                Build Directed Graph
                         |
                         v
               DFS/BFS from method k
                         |
                         v
                Mark suspicious methods
                         |
                         v
               Scan every invocation
                         |
                         v
          Is there an edge outside → suspicious?
                  /                 \
                Yes                  No
                 |                    |
                 v                    v
        Cannot remove them      Remove suspicious
                 |                    |
                 v                    v
        Return all methods      Return remaining
```

---

# 🚀 Approach 1 — DFS + Reverse Graph

This is the **recommended approach** and is a clean graph solution.

We build two graphs:

### Directed graph

```text
g[a] = methods directly invoked by a
```

This is used to find all suspicious methods.

Starting from:

```text
k
```

run DFS.

Every reachable method becomes suspicious.

---

### Reverse / Undirected helper graph

For checking whether suspicious methods are connected to outside callers, we can also store incoming relationships.

A convenient implementation uses:

```text
f
```

where every invocation:

```text
a -> b
```

is represented in both directions:

```text
f[a].add(b)
f[b].add(a)
```

Then we traverse the non-suspicious part to identify methods reachable from outside and mark them as safe.

This is the structure used in the standard two-DFS solution.

---

# 🧠 Two-DFS Idea

The first DFS finds:

```text
all suspicious methods
```

The second DFS starts from non-suspicious methods and walks through their connections.

If a suspicious node is reached from the outside, it becomes known to be connected to an external caller.

The remaining methods are exactly those that stay non-suspicious.

This approach directly models the condition for safe removal.

---

# 🔍 First DFS — Find Suspicious Methods

Starting at:

```text
k
```

follow only outgoing invocation edges.

For example:

```text
0 → 1
1 → 2
2 → 4
3 → 4
```

If:

```text
k = 1
```

then:

```text
1 → 2 → 4
```

so:

```text
suspicious = {1,2,4}
```

Method `3` is not suspicious because `1` cannot reach it.

---

# 🔍 Second Step — Check External Callers

Now inspect every invocation:

```text
a → b
```

If:

```text
a is not suspicious
```

but:

```text
b is suspicious
```

then a non-suspicious method directly invokes a suspicious method.

That means the suspicious group is not removable.

So:

```text
return [0,1,2,...,n-1]
```

---

# 🧮 Simplified Equivalent Condition

After the first DFS:

```text
suspicious[x] = true
```

means:

```text
x is reachable from k
```

Now simply test every edge:

```text
if (!suspicious[a] && suspicious[b])
```

then deletion is impossible.

If no such edge exists, all suspicious methods can be removed.

This is often the easiest way to explain the core logic.

---

# 📖 Example 1

```text
n = 4
k = 1

invocations = [
    [1,2],
    [0,1],
    [3,2]
]
```

Graph:

```text
0 → 1 → 2
3 ───→ 2
```

Starting from `1`:

```text
1 → 2
```

Suspicious:

```text
{1,2}
```

Now check edges.

### Edge

```text
0 → 1
```

`0` is not suspicious.

`1` is suspicious.

Therefore:

```text
non-suspicious → suspicious
```

exists.

So we cannot remove the suspicious group.

Answer:

```text
[0,1,2,3]
```

---

# 📖 Example 2

```text
n = 5
k = 0

invocations = [
    [1,2],
    [0,2],
    [0,1],
    [3,4]
]
```

Graph:

```text
0 → 1 → 2
 \------→ 2

3 → 4
```

Starting from `0`:

```text
0 → 1
0 → 2
1 → 2
```

Suspicious:

```text
{0,1,2}
```

Check incoming edges into suspicious nodes.

Every edge involving suspicious nodes comes from another suspicious node.

There is no:

```text
non-suspicious → suspicious
```

edge.

Therefore, the suspicious group can be removed.

Remaining:

```text
[3,4]
```

---

# 📖 Example 3

```text
n = 3
k = 2

invocations = [
    [1,2],
    [0,1],
    [2,0]
]
```

Graph:

```text
0 → 1 → 2
↑         |
└─────────┘
```

Starting from `2`:

```text
2 → 0 → 1 → 2
```

All methods are reachable.

Therefore:

```text
suspicious = {0,1,2}
```

There are no non-suspicious methods.

So there cannot be an outside caller.

All methods can be removed.

Answer:

```text
[]
```

---

# 🔄 Detailed Dry Run

Consider:

```text
n = 5
k = 0

invocations = [
    [1,2],
    [0,2],
    [0,1],
    [3,4]
]
```

## Step 1 — Build Graph

```text
0 → 2
0 → 1
1 → 2
3 → 4
```

---

## Step 2 — Start DFS From k = 0

Visit:

```text
0
```

From `0`:

```text
1
2
```

From `1`:

```text
2
```

So suspicious nodes are:

```text
0,1,2
```

---

## Step 3 — Identify Non-Suspicious Methods

Remaining methods:

```text
3,4
```

---

## Step 4 — Check Edges

```text
1 → 2
0 → 2
0 → 1
```

All source nodes are suspicious.

The edge:

```text
3 → 4
```

is completely outside the suspicious group.

Therefore, no external method calls a suspicious method.

Deletion is valid.

---

## Step 5 — Build Answer

Return every method where:

```text
suspicious[i] == false
```

Therefore:

```text
[3,4]
```

---

# 🌳 Approach 1 Flowchart

```text
                         Start
                           |
                           v
                    Build directed graph
                           |
                           v
                       DFS(k)
                           |
                           v
                 Mark suspicious methods
                           |
                           v
                    Check every edge
                           |
                           v
             source non-suspicious AND
                destination suspicious?
                       /          \
                     Yes           No
                      |             |
                      v             v
              Return all      Continue checking
                 methods
                                    |
                                    v
                            No violating edge?
                                    |
                                    v
                         Return non-suspicious
                              methods
```

---

# 🥈 Approach 2 — DFS + Explicit Incoming-Edge Check

A slightly simpler implementation is:

```text
1. Build only an outgoing adjacency list.
2. DFS from k to mark all suspicious methods.
3. Scan the original invocation list.
4. If any:
       !suspicious[a] && suspicious[b]
   exists:
       return all methods.
5. Otherwise return all non-suspicious methods.
```

This approach avoids the second DFS completely.

It works because the removal condition is exactly about an invocation from outside the suspicious set into the suspicious set.

For implementation and explanation, this is usually the most direct approach.

---

# 🧠 Why Approach 2 Is Sufficient

The first DFS tells us:

```text
Which methods are suspicious?
```

The original invocation list already gives every directed edge.

So there is no need to reconstruct reachability from the outside.

We only need to test the condition:

```text
outside → suspicious
```

If it exists:

```text
cannot remove
```

If it does not:

```text
can remove
```

Therefore:

```text
DFS + edge scan
```

completely solves the problem.

---

# 📊 Approach Comparison

| Approach                | Main Idea                             |          Time           |    Space     |     Recommendation     |
| :---------------------- | :------------------------------------ | :---------------------: | :----------: | :--------------------: |
| Approach 1              | Two DFS + graph traversal             |      **O(n + m)**       | **O(n + m)** |         ⭐⭐⭐         |
| Approach 2              | DFS + scan invocation edges           |      **O(n + m)**       | **O(n + m)** | ⭐⭐⭐ Best / Simplest |
| Naive Repeated Scanning | Repeatedly discover reachable methods | **O(n × m)** worst case |     O(n)     |           ❌           |

where:

```text
n = number of methods
m = number of invocations
```

---

# 🧠 Why Repeated Scanning Is Bad

A tempting solution is:

```text
Start with k
Repeatedly scan every invocation
Whenever a suspicious source is found,
mark its destination suspicious
Repeat until nothing changes
```

In the worst case, a chain like:

```text
0 → 1 → 2 → 3 → ... → n-1
```

can cause many complete scans.

This can lead to:

```text
O(n × m)
```

which is too slow for:

```text
n <= 10^5
m <= 2 × 10^5
```

DFS/BFS processes each node and edge only a constant number of times.

---

# 🔍 Graph Example

Consider:

```text
k = 2
```

and:

```text
2 → 3
3 → 5
5 → 7
```

Then:

```text
suspicious = {2,3,5,7}
```

If there is:

```text
4 → 5
```

then:

```text
4 = non-suspicious
5 = suspicious
```

This is an external invocation.

Therefore:

```text
Cannot remove suspicious methods.
```

---

# 🏆 Correctness Proof

## Lemma 1 — DFS From k Finds Exactly the Suspicious Methods

A method is suspicious if and only if it is:

```text
k
```

or is reachable from `k` through one or more invocation edges.

DFS starting at `k` visits exactly all nodes reachable from `k`.

Therefore, the DFS marks exactly the suspicious methods.

---

## Lemma 2 — An Outside-to-Suspicious Edge Prevents Removal

Suppose there is an invocation:

```text
u → v
```

where:

```text
u is non-suspicious
v is suspicious
```

If we remove all suspicious methods, method `u` remains but its invocation to removed method `v` would cross the removed group boundary.

Therefore, the suspicious group cannot be legally removed.

So:

```text
!suspicious[u] && suspicious[v]
```

implies:

```text
return all methods
```

---

## Lemma 3 — If No Outside-to-Suspicious Edge Exists, Removal Is Safe

Assume there is no edge:

```text
non-suspicious → suspicious
```

Then every method outside the suspicious group has no invocation into the suspicious group.

Therefore, removing the entire suspicious group satisfies the problem's removal condition.

So all non-suspicious methods can safely remain.

---

## Theorem

The algorithm:

```text
1. Finds exactly the suspicious methods.
2. Checks whether any outside method invokes a suspicious method.
3. Returns all methods if such an edge exists.
4. Otherwise returns only non-suspicious methods.
```

therefore produces exactly the required result.

---

# ⚠️ Important Edge Cases

## 1. k Is the Only Suspicious Method

If no other method is reachable from `k`, then:

```text
suspicious = {k}
```

It can be removed only if no other method invokes `k`.

---

## 2. All Methods Are Suspicious

If every method is reachable from `k`, then there are no outside methods.

Therefore, the entire set can be removed.

Return:

```text
[]
```

---

## 3. No Invocation Edges

If:

```text
invocations = []
```

then only `k` is suspicious.

Since nobody can invoke `k`, it can be removed.

Return:

```text
all methods except k
```

---

## 4. External Method Invokes a Suspicious Method

Even one edge:

```text
outside → suspicious
```

is enough to prevent deletion.

Return:

```text
[0,1,...,n-1]
```

---

# 🧩 Important Direction of the Edge

The invocation:

```text
a → b
```

means:

```text
a invokes b
```

The blocking condition is:

```text
non-suspicious a
        →
suspicious b
```

Not the opposite.

An edge:

```text
suspicious → non-suspicious
```

does **not** prevent removing the suspicious group according to the stated condition.

---

# 🧠 Common Mistakes

### Mistake 1 — Treating the Graph as Undirected

Reachability from `k` follows:

```text
a → b
```

only in the direction of the invocation.

Do not perform ordinary undirected traversal for suspicious detection.

---

### Mistake 2 — Only Finding Suspicious Methods

Finding the suspicious set is not enough.

You must also check:

```text
non-suspicious → suspicious
```

---

### Mistake 3 — Checking the Wrong Edge Direction

The important condition is:

```text
!suspicious[a] && suspicious[b]
```

for:

```text
a → b
```

---

### Mistake 4 — Repeatedly Scanning All Edges

Use DFS/BFS instead of repeatedly scanning the entire invocation list.

---

### Mistake 5 — Returning Only Methods Not Reachable From k

That is correct only if no external caller enters the suspicious set.

Always perform the external-invocation check first.

---

# 🌟 Pattern Recognition

This problem combines:

```text
Directed Graph
+
Reachability
+
DFS/BFS
+
Set Validation
```

A useful general pattern is:

```text
1. Identify a special connected/reachable group.
2. Validate whether anything outside the group points into it.
3. If valid, remove it.
4. Otherwise, keep everything.
```

This pattern appears in dependency removal, module cleanup, package deletion, and graph-based project maintenance.

---

# 🗺️ Complete Graph Visualization

```text
                 ┌───────────────┐
                 │   Method k    │
                 └───────┬───────┘
                         |
                         v
                 Suspicious Group
            ┌────────────┬────────────┐
            |            |            |
            v            v            v
           M1           M2           M3
            |            |
            v            v
           M4           M5

Outside Methods:

       O1 ─────────────→ M2
                           ↑
                           |
                     Blocking Edge
```

Because:

```text
O1 is outside
M2 is suspicious
O1 → M2
```

the suspicious group cannot be removed.

---

# 💻 Java — Approach 1

```java
//Approach-1 (DFS + External Invocation Check)
//T.C : O(n + m)
//S.C : O(n + m)

import java.util.*;

class Solution {

    private List<Integer>[] graph;
    private boolean[] suspicious;

    public List<Integer> remainingMethods(
        int n,
        int k,
        int[][] invocations
    ) {

        graph =
            new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] =
                new ArrayList<>();
        }

        for (int[] edge : invocations) {

            int a =
                edge[0];

            int b =
                edge[1];

            graph[a].add(b);
        }

        suspicious =
            new boolean[n];

        dfs(k);

        for (int[] edge : invocations) {

            int a =
                edge[0];

            int b =
                edge[1];

            if (
                !suspicious[a] &&
                suspicious[b]
            ) {

                List<Integer> answer =
                    new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    answer.add(i);
                }

                return answer;
            }
        }

        List<Integer> answer =
            new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (!suspicious[i]) {
                answer.add(i);
            }
        }

        return answer;
    }

    private void dfs(
        int node
    ) {

        suspicious[node] =
            true;

        for (
            int next :
            graph[node]
        ) {

            if (!suspicious[next]) {
                dfs(next);
            }
        }
    }
}
```

---

# 💻 C++ — Approach 1

```cpp
//Approach-1 (DFS + External Invocation Check)
//T.C : O(n + m)
//S.C : O(n + m)

class Solution {
public:

    vector<int> remainingMethods(
        int n,
        int k,
        vector<vector<int>>& invocations
    ) {

        vector<vector<int>> graph(
            n
        );

        for (
            auto& edge :
            invocations
        ) {

            int a =
                edge[0];

            int b =
                edge[1];

            graph[a].push_back(
                b
            );
        }

        vector<bool> suspicious(
            n,
            false
        );

        dfs(
            k,
            graph,
            suspicious
        );

        for (
            auto& edge :
            invocations
        ) {

            int a =
                edge[0];

            int b =
                edge[1];

            if (
                !suspicious[a] &&
                suspicious[b]
            ) {

                vector<int> answer;

                for (
                    int i = 0;
                    i < n;
                    i++
                ) {

                    answer.push_back(i);
                }

                return answer;
            }
        }

        vector<int> answer;

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (!suspicious[i]) {
                answer.push_back(i);
            }
        }

        return answer;
    }

private:

    void dfs(
        int node,
        vector<vector<int>>& graph,
        vector<bool>& suspicious
    ) {

        suspicious[node] =
            true;

        for (
            int next :
            graph[node]
        ) {

            if (!suspicious[next]) {

                dfs(
                    next,
                    graph,
                    suspicious
                );
            }
        }
    }
};
```

---

# 🥈 Java — Approach 2

```java
//Approach-2 (Two DFS + Graph Traversal)
//T.C : O(n + m)
//S.C : O(n + m)

import java.util.*;

class Solution {

    private boolean[] suspicious;
    private boolean[] visited;
    private List<Integer>[] graph;
    private List<Integer>[] reverseGraph;

    public List<Integer> remainingMethods(
        int n,
        int k,
        int[][] invocations
    ) {

        graph =
            new ArrayList[n];

        reverseGraph =
            new ArrayList[n];

        for (int i = 0; i < n; i++) {

            graph[i] =
                new ArrayList<>();

            reverseGraph[i] =
                new ArrayList<>();
        }

        for (int[] edge : invocations) {

            int a =
                edge[0];

            int b =
                edge[1];

            graph[a].add(b);

            reverseGraph[b].add(a);
        }

        suspicious =
            new boolean[n];

        dfs(k);

        for (int i = 0; i < n; i++) {

            if (!suspicious[i]) {
                continue;
            }

            for (
                int caller :
                reverseGraph[i]
            ) {

                if (!suspicious[caller]) {

                    return allMethods(n);
                }
            }
        }

        List<Integer> answer =
            new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (!suspicious[i]) {
                answer.add(i);
            }
        }

        return answer;
    }

    private void dfs(
        int node
    ) {

        suspicious[node] =
            true;

        for (
            int next :
            graph[node]
        ) {

            if (!suspicious[next]) {

                dfs(next);
            }
        }
    }

    private List<Integer> allMethods(
        int n
    ) {

        List<Integer> answer =
            new ArrayList<>();

        for (int i = 0; i < n; i++) {
            answer.add(i);
        }

        return answer;
    }
}
```

---

# 🥈 C++ — Approach 2

```cpp
//Approach-2 (DFS + Reverse Graph)
//T.C : O(n + m)
//S.C : O(n + m)

class Solution {
public:

    vector<int> remainingMethods(
        int n,
        int k,
        vector<vector<int>>& invocations
    ) {

        vector<vector<int>> graph(
            n
        );

        vector<vector<int>> reverseGraph(
            n
        );

        for (
            auto& edge :
            invocations
        ) {

            int a =
                edge[0];

            int b =
                edge[1];

            graph[a].push_back(b);

            reverseGraph[b].push_back(a);
        }

        vector<bool> suspicious(
            n,
            false
        );

        dfs(
            k,
            graph,
            suspicious
        );

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (!suspicious[i]) {
                continue;
            }

            for (
                int caller :
                reverseGraph[i]
            ) {

                if (!suspicious[caller]) {

                    vector<int> answer;

                    for (
                        int j = 0;
                        j < n;
                        j++
                    ) {

                        answer.push_back(j);
                    }

                    return answer;
                }
            }
        }

        vector<int> answer;

        for (
            int i = 0;
            i < n;
            i++
        ) {

            if (!suspicious[i]) {
                answer.push_back(i);
            }
        }

        return answer;
    }

private:

    void dfs(
        int node,
        vector<vector<int>>& graph,
        vector<bool>& suspicious
    ) {

        suspicious[node] =
            true;

        for (
            int next :
            graph[node]
        ) {

            if (!suspicious[next]) {

                dfs(
                    next,
                    graph,
                    suspicious
                );
            }
        }
    }
};
```

---

# 🧠 Approach 1 vs Approach 2

## Approach 1

```text
DFS from k
+
scan every original edge
```

This is the simplest implementation.

We already have the original list:

```text
invocations
```

so checking:

```text
!suspicious[a] && suspicious[b]
```

is straightforward.

---

## Approach 2

```text
DFS from k
+
reverse graph
```

The reverse graph lets us directly ask:

```text
Who can invoke this suspicious method?
```

Then we check whether any caller is non-suspicious.

This can make the graph interpretation clearer, although the first approach requires less code.

---

# 📊 Final Comparison

| Approach   | Detection | External Check |     Time     |    Space     |
| :--------- | :-------- | :------------- | :----------: | :----------: |
| Approach 1 | DFS       | Scan edges     | **O(n + m)** | **O(n + m)** |
| Approach 2 | DFS       | Reverse graph  | **O(n + m)** | **O(n + m)** |

For a GitHub solution, **Approach 1 is recommended** because it is shorter and directly reflects the condition in the problem.

---

# 🎯 Key Takeaways

- ✅ Model methods as nodes of a directed graph.
- ✅ `a -> b` means method `a` invokes method `b`.
- ✅ The suspicious set is exactly the set of nodes reachable from `k`.
- ✅ Use DFS/BFS to find that set.
- ✅ After finding suspicious methods, check for:
  ```text
  non-suspicious → suspicious
  ```
- ✅ If such an edge exists, return all methods unchanged.
- ✅ Otherwise, return every non-suspicious method.
- ✅ The direction of invocation edges matters.
- ✅ A suspicious method invoking a non-suspicious method does not by itself block removal.
- ✅ No repeated edge scanning is required.
- ✅ Time Complexity: **O(n + m)**.
- ✅ Space Complexity: **O(n + m)**.

---

# 🏁 Final Summary

The entire problem can be reduced to two questions:

### Question 1

Which methods are suspicious?

```text
DFS/BFS from k
```

### Question 2

Can the suspicious group be removed?

Check whether there exists:

```text
non-suspicious → suspicious
```

If yes:

```text
Return all methods.
```

If no:

```text
Return all non-suspicious methods.
```

The complete flow is:

```text
                       Method k
                           |
                           v
                    DFS / BFS
                           |
                           v
                 Suspicious Methods
                           |
                           v
                  Check all invocations
                           |
                           v
             Outside → Suspicious exists?
                    /                 \
                  Yes                  No
                   |                    |
                   v                    v
             Remove nothing       Remove suspicious
                   |                    |
                   v                    v
             Return all methods   Return remaining
```

Therefore, the optimal solution is:

```text
Time Complexity  : O(n + m)

Space Complexity : O(n + m)
```

where:

```text
n = number of methods
m = number of invocations
```

---

# 🏷️ Tags

`Graph` `Depth First Search` `Breadth First Search` `Directed Graph` `Reachability` `Graph Traversal` `Hash Table`
