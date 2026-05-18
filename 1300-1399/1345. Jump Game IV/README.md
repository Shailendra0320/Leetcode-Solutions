class Solution {
public int minJumps(int[] nums) {
int len = nums.length;

        HashMap<Integer, List<Integer>> groups = new HashMap<>();

        for (int pos = 0; pos < len; pos++) {
            if (!groups.containsKey(nums[pos])) {
                groups.put(nums[pos], new ArrayList<>());
            }

            groups.get(nums[pos]).add(pos);
        }

        boolean[] seen = new boolean[len];

        Queue<State> bfs = new LinkedList<>();

        bfs.add(new State(nums[0], 0, 0));

        seen[0] = true;

        while (!bfs.isEmpty()) {

            State cur = bfs.poll();

            int value = cur.value;
            int steps = cur.steps;
            int idx = cur.idx;

            if (idx + 1 < len) {
                if (!seen[idx + 1]) {
                    bfs.add(new State(nums[idx] + 1, steps + 1, idx + 1));
                }

                seen[idx + 1] = true;
            }

            if (idx - 1 >= 0) {
                if (!seen[idx - 1]) {
                    bfs.add(new State(nums[idx] - 1, steps + 1, idx - 1));
                }

                seen[idx - 1] = true;
            }

            for (int next : groups.get(nums[idx])) {
                if (next != idx && seen[next] == false) {
                    bfs.add(new State(nums[next], steps + 1, next));
                }

                seen[next] = true;
            }

            groups.get(nums[idx]).clear();

            if (idx == len - 1) {
                return steps;
            }
        }

        return 0;
    }

}

class State {

    int value;
    int steps;
    int idx;

    State(int value, int steps, int idx) {
        this.value = value;
        this.steps = steps;
        this.idx = idx;
    }

}
