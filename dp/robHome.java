import java.util.HashMap;
import java.util.Map;

class test {
	/**
	 * res = Math.max(dp(nums, start+1) + dp(nums, start) + nums[start+2]) ;
	 * 
	 * @author hao
	 *
	 */
	class Solution {
		/**
		 * 198. House Robber You are a professional robber planning to rob houses along
		 * a street. Each house has a certain amount of money stashed, the only
		 * constraint stopping you from robbing each of them is that adjacent houses
		 * have security system connected and it will automatically contact the police
		 * if two adjacent houses were broken into on the same night.
		 * 
		 * Given a list of non-negative integers representing the amount of money of
		 * each house, determine the maximum amount of money you can rob tonight without
		 * alerting the police.
		 * 
		 * 
		 * 
		 * Example 1:
		 * 
		 * Input: nums = [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and
		 * then rob house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.
		 * 
		 * @param nums
		 * @return
		 */

		class Solution1 {
			private int[] memo;

			// 主函数
			public int rob(int[] nums) {
				// 初始化备忘录
				memo = new int[nums.length];
				Arrays.fill(memo, -1);
				// 强盗从第 0 间房子开始抢劫
				return dp(nums, 0);
			}

			// 返回 dp[start..] 能抢到的最大值
			private int dp(int[] nums, int start) {
				if (start >= nums.length) {
					return 0;
				}
				// 避免重复计算
				if (memo[start] != -1)
					return memo[start];

				int res = Math.max(dp(nums, start + 1), nums[start] + dp(nums, start + 2));
				// 记入备忘录
				memo[start] = res;
				return res;
			}
		}

		/**
		 * 213. House Robber II You are a professional robber planning to rob houses
		 * along a street. Each house has a certain amount of money stashed. All houses
		 * at this place are arranged in a circle. That means the first house is the
		 * neighbor of the last one. Meanwhile, adjacent houses have a security system
		 * connected, and it will automatically contact the police if two adjacent
		 * houses were broken into on the same night.
		 * 
		 * Given a list of non-negative integers nums representing the amount of money
		 * of each house, return the maximum amount of money you can rob tonight without
		 * alerting the police.
		 * 
		 * 
		 * 
		 * Example 1:
		 * 
		 * Input: nums = [2,3,2] Output: 3 Explanation: You cannot rob house 1 (money =
		 * 2) and then rob house 3 (money = 2), because they are adjacent houses.
		 * 
		 * @param nums
		 * @return
		 */
		class Solution2 {
			public int rob(int[] nums) {
				int n = nums.length;
				if (n == 1)
					return nums[0];
				return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
			}

			// 仅计算闭区间 [start,end] 的最优结果
			int robRange(int[] nums, int start, int end) {
				int n = nums.length;
				int dp_i_1 = 0, dp_i_2 = 0;
				int dp_i = 0;
				for (int i = end; i >= start; i--) {
					dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
					dp_i_2 = dp_i_1;
					dp_i_1 = dp_i;
				}
				return dp_i;
			}
		}

		/**
		 * 337. House Robber III The thief has found himself a new place for his
		 * thievery again. There is only one entrance to this area, called the "root."
		 * Besides the root, each house has one and only one parent house. After a tour,
		 * the smart thief realized that "all houses in this place forms a binary tree".
		 * It will automatically contact the police if two directly-linked houses were
		 * broken into on the same night.
		 * 
		 * Determine the maximum amount of money the thief can rob tonight without
		 * alerting the police.
		 * 
		 * Example 1:
		 * 
		 * Input: [3,2,3,null,3,null,1]
		 * 
		 * @author hao
		 *
		 */
		/**
		 * Definition for a binary tree node.
		 */
		public class TreeNode {
			int val;
			TreeNode left;
			TreeNode right;

			TreeNode(int x) {
				val = x;
			}
		}

		class Solution3 {
			Map<TreeNode, Integer> memo = new HashMap<>();

			public int rob(TreeNode root) {
				if (root == null)
					return 0;
				// 利用备忘录消除重叠子问题
				if (memo.containsKey(root))
					return memo.get(root);
				// 抢，然后去下下家
				int do_it = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
						+ (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
				// 不抢，然后去下家
				int not_do = rob(root.left) + rob(root.right);

				int res = Math.max(do_it, not_do);
				memo.put(root, res);
				return res;
			}
		}
	}

}
