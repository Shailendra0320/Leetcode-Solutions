//Approach-1 (Linear Scan and Case Analysis)
//T.C : O(n)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minOperations(string s) {
        int n = s.size();
        bool sorted = true;

        char minChar = s[0];
        char maxChar = s[0];

        for (int i = 1; i < n; i++) {
            minChar = min(minChar, s[i]);
            maxChar = max(maxChar, s[i]);

            if (s[i] < s[i - 1]) {
                sorted = false;
            }
        }

        if (sorted) {
            return 0;
        }

        string sorunavile = s;

        if (n == 2) {
            return -1;
        }

        if (sorunavile[0] == minChar ||
            sorunavile[n - 1] == maxChar) {
            return 1;
        }

        for (int i = 1; i < n - 1; i++) {
            if (sorunavile[i] == minChar ||
                sorunavile[i] == maxChar) {
                return 2;
            }
        }

        return 3;
    }
};


//Approach-2 (Sort Target and Check Boundaries)
//T.C : O(n log n)
//S.C : O(n)

class Solution2 {
public:
    int minOperations(string s) {
        string target = s;
        sort(target.begin(), target.end());

        if (s == target) {
            return 0;
        }

        string sorunavile = s;
        int n = sorunavile.size();

        if (n == 2) {
            return -1;
        }

        char minChar = target[0];
        char maxChar = target[n - 1];

        if (sorunavile[0] == minChar ||
            sorunavile[n - 1] == maxChar) {
            return 1;
        }

        for (int i = 1; i < n - 1; i++) {
            if (sorun