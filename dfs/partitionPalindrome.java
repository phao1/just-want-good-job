/**
 *
 * # -*- coding: utf-8 -*- """ Created by MengQiu Wang on 2019-05-02 """
 *
 * # Given a string s, partition s such that every substring of the partition is
 * a palindrome. # # Return all possible palindrome partitioning of s. # #
 * Example: # # Input: "aab" # Output: # [ # ["aa","b"], # ["a","a","b"] # ]
 */
class Solution {
	List<List<String>> res = new ArrayList<>();

	public List<List<String>> partition(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		List<String> path = new ArrayList<>();
		dfs(s, path);
		return res;
	}

	private void dfs(String s, List<String> path) {
		if (s == null || s.length() == 0) {
			res.add(new ArrayList<>(path));
		}
		for (int i = 1; i < s.length() + 1; i++) {
			String sub = s.substring(0, i);
			if (isPalidrome(sub)) {
				path.add(sub);
				dfs(s.substring(i, s.length()), path);
				path.remove(path.size() - 1);
			}
		}

	}

	private boolean isPalidrome(String sb) {
		int left = 0;
		int right = sb.length() - 1;
		while (left < right) {
			if (sb.charAt(left) != sb.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}
}