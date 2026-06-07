/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right)
 *         : val(x), left(left), right(right) {}
 * };
 */

class Solution {
public:

    TreeNode* createBinaryTree(vector<vector<int>>& descriptions) {

        unordered_map<int, TreeNode*> nodeMap;

        unordered_set<int> children;

        for (auto& desc : descriptions) {

            int parentVal = desc[0];
            int childVal  = desc[1];
            int isLeft    = desc[2];

            if (!nodeMap.count(parentVal)) {
                nodeMap[parentVal] = new TreeNode(parentVal);
            }

            if (!nodeMap.count(childVal)) {
                nodeMap[childVal] = new TreeNode(childVal);
            }

            children.insert(childVal);

            if (isLeft == 1) {
                nodeMap[parentVal]->left = nodeMap[childVal];
            }
            else {
                nodeMap[parentVal]->right = nodeMap[childVal];
            }
        }

        int rootVal = -1;

        for (auto& desc : descriptions) {

            int parentVal = desc[0];

            if (!children.count(parentVal)) {

                rootVal = parentVal;
                break;
            }
        }

        return nodeMap[rootVal];
    }
};