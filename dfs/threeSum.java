/**
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero. Notice that the solution set must not contain duplicate triplets.
 * 
 * Example 1:
 * 
 * Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]] Example 2:
 * 
 * Input: nums = [] Output: [] Example 3:
 * 
 * Input: nums = [0] Output: []
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author hao
 *
 */
class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList();
		Arrays.sort(nums);
		int len = nums.length;
		if (nums == null || len < 3)
			return result;
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] > 0)
				break;
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int pivot = nums[i];
			int leftPosition = i + 1;
			int rightPosition = len - 1;
			while (leftPosition < rightPosition) {
				int leftValue = nums[leftPosition];
				int rightValue = nums[rightPosition];
				int sum = pivot + leftValue + rightValue;
				if (sum == 0) {
					result.add(new ArrayList<Integer>(Arrays.asList(leftValue, rightValue, pivot)));
					while (leftPosition < rightPosition && nums[leftPosition] == nums[leftPosition + 1]) {
						leftPosition++;
					}
					while (leftPosition < rightPosition && nums[rightPosition] == nums[rightPosition - 1]) {
						rightPosition--;
					}
					leftPosition++;
					rightPosition--;
				} else if (sum < 0) {
					leftPosition++;
				} else {
					rightPosition--;
				}
			}
		}
		return result;
	}
}