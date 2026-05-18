class State
{
public:
  int value;
  int steps;
  int idx;

  State(int value, int steps, int idx)
  {

    this->value = value;
    this->steps = steps;
    this->idx = idx;
  }
};

class Solution
{
public:
  int minJumps(vector<int> &nums)
  {

    int len = nums.size();

    unordered_map<int, vector<int>> groups;

    for (int pos = 0; pos < len; pos++)
    {

      groups[nums[pos]].push_back(pos);
    }

    vector<bool> seen(len, false);

    queue<State> bfs;

    bfs.push(State(nums[0], 0, 0));

    seen[0] = true;

    while (!bfs.empty())
    {

      State cur = bfs.front();

      bfs.pop();

      int value = cur.value;
      int steps = cur.steps;
      int idx = cur.idx;

      if (idx + 1 < len)
      {

        if (!seen[idx + 1])
        {

          bfs.push(State(nums[idx] + 1, steps + 1, idx + 1));
        }

        seen[idx + 1] = true;
      }

      if (idx - 1 >= 0)
      {

        if (!seen[idx - 1])
        {

          bfs.push(State(nums[idx] - 1, steps + 1, idx - 1));
        }

        seen[idx - 1] = true;
      }

      for (int next : groups[nums[idx]])
      {

        if (next != idx && seen[next] == false)
        {

          bfs.push(State(nums[next], steps + 1, next));
        }

        seen[next] = true;
      }

      groups[nums[idx]].clear();

      if (idx == len - 1)
      {

        return steps;
      }
    }

    return 0;
  }
};