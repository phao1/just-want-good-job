class Solution {
	HashMap<Integer, Integer> memo = new HashMap<>();

	public int coinChange(int[] coins, int amount) {
		return dfs(coins, amount);
	}

	private int dfs(int[] coins, int sum) {
		if (memo.containsKey(sum)) {
			return memo.get(sum);
		}
		if (sum == 0) {
			return 0;
		}
		if (sum < 0) {
			return -1;
		}
		int res = Integer.MAX_VALUE;
		for (int coin : coins) {
			int subproblem = dfs(coins, sum - coin);
			if (subproblem == -1) {
				continue;
			}
			res = Math.min(subproblem + 1, res);
		}
		if (res == Integer.MAX_VALUE) {
			memo.put(sum, -1);
		} else {
			memo.put(sum, res);
		}
		return memo.get(sum);
	}

	public int coinChange3(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i < coins[j]) {
					continue;
				}
				dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
			}
		}
		return (dp[amount] == amount + 1) ? -1 : dp[amount];
	}
}