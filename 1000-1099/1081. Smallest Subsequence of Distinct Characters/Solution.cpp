//Approach-1 (Greedy + Monotonic Stack)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    string smallestSubsequence(
        string s
    ) {

        vector<int> lastIndex(
            26
        );

        vector<bool> inStack(
            26,
            false
        );

        string stack = "";

        for (
            int i = 0;
            i < s.size();
            i++
        ) {

            lastIndex[
                s[i] - 'a'
            ] = i;
        }

        for (
            int i = 0;
            i < s.size();
            i++
        ) {

            int current =
                s[i] - 'a';

            if (
                inStack[current]
            ) {

                continue;
            }

            while (
                !stack.empty()
            ) {

                int top =
                    stack.back() - 'a';

                if (
                    top > current &&
                    lastIndex[top] > i
                ) {

                    inStack[top] =
                        false;

                    stack.pop_back();

                } else {

                    break;
                }
            }

            stack.push_back(
                s[i]
            );

            inStack[current] =
                true;
        }

        return stack;
    }
};


/*
//Approach-2 (Using std::stack)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    string smallestSubsequence(
        string s
    ) {

        vector<int> lastIndex(
            26
        );

        vector<bool> visited(
            26,
            false
        );

        stack<char> st;

        for (
            int i = 0;
            i < s.size();
            i++
        ) {

            lastIndex[
                s[i] - 'a'
            ] = i;
        }

        for (
            int i = 0;
            i < s.size();
            i++
        ) {

            char current =
                s[i];

            if (
                visited[current - 'a']
            ) {

                continue;
            }

            while (
                !st.empty() &&
                st.top() > current &&
                lastIndex[st.top() - 'a'] > i
            ) {

                visited[
                    st.top() - 'a'
                ] = false;

                st.pop();
            }

            st.push(
                current
            );

            visited[
                current - 'a'
            ] = true;
        }

        string answer = "";

        while (
            !st.empty()
        ) {

            answer +=
                st.top();

            st.pop();
        }

        reverse(
            answer.begin(),
            answer.end()
        );

        return answer;
    }
};
*/