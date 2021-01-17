class Solution {
        public int findTargetSumWays(int[] nums, int S) {
            int sum = 0;
            for (int n : nums) sum += n;
            // 这两种情况，不可能存在合法的子集划分
            if (sum < S || (sum + S) % 2 == 1) {
                return 0;
            }
            return subsets(nums, (sum + S) / 2);
        }

        int subsets(int[] nums, int sum) {
        int n = nums.length;
        int[] dp = new int[sum + 1];
        // base case
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // j 要从后往前遍历
            for (int j = sum; j >= 0; j--) {
                // 状态转移方程
                if (j >= nums[i-1]) {
                    dp[j] = dp[j] + dp[j-nums[i-1]];
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[sum];
    }
    }