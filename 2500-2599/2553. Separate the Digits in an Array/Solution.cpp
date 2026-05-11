class Solution {
public:
    vector<int> separateDigits(vector<int>& nums) {
        string s = "";

        for (int i = 0; i < nums.size(); i++) {
            s += to_string(nums[i]);
        }

        vector<int> result(s.length());

        for (int i = 0; i < s.length(); i++) {
            result[i] = s[i] - '0';
        }

        return result;
    }
};