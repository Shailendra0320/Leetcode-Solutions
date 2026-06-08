//Approach-1 (Using Three Vectors)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    vector<int> pivotArray(vector<int>& nums, int pivot) {

        vector<int> smaller;

        vector<int> larger;

        vector<int> equal;

        for (int num : nums) {

            if (num < pivot) {

                smaller.push_back(num);
            }
            else if (num > pivot) {

                larger.push_back(num);
            }
            else {

                equal.push_back(num);
            }
        }

        smaller.insert(
            smaller.end(),
            equal.begin(),
            equal.end()
        );

        smaller.insert(
            smaller.end(),
            larger.begin(),
            larger.end()
        );

        return smaller;
    }
};


/*
//Approach-2 (Using Result Array)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    vector<int> pivotArray(vector<int>& nums, int pivot) {

        int n = nums.size();

        vector<int> result(n);

        int index = 0;

        for (int num : nums) {

            if (num < pivot) {

                result[index++] = num;
            }
        }

        for (int num : nums) {

            if (num == pivot) {

                result[index++] = num;
            }
        }

        for (int num : nums) {

            if (num > pivot) {

                result[index++] = num;
            }
        }

        return result;
    }
};
*/