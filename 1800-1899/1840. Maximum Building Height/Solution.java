//Approach-1 (Peak Formula)
//T.C : O(m log m)
//S.C : O(m)

class Solution {

  public int maxBuilding(int n, int[][] restrictions) {

    List<int[]> list = new ArrayList<>();

    for (int[] r : restrictions) {
      list.add(r);
    }

    list.add(new int[] { 1, 0 });
    list.add(new int[] { n, n - 1 });

    Collections.sort(list, (a, b) -> a[0] - b[0]);

    int size = list.size();

    for (int i = 1; i < size; i++) {

      int[] prev = list.get(i - 1);
      int[] curr = list.get(i);

      curr[1] = Math.min(
          curr[1],
          prev[1] + (curr[0] - prev[0]));
    }

    for (int i = size - 2; i >= 0; i--) {

      int[] curr = list.get(i);
      int[] next = list.get(i + 1);

      curr[1] = Math.min(
          curr[1],
          next[1] + (next[0] - curr[0]));
    }

    int maxHeight = 0;

    for (int i = 1; i < size; i++) {

      int[] prev = list.get(i - 1);
      int[] curr = list.get(i);

      int distance = curr[0] - prev[0];

      int peak = (distance + prev[1] + curr[1]) / 2;

      maxHeight = Math.max(maxHeight, peak);
    }

    return maxHeight;
  }
}

/*
 * //Approach-2 (Alternative Peak Formula)
 * //T.C : O(m log m)
 * //S.C : O(m)
 * 
 * class Solution {
 * 
 * public int maxBuilding(int n, int[][] restrictions) {
 * 
 * List<int[]> list = new ArrayList<>();
 * 
 * list.add(new int[]{1,0});
 * 
 * for(int[] restriction : restrictions){
 * list.add(restriction);
 * }
 * 
 * Collections.sort(list,(a,b)->a[0]-b[0]);
 * 
 * if(list.get(list.size()-1)[0] != n){
 * list.add(new int[]{n,n-1});
 * }
 * 
 * for(int i=1;i<list.size();i++){
 * 
 * list.get(i)[1] =
 * Math.min(
 * list.get(i)[1],
 * list.get(i-1)[1]
 * +
 * list.get(i)[0]
 * -
 * list.get(i-1)[0]
 * );
 * }
 * 
 * for(int i=list.size()-2;i>=0;i--){
 * 
 * list.get(i)[1] =
 * Math.min(
 * list.get(i)[1],
 * list.get(i+1)[1]
 * +
 * list.get(i+1)[0]
 * -
 * list.get(i)[0]
 * );
 * }
 * 
 * int result = 0;
 * 
 * for(int i=1;i<list.size();i++){
 * 
 * int h1 = list.get(i-1)[1];
 * int h2 = list.get(i)[1];
 * 
 * int x = list.get(i-1)[0];
 * int y = list.get(i)[0];
 * 
 * result =
 * Math.max(
 * result,
 * Math.max(h1,h2)
 * +
 * (
 * y-x-
 * Math.abs(h1-h2)
 * )/2
 * );
 * }
 * 
 * return result;
 * }
 * }
 */