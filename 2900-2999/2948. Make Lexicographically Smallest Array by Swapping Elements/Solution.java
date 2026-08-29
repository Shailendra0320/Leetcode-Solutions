import java.util.*;

class Solution {

  // ============================================================
  // Approach 1: Sort indices + Group elements
  // Time: O(n log n)
  // Space: O(n)
  // ============================================================

  public int[] lexicographicallySmallestArray(int[] nums, int limit) {

    int n = nums.length;

    // Store {value, original index}
    int[][] arr = new int[n][2];

    for (int i = 0; i < n; i++) {
      arr[i][0] = nums[i];
      arr[i][1] = i;
    }

    // Sort according to value
    Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

    int[] result = new int[n];

    int i = 0;

    while (i < n) {

      int j = i;

      // Find all values belonging to the same group.
      // Consecutive values can be swapped if their difference <= limit.
      while (j + 1 < n &&
          arr[j + 1][0] - arr[j][0] <= limit) {
        j++;
      }

      // Original indices of this group
      List<Integer> indices = new ArrayList<>();

      for (int k = i; k <= j; k++) {
        indices.add(arr[k][1]);
      }

      // Sort original indices
      Collections.sort(indices);

      // Values are already sorted because arr is sorted
      for (int k = 0; k < indices.size(); k++) {
        result[indices.get(k)] = arr[i + k][0];
      }

      i = j + 1;
    }

    return result;
  }

  // ============================================================
  // Approach 2: DSU / Union-Find
  // Time: O(n log n)
  // Space: O(n)
  // ============================================================

  public int[] lexicographicallySmallestArrayDSU(int[] nums, int limit) {

    int n = nums.length;

    int[][] arr = new int[n][2];

    for (int i = 0; i < n; i++) {
      arr[i][0] = nums[i];
      arr[i][1] = i;
    }

    Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

    DSU dsu = new DSU(n);

    // Connect elements that can belong to the same group
    for (int i = 1; i < n; i++) {

      if (arr[i][0] - arr[i - 1][0] <= limit) {
        dsu.union(arr[i][1], arr[i - 1][1]);
      }
    }

    // Group values by their DSU parent
    Map<Integer, List<Integer>> groups = new HashMap<>();

    for (int i = 0; i < n; i++) {
      int parent = dsu.find(i);

      groups.computeIfAbsent(parent, x -> new ArrayList<>())
          .add(i);
    }

    // Sort indices inside every group
    for (List<Integer> list : groups.values()) {
      Collections.sort(list);
    }

    // Values can be collected group-wise
    Map<Integer, List<Integer>> values = new HashMap<>();

    for (int i = 0; i < n; i++) {
      int parent = dsu.find(arr[i][1]);

      values.computeIfAbsent(parent, x -> new ArrayList<>())
          .add(arr[i][0]);
    }

    int[] result = new int[n];

    for (int parent : groups.keySet()) {

      List<Integer> indices = groups.get(parent);
      List<Integer> vals = values.get(parent);

      Collections.sort(vals);

      for (int i = 0; i < indices.size(); i++) {
        result[indices.get(i)] = vals.get(i);
      }
    }

    return result;
  }

  // DSU implementation
  class DSU {

    int[] parent;
    int[] rank;

    DSU(int n) {
      parent = new int[n];
      rank = new int[n];

      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    int find(int x) {

      if (parent[x] != x) {
        parent[x] = find(parent[x]);
      }

      return parent[x];
    }

    void union(int a, int b) {

      int pa = find(a);
      int pb = find(b);

      if (pa == pb) {
        return;
      }

      if (rank[pa] < rank[pb]) {
        parent[pa] = pb;
      } else if (rank[pa] > rank[pb]) {
        parent[pb] = pa;
      } else {
        parent[pb] = pa;
        rank[pa]++;
      }
    }
  }
}