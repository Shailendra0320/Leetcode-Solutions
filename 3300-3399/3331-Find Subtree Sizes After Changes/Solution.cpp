#include <bits/stdc++.h>
using namespace std;

class Solution {
public:

    //Approach-1 (Build Modified Tree + Reverse DFS)
    //T.C: O(n)
    //S.C: O(n)

    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = parent.size();

        vector<vector<int>> children(n);

        for (int i = 1; i < n; i++) {
            children[parent[i]].push_back(i);
        }

        vector<int> newParent(n, -1);
        vector<int> last(26, -1);
        vector<int> previousSame(n, -1);

        vector<int> st = {0};

        while (!st.empty()) {
            int event = st.back();
            st.pop_back();

            if (event >= 0) {
                int u = event;
                int c = s[u] - 'a';

                previousSame[u] = last[c];

                if (u != 0) {
                    newParent[u] = last[c] != -1 ? last[c] : parent[u];
                }

                last[c] = u;
                st.push_back(~u);

                for (int v : children[u]) {
                    st.push_back(v);
                }
            } else {
                int u = ~event;
                last[s[u] - 'a'] = previousSame[u];
            }
        }

        vector<vector<int>> modified(n);

        for (int i = 1; i < n; i++) {
            modified[newParent[i]].push_back(i);
        }

        vector<int> order;
        st = {0};

        while (!st.empty()) {
            int u = st.back();
            st.pop_back();

            order.push_back(u);

            for (int v : modified[u]) {
                st.push_back(v);
            }
        }

        vector<int> ans(n, 1);

        for (int i = (int)order.size() - 1; i > 0; i--) {
            int u = order[i];
            ans[newParent[u]] += ans[u];
        }

        return ans;
    }
};


class Solution2 {
public:

    //Approach-2 (Single DFS + Direct Subtree Accumulation)
    //T.C: O(n)
    //S.C: O(n)

    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = parent.size();

        vector<vector<int>> children(n);

        for (int i = 1; i < n; i++) {
            children[parent[i]].push_back(i);
        }

        vector<int> last(26, -1);
        vector<int> previousSame(n, -1);
        vector<int> ans(n, 1);

        vector<int> st = {0};

        while (!st.empty()) {
            int event = st.back();
            st.pop_back();

            if (event >= 0) {
                int u = event;
                int c = s[u] - 'a';

                previousSame[u] = last[c];
                last[c] = u;

                st.push_back(~u);

                for (int v : children[u]) {
                    st.push_back(v);
                }
            } else {
                int u = ~event;

                int target = previousSame[u] != -1
                        ? previousSame[u]
                        : parent[u];

                if (target != -1) {
                    ans[target] += ans[u];
                }

                last[s[u] - 'a'] = previousSame[u];
            }
        }

        return ans;
    }
};