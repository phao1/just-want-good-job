import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class test {
	/**
	 * 46. Permutations Given an array nums of distinct integers, return all the
	 * possible permutations. You can return the answer in any order.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: nums = [1,2,3] Output:
	 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
	 * 
	 * @author hao
	 *
	 */
	class Solution {
		List<List<Integer>> res = new LinkedList<>();

		/* 主函数，输入一组不重复的数字，返回它们的全排列 */
		List<List<Integer>> permute(int[] nums) {
			// 记录「路径」
			LinkedList<Integer> track = new LinkedList<>();
			backtrack(nums, track);
			return res;
		}

		void backtrack(int[] nums, LinkedList<Integer> track) {
			// 触发结束条件
			if (track.size() == nums.length) {
				res.add(new LinkedList(track));
				return;
			}

			for (int i = 0; i < nums.length; i++) {
				// 排除不合法的选择
				if (track.contains(nums[i]))
					continue;
				// 做选择
				track.add(nums[i]);
				// 进入下一层决策树
				backtrack(nums, track);
				// 取消选择
				track.removeLast();
			}
		}
	}
	/**
	 * 47. Permutations II Given a collection of numbers, nums, that might contain
	 * duplicates, return all possible unique permutations in any order.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: nums = [1,1,2] Output: [[1,1,2], [1,2,1], [2,1,1]]
	 */
	class Solution {
        boolean[] vis;

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            List<Integer> perm = new ArrayList<Integer>();
            vis = new boolean[nums.length];
            Arrays.sort(nums);
            backtrack(nums, ans, 0, perm);
            return ans;
        }

        public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
            if (idx == nums.length) {
                ans.add(new ArrayList<Integer>(perm));
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                    continue;
                }
                perm.add(nums[i]);
                vis[i] = true;
                backtrack(nums, ans, idx + 1, perm);
                vis[i] = false;
                perm.remove(idx);
            }
        }
    }
}