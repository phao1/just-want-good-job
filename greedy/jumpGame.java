/**
 * 55. Jump Game Given an array of non-negative integers nums, you are initially
 * positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0
 * to 1, then 3 steps to the last index.
 * 
 * @author hao
 *
 */
public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * 403. Frog Jump A frog is crossing a river. The river is divided into some
 * number of units, and at each unit, there may or may not exist a stone. The
 * frog can jump on a stone, but it must not jump into the water.
 * 
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog can cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assumes the first jump must be
 * 1 unit.
 * 
 * If the frog's last jump was k units, its next jump must be either k - 1, k,
 * or k + 1 units. The frog can only jump in the forward direction.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: stones = [0,1,3,5,6,8,12,17] Output: true Explanation: The frog can
 * jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to
 * the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 */

public class Solution {
    public boolean canCross(int[] stones) {
        int[][] memo = new int[stones.length][stones.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return can_Cross(stones, 0, 0, memo) == 1;
    }
    public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
        if (memo[ind][jumpsize] >= 0) {
            return memo[ind][jumpsize];
        }
        int ind1 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize);
        if (ind1 >= 0 && can_Cross(stones, ind1, jumpsize, memo) == 1) {
            memo[ind][jumpsize] = 1;
            return 1;
        }
        int ind2 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize - 1);
        if (ind2 >= 0 && can_Cross(stones, ind2, jumpsize - 1, memo) == 1) {
            memo[ind][jumpsize - 1] = 1;
            return 1;
        }
        int ind3 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize + 1);
        if (ind3 >= 0 && can_Cross(stones, ind3, jumpsize + 1, memo) == 1) {
            memo[ind][jumpsize + 1] = 1;
            return 1;
        }
        memo[ind][jumpsize] = ((ind == stones.length - 1) ? 1 : 0);
        return memo[ind][jumpsize];
    }
}
// public class Solution2 {
//         public boolean canCross(int[] stones) {
//             HashMap<Integer, Set<Integer>> map = new HashMap<>();
//             for (int i = 0; i < stones.length; i++) {
//                 map.put(stones[i], new HashSet<Integer>());
//             }
//             map.get(0).add(0);
//             for (int i = 0; i < stones.length; i++) {
//                 for (int k : map.get(stones[i])) {
//                     for (int step = k - 1; step <= k + 1; step++) {
//                         if (step > 0 && map.containsKey(stones[i] + step)) {
//                             map.get(stones[i] + step).add(step);
//                         }
//                     }
//                 }
//             }
//             return map.get(stones[stones.length - 1]).size() > 0;
//         }
//     }

/**
 * 45. 跳跃游戏 II 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 示例:
 * 
 * 输入: [2,3,1,1,4] 输出: 2 解释: 跳到最后一个位置的最小跳跃数是 2。 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3
 * 步到达数组的最后一个位置。 说明:
 * 
 * 假设你总是可以到达数组的最后一个位置。
 */
//class Solution {
//HashMap<Integer, Integer> memo = new HashMap<>();
//public int jump(int[] nums) {
//  int n = nums.length;
//  for (int i = 0; i < n; i++) {
//      memo.put(i,n);
//  }
//  return dp(nums, 0);
//}

//private int dp(int[] nums, int p) {
//  int n = nums.length;
//  if (p >= n -1) {
//      return 0;
//  }
//  if (memo.get(p) != n) {
//      return memo.get(p);
//  }

//  int steps = nums[p];
//  for (int i = 1; i <= steps; i++) {
//      int sub = dp(nums, p + i);
//      memo.put(p, Math.min(memo.get(p), sub + 1));
//  }
//  return memo.get(p);
//}
//}
class Solution {
	public int jump(int [] nums){
		int end = 0;
		int maxPosition = 0;
		int steps = 0;
		
		for(int i=0; i<nums.length-1;i++){
			maxPosition = Math.max(maxPosition ,nums[i] + i);
			if(i == end){
				end = maxPosition;
				steps++;
			}
		}
		return steps;
	}
}


