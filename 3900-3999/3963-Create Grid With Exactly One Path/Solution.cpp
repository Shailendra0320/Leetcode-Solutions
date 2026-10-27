//Approach-1 (Open Top Row and Last Column)
//T.C : O(m * n)
//S.C : O(m * n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<string> createGrid(int m, int n) {
        vector<string> grid(m, string(n, '#'));

        grid[0] = string(n, '.');

        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        return grid;
    }
};

//Approach-2 (Open First Column and Last Row)
//T.C : O(m * n)
//S.C : O(m * n)

class Solution2 {
public:
    vector<string> createGrid(int m, int n) {
        vector<string> grid(m, string(n, '#'));

        for (int i = 0; i < m; i++) {
            grid[i][0] = '.';
        }

        grid[m - 1] = string(n, '.');

        return grid;
    }
};