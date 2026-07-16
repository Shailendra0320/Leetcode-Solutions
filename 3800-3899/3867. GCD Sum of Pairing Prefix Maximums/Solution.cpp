//Approach-1 (Prefix GCD + Sorting)
//T.C : O(n log n)
//S.C : O(n)

class Solution {
public:

    long long gcdSum(
        vector<int>& nums
    ) {

        int n = nums.size();

        vector<int> prefixGcd(
            n
        );

        int maximum = 0;

        for (
            int i = 0;
            i < n;
            i++
        ) {

            maximum =
                max(
                    maximum,
                    nums[i]
                );

            prefixGcd[i] =
                gcd(
                    nums[i],
                    maximum
                );
        }

        sort(
            prefixGcd.begin(),
            prefixGcd.end()
        );

        long long answer = 0;

        for (
            int i = 0;
            i < n / 2;
            i++
        ) {

            answer +=
                gcd(
                    prefixGcd[i],
                    prefixGcd[n - i - 1]
                );
        }

        return answer;
    }

private:

    int gcd(
        int a,
        int b
    ) {

        while (b != 0) {

            int temp =
                a % b;

            a = b;

            b = temp;
        }

        return a;
    }
};


/*
//Approach-2 (Brute Force Simulation)
//T.C : O(n log n)
//S.C : O(n)

class Solution {
public:

    long long gcdSum(
        vector<int>& nums
    ) {

        int n = nums.size();

        vector<int> values;

        int maximum = 0;

        for (int value : nums) {

            maximum =
                max(
                    maximum,
                    value
                );

            values.push_back(
                gcd(
                    value,
                    maximum
                )
            );
        }

        sort(
            values.begin(),
            values.end()
        );

        long long answer = 0;

        int left = 0;

        int right = n - 1;

        while (left < right) {

            answer +=
                gcd(
                    values[left],
                    values[right]
                );

            left++;

            right--;
        }

        return answer;
    }

private:

    int gcd(
        int a,
        int b
    ) {

        while (b != 0) {

            int temp =
                a % b;

            a = b;

            b = temp;
        }

        return a;
    }
};
*/