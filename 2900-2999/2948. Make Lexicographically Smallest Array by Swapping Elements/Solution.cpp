// ============================================================
// Approach 1: Sort pairs + Group elements
// Time: O(n log n)
// Space: O(n)
// ============================================================

class Solution
{
public:
  vector<int> lexicographicallySmallestArray(
      vector<int> &nums,
      int limit)
  {

    int n = nums.size();

    // Store {value, original index}
    vector<pair<int, int>> arr;

    for (int i = 0; i < n; i++)
    {
      arr.push_back({nums[i], i});
    }

    // Sort by value
    sort(arr.begin(), arr.end());

    vector<int> result(n);

    int i = 0;

    while (i < n)
    {

      int j = i;

      // Find all elements in the same group
      while (j + 1 < n &&
             arr[j + 1].first - arr[j].first <= limit)
      {
        j++;
      }

      // Store original indices of this group
      vector<int> indices;

      for (int k = i; k <= j; k++)
      {
        indices.push_back(arr[k].second);
      }

      // Smallest values should go to smallest indices
      sort(indices.begin(), indices.end());

      for (int k = 0; k < indices.size(); k++)
      {
        result[indices[k]] = arr[i + k].first;
      }

      i = j + 1;
    }

    return result;
  }
};

// ============================================================
// Approach 2: DSU / Union-Find
// Time: O(n log n)
// Space: O(n)
// ============================================================

class Solution
{
public:
  vector<int> lexicographicallySmallestArray(
      vector<int> &nums,
      int limit)
  {

    int n = nums.size();

    vector<pair<int, int>> arr;

    for (int i = 0; i < n; i++)
    {
      arr.push_back({nums[i], i});
    }

    // Sort by value
    sort(arr.begin(), arr.end());

    DSU dsu(n);

    // Connect elements belonging to the same group
    for (int i = 1; i < n; i++)
    {

      if (arr[i].first - arr[i - 1].first <= limit)
      {
        dsu.unite(arr[i].second, arr[i - 1].second);
      }
    }

    // Group original indices
    unordered_map<int, vector<int>> groups;

    for (int i = 0; i < n; i++)
    {
      groups[dsu.find(i)].push_back(i);
    }

    // Group values
    unordered_map<int, vector<int>> values;

    for (auto &[value, index] : arr)
    {
      values[dsu.find(index)].push_back(value);
    }

    vector<int> result(n);

    for (auto &[parent, indices] : groups)
    {

      // Sort indices
      sort(indices.begin(), indices.end());

      // Sort values
      vector<int> &vals = values[parent];
      sort(vals.begin(), vals.end());

      // Assign smallest values to smallest indices
      for (int i = 0; i < indices.size(); i++)
      {
        result[indices[i]] = vals[i];
      }
    }

    return result;
  }

private:
  // ============================================================
  // DSU / Union-Find implementation
  // ============================================================

  class DSU
  {

  public:
    vector<int> parent;
    vector<int> rank;

    DSU(int n)
    {

      parent.resize(n);
      rank.resize(n, 0);

      for (int i = 0; i < n; i++)
      {
        parent[i] = i;
      }
    }

    int find(int x)
    {

      if (parent[x] != x)
      {
        parent[x] = find(parent[x]);
      }

      return parent[x];
    }

    void unite(int a, int b)
    {

      int pa = find(a);
      int pb = find(b);

      if (pa == pb)
      {
        return;
      }

      if (rank[pa] < rank[pb])
      {
        parent[pa] = pb;
      }
      else if (rank[pa] > rank[pb])
      {
        parent[pb] = pa;
      }
      else
      {
        parent[pb] = pa;
        rank[pa]++;
      }
    }
  };
};