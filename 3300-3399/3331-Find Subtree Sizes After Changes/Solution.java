import java.util.*;

class Solution {

    //Approach-1 (Build Modified Tree + Reverse DFS)
    //T.C: O(n)
    //S.C: O(n)

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        int[] last = new int[26];
        int[] previousSame = new int[n];

        Arrays.fill(last, -1);
        Arrays.fill(newParent, -1);

        int[] stack = new int[2 * n];
        int top = 0;
        stack[top++] = 0;

        while (top > 0) {
            int event = stack[--top];

            if (event >= 0) {
                int u = event;
                int c = s.charAt(u) - 'a';

                previousSame[u] = last[c];

                if (u != 0) {
                    newParent[u] = last[c] != -1 ? last[c] : parent[u];
                }

                last[c] = u;
                stack[top++] = ~u;

                for (int v : children[u]) {
                    stack[top++] = v;
                }
            } else {
                int u = ~event;
                last[s.charAt(u) - 'a'] = previousSame[u];
            }
        }

        List<Integer>[] modified = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            modified[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            modified[newParent[i]].add(i);
        }

        int[] order = new int[n];
        top = 0;
        int count = 0;
        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : modified[u]) {
                stack[top++] = v;
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = count - 1; i > 0; i--) {
            int u = order[i];
            ans[newParent[u]] += ans[u];
        }

        return ans;
    }
}


class Solution2 {

    //Approach-2 (Single DFS + Direct Subtree Accumulation)
    //T.C: O(n)
    //S.C: O(n)

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] last = new int[26];
        int[] previousSame = new int[n];
        int[] ans = new int[n];

        Arrays.fill(last, -1);
        Arrays.fill(ans, 1);

        int[] stack = new int[2 * n];
        int top = 0;
        stack[top++] = 0;

        while (top > 0) {
            int event = stack[--top];

            if (event >= 0) {
                int u = event;
                int c = s.charAt(u) - 'a';

                previousSame[u] = last[c];
                last[c] = u;

                stack[top++] = ~u;

                for (int v : children[u]) {
                    stack[top++] = v;
                }
            } else {
                int u = ~event;
                int target = previousSame[u] != -1
                        ? previousSame[u]
                        : parent[u];

                if (target != -1) {
                    ans[target] += ans[u];
                }

                last[s.charAt(u) - 'a'] = previousSame[u];
            }
        }

        return ans;
    }
}