class Solution
{

  vector<int> prefLen;
  vector<int> sufLen;
  vector<int> maxLen;
  vector<int> rangeLen;

  vector<char> leftChar;
  vector<char> rightChar;

  string str;

  void build(int node, int start, int end)
  {
    if (start == end)
    {
      prefLen[node] = 1;
      sufLen[node] = 1;
      maxLen[node] = 1;
      rangeLen[node] = 1;

      leftChar[node] = str[start];
      rightChar[node] = str[start];

      return;
    }

    int mid = (start + end) / 2;

    build(2 * node, start, mid);
    build(2 * node + 1, mid + 1, end);

    merge(node);
  }

  void merge(int node)
  {
    int left = 2 * node;
    int right = 2 * node + 1;

    leftChar[node] = leftChar[left];
    rightChar[node] = rightChar[right];

    rangeLen[node] =
        rangeLen[left] + rangeLen[right];

    prefLen[node] = prefLen[left];

    if (rightChar[left] == leftChar[right] &&
        prefLen[left] == rangeLen[left])
    {

      prefLen[node] =
          rangeLen[left] + prefLen[right];
    }

    sufLen[node] = sufLen[right];

    if (rightChar[left] == leftChar[right] &&
        sufLen[right] == rangeLen[right])
    {

      sufLen[node] =
          rangeLen[right] + sufLen[left];
    }

    maxLen[node] =
        max(maxLen[left], maxLen[right]);

    if (rightChar[left] == leftChar[right])
    {
      maxLen[node] =
          max(
              maxLen[node],
              sufLen[left] + prefLen[right]);
    }
  }

  void update(
      int node,
      int start,
      int end,
      int idx,
      char ch)
  {
    if (start == end)
    {
      str[idx] = ch;

      leftChar[node] = ch;
      rightChar[node] = ch;

      prefLen[node] = 1;
      sufLen[node] = 1;
      maxLen[node] = 1;

      return;
    }

    int mid = (start + end) / 2;

    if (idx <= mid)
    {
      update(
          2 * node,
          start,
          mid,
          idx,
          ch);
    }
    else
    {
      update(
          2 * node + 1,
          mid + 1,
          end,
          idx,
          ch);
    }

    merge(node);
  }

public:
  vector<int> longestRepeating(
      string s,
      string queryCharacters,
      vector<int> &queryIndices)
  {

    int n = s.length();
    int q = queryIndices.size();

    str = s;

    prefLen.resize(4 * n);
    sufLen.resize(4 * n);
    maxLen.resize(4 * n);
    rangeLen.resize(4 * n);

    leftChar.resize(4 * n);
    rightChar.resize(4 * n);

    build(1, 0, n - 1);

    vector<int> ans(q);

    for (int i = 0; i < q; i++)
    {
      update(
          1,
          0,
          n - 1,
          queryIndices[i],
          queryCharacters[i]);

      ans[i] = maxLen[1];
    }

    return ans;
  }
};