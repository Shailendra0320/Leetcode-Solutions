//Approach-1 (Day-of-Year + Interval Intersection)
//T.C : O(1)
//S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    int dayOfYear(const string& date) {
        int month = stoi(date.substr(0, 2));
        int day = stoi(date.substr(3, 2));

        int prefix[] = {
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        };

        return prefix[month - 1] + day;
    }

public:
    int countDaysTogether(
        string arriveAlice,
        string leaveAlice,
        string arriveBob,
        string leaveBob
    ) {
        int startAlice = dayOfYear(arriveAlice);
        int endAlice = dayOfYear(leaveAlice);
        int startBob = dayOfYear(arriveBob);
        int endBob = dayOfYear(leaveBob);

        int start = max(startAlice, startBob);
        int end = min(endAlice, endBob);

        return max(0, end - start + 1);
    }
};

//Approach-2 (Enumerate Every Calendar Day)
//T.C : O(365) = O(1)
//S.C : O(1)

class Solution2 {
private:
    int dayOfYear(const string& date) {
        int month = stoi(date.substr(0, 2));
        int day = stoi(date.substr(3, 2));

        int prefix[] = {
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        };

        return prefix[month - 1] + day;
    }

public:
    int countDaysTogether(
        string arriveAlice,
        string leaveAlice,
        string arriveBob,
        string leaveBob
    ) {
        int startAlice = dayOfYear(arriveAlice);
        int endAlice = dayOfYear(leaveAlice);
        int startBob = dayOfYear(arriveBob);
        int endBob = dayOfYear(leaveBob);

        int answer = 0;

        for (int day = 1; day <= 365; day++) {
            if (day >= startAlice && day <= endAlice &&
                day >= startBob && day <= endBob) {
                answer++;
            }
        }

        return answer;
    }
};