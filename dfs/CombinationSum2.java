class Solution2 {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < candidates.length; i++) {
			if (candidates[i] == target) {
				List<Integer> res1 = new ArrayList<>();
				res1.add(candidates[i]);
				res.add(res1);
				break;
			}
		}
		for (int i = 0; i <= candidates.length; i++) {
			List<List<Integer>> sub = nSumTarget(candidates, i, 0, target);
			res.addAll(sub);
		}
		return res;
	}

	public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
		List<List<Integer>> res = new ArrayList<>();
		int sz = nums.length;
		if (n < 2 || sz < n)
			return res;
		if (n == 2) {
			int lo = start;
			int hi = sz - 1;
			while (lo < hi) {
				int sumTemp = nums[lo] + nums[hi];
				int left = nums[lo];
				int right = nums[hi];
				if (sumTemp > target) {
					while (lo < hi && nums[hi] == right)
						hi--;
				} else if (sumTemp < target) {
					while (lo < hi && nums[lo] == left)
						lo++;
				} else {
					List<Integer> list = new ArrayList<>();
					list.add(nums[lo]);
					list.add(nums[hi]);
					res.add(list);
					while (lo < hi && nums[lo] == left)
						lo++;
					while (lo < hi && nums[hi] == right)
						hi--;
				}
			}
		} else {
			for (int i = start; i < sz; i++) {
				List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
				for (List<Integer> arr : sub) {
					arr.add(nums[i]);
					res.add(arr);
				}
				while (i < sz - 1 && nums[i] == nums[i + 1])
					i++;
			}
		}
		return res;
	}
}

class Solution {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		if (candidates.length <= 0) {
			return null;
		}
		Arrays.sort(candidates);
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> res = new ArrayList<>();
		dfs(candidates, 0, res, results, target);
		return results;
	}

	public void dfs(int[] arr, int start, List<Integer> res, List<List<Integer>> results, int target) {
		if (target == 0) {
			results.add(res);
			return;
		}
		if (arr.length <= 0) {
			return;
		}
		if (target < 0) {
			return;
		}
		for (int i = start; i < arr.length; i++) {
			if (i != start && arr[i] == arr[i - 1]) {
				continue;
			}
			List<Integer> ressub = new ArrayList<>();
			ressub.addAll(res);
			ressub.add(arr[i]);
			dfs(arr, i + 1, ressub, results, target - arr[i]);
		}
	}
}