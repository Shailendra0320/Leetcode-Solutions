// DFS
class Solution
{
  int n;

  bool dfs(vector<int> &arr, int i)
  {
    if (i < 0 || i >= n || arr[i] < 0)
    {
      return false;
    }
    if (arr[i] == 0)
      return true;

    arr[i] *= -1;
    bool left = dfs(arr, i - arr[i]);
    bool right = dfs(arr, i + arr[i]);
    return left || right;
  }

public:
  bool canReach(vector<int> &arr, int start)
  {
    n = arr.size();
    return dfs(arr, start);
  }
};

// BFS
/*
class Solution {
public:
    bool canReach(vector<int>& arr, int start) {
        int n = arr.size();
        queue<int> que;
        que.push(start);

        while(!que.empty()) {
            int curr = que.front();
            que.pop();

            if(arr[curr] == 0)
                return true;

            if(arr[curr] < 0)
                continue;

            int left  = curr + arr[curr];
            int right = curr - arr[curr];

            if(left >= 0 && left < n)
                que.push(left);
            if(right >= 0 && right < n)
                que.push(right);

            arr[curr] = -arr[curr];
        }

        return false;
    }
};
 */