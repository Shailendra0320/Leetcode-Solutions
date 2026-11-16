//Approach-1 (Iterative Simulation)
//T.C: O(n)
//S.C: O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string digitSum(string s, int k) {
        while (s.length() > k) {
            string next = "";

            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;

                for (int j = i; j < min(i + k, (int)s.length()); j++) {
                    sum += s[j] - '0';
                }

                next += to_string(sum);
            }

            s = next;
        }

        return s;
    }
};


//Approach-2 (Recursive Simulation)
//T.C: O(n)
//S.C: O(n)

class Solution2 {
public:
    string digitSum(string s, int k) {
        if (s.length() <= k) {
            return s;
        }

        string next = "";

        for (int i = 0; i < s.length(); i += k) {
            int sum = 0;

            for (int j = i; j < min(i + k, (int)s.length()); j++) {
                sum += s[j] - '0';
            }

            next += to_string(sum);
        }

        return digitSum(next, k);
    }
};