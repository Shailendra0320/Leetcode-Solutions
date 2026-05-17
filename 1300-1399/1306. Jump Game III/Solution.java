class Solution {
    int n;

    private boolean dfs(int[] arr, int i) {
        if(i < 0 || i >= n || arr[i] < 0) {
            return false;
        }
        if(arr[i] == 0)
            return true;

        arr[i] *= -1;
        boolean left  = dfs(arr, i - arr[i]);
        boolean right = dfs(arr, i + arr[i]);
        return left || right;
    }

    public boolean canReach(int[] arr, int start) {
        n = arr.length;
        return dfs(arr, start);
    }
}


/*
class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        while(!que.isEmpty()) {
            int curr = que.poll();

            if(arr[curr] == 0)
                return true;

            if(arr[curr] < 0)
                continue;

            int left  = curr + arr[curr];
            int right = curr - arr[curr];

            if(left >= 0 && left < n)
                que.add(left);
            if(right >= 0 && right < n)
                que.add(right);

            arr[curr] = -arr[curr];
        }

        return false;
    }
}
*/