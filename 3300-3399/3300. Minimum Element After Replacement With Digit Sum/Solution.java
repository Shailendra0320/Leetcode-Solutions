class Solution {
    public int minElement(int[] nums) {
        int[] newArr = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int digit = nums[i];
            while (digit > 0) {
                sum += digit % 10;
                digit = digit / 10;
            }
            newArr[i] = sum;
        }
        
        Arrays.sort(newArr);
        return newArr[0];
    }
}


/*
class Solution {
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;
        
        for (int num : nums) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            min = Math.min(min, sum);
        }
        
        return min;
    }
}
*/