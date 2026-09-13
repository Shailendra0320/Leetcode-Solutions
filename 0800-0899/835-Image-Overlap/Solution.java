import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Approach-1 (Brute Force Translation / Simulation)
//T.C : O(n^4)
//S.C : O(1)

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;
        int answer = 0;

        for (int dr = -(n - 1); dr <= n - 1; dr++) {
            for (int dc = -(n - 1); dc <= n - 1; dc++) {

                int overlap = 0;

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {

                        int nr = r + dr;
                        int nc = c + dc;

                        if (nr < 0 || nr >= n ||
                            nc < 0 || nc >= n) {
                            continue;
                        }

                        if (img1[r][c] == 1 && img2[nr][nc] == 1) {
                            overlap++;
                        }
                    }
                }

                answer = Math.max(answer, overlap);
            }
        }

        return answer;
    }
}


//Approach-2 (Coordinates + Frequency Map)
//T.C : O(n^2 + k1 * k2)
//S.C : O(k1 * k2)

class Solution2 {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                if (img1[r][c] == 1) {
                    ones1.add(new int[]{r, c});
                }

                if (img2[r][c] == 1) {
                    ones2.add(new int[]{r, c});
                }
            }
        }

        Map<String, Integer> frequency = new HashMap<>();

        int answer = 0;

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {

                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];

                String key = dr + "," + dc;

                int count = frequency.getOrDefault(key, 0) + 1;

                frequency.put(key, count);

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }
}