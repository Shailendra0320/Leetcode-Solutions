// Approach-1 (0-1 BFS using Deque)
// T.C : O(m × n)
// S.C : O(m × n)

class Solution
{
public:
  vector<vector<int>> directions{
      {0, 1},
      {0, -1},
      {1, 0},
      {-1, 0}};

  bool findSafeWalk(vector<vector<int>> &grid, int health)
  {

    int rows = grid.size();

    int cols = grid[0].size();

    vector<vector<int>> cost(
        rows,
        vector<int>(cols, INT_MAX));

    deque<vector<int>> dequeQueue;

    int startCost = grid[0][0];

    cost[0][0] = startCost;

    dequeQueue.push_front(
        {0, 0, startCost});

    while (!dequeQueue.empty())
    {

      auto current = dequeQueue.front();

      dequeQueue.pop_front();

      int row = current[0];

      int col = current[1];

      int healthUsed = current[2];

      if (healthUsed > cost[row][col])
      {
        continue;
      }

      for (auto &direction : directions)
      {

        int nextRow = row + direction[0];

        int nextCol = col + direction[1];

        if (
            nextRow >= 0 &&
            nextRow < rows &&
            nextCol >= 0 &&
            nextCol < cols)
        {

          int cellCost = grid[nextRow][nextCol];

          int newCost = healthUsed + cellCost;

          if (newCost < cost[nextRow][nextCol])
          {

            cost[nextRow][nextCol] = newCost;

            if (cellCost == 0)
            {

              dequeQueue.push_front(
                  {nextRow, nextCol, newCost});
            }
            else
            {

              dequeQueue.push_back(
                  {nextRow, nextCol, newCost});
            }
          }
        }
      }
    }

    return cost[rows - 1][cols - 1] < health;
  }
};

/*
//Approach-2 (Dijkstra using Priority Queue)
//T.C : O(m × n log(m × n))
//S.C : O(m × n)

class Solution {
public:

    vector<vector<int>> directions{
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    bool findSafeWalk(vector<vector<int>>& grid, int health) {

        int rows = grid.size();

        int cols = grid[0].size();

        vector<vector<int>> cost(
            rows,
            vector<int>(cols, INT_MAX)
        );

        priority_queue<
            vector<int>,
            vector<vector<int>>,
            greater<vector<int>>
        > priorityQueue;

        int startCost = grid[0][0];

        cost[0][0] = startCost;

        priorityQueue.push(
            {startCost, 0, 0}
        );

        while (!priorityQueue.empty()) {

            auto current = priorityQueue.top();

            priorityQueue.pop();

            int healthUsed = current[0];

            int row = current[1];

            int col = current[2];

            if (healthUsed > cost[row][col]) {
                continue;
            }

            if (
                row == rows - 1 &&
                col == cols - 1
            ) {
                return healthUsed < health;
            }

            for (auto &direction : directions) {

                int nextRow = row + direction[0];

                int nextCol = col + direction[1];

                if (
                    nextRow >= 0 &&
                    nextRow < rows &&
                    nextCol >= 0 &&
                    nextCol < cols
                ) {

                    int newCost =
                        healthUsed +
                        grid[nextRow][nextCol];

                    if (
                        newCost <
                        cost[nextRow][nextCol]
                    ) {

                        cost[nextRow][nextCol] =
                            newCost;

                        priorityQueue.push(
                            {
                                newCost,
                                nextRow,
                                nextCol
                            }
                        );
                    }
                }
            }
        }

        return false;
    }
};