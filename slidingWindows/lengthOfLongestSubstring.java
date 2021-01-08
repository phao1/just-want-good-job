
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class test {
	/*
	 * 3. Longest Substring Without Repeating Characters Given a string s, find the
	 * length of the longest substring without repeating characters.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: s = "abcabcbb" Output: 3 Explanation: The answer is "abc", with the
	 * length of 3. Example 2:
	 * 
	 * Input: s = "bbbbb" Output: 1 Explanation: The answer is "b", with the length
	 * of 1. Example 3:
	 * 
	 * Input: s = "pwwkew" Output: 3 Explanation: The answer is "wke", with the
	 * length of 3. Notice that the answer must be a substring, "pwke" is a
	 * subsequence and not a substring.
	 */
	class Solution {
		HashMap<Character, Integer> window = new HashMap<>();
		int max = 0;
		public int lengthOfLongestSubstring(String s) {
			if (s == null || s == "") {
				return 0;
			}
			int left = 0;
			int right = 0;
			int max = 0;
			int sLen = s.length();
			while (right < sLen) {
				char tempC = s.charAt(right);
				right++;
				window.put(tempC, window.getOrDefault(tempC, 0) + 1);
				
				while (window.getOrDefault(tempC, 0) > 1) {
					window.put(s.charAt(left), window.getOrDefault(s.charAt(left), 0) - 1);
					left++;
				}
				max = Math.max(max, right - left);
			}
			return max;
		}
	}

}
