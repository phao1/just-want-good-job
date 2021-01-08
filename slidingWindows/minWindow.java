/**
 * 76. Minimum Window Substring Given two strings s and t, return the minimum
 * window in s which will contain all the characters in t. If there is no such
 * window in s that covers all characters in t, return the empty string "".
 * 
 * Note that If there is such a window, it is guaranteed that there will always
 * be only one unique minimum window in s.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "ADOBECODEBANC", t = "ABC" Output: "BANC" Example 2:
 * 
 * Input: s = "a", t = "a" Output: "a"
 * 
 * @author hao
 *
 */

class Solution {
	HashMap<Character, Integer> mapOri = new HashMap<>();
	HashMap<Character, Integer> mapCnt = new HashMap<>();
	public String minWindow(String s, String t) {
		if (t == null || t == "") {
			return "";
		}
		for (char c : t.toCharArray()) {
			mapOri.put(c, mapOri.getOrDefault(c, 0) + 1);
		}
		int left = 0;
		int right = 0;
		int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
		while (right < s.length()) {
			char tempC = s.charAt(right);
			if (mapOri.containsKey(tempC)) {
				int tempSum = mapCnt.getOrDefault(tempC, 0) + 1;
				mapCnt.put(tempC, tempSum);
			}
			while(left <= right && check()) {
				if (right - left + 1 < len) {
                    len = right - left + 1;
					ansL = left;
					ansR = left + len;
				}
				if (mapOri.containsKey(s.charAt(left))) {
					mapCnt.put(s.charAt(left), mapCnt.getOrDefault(s.charAt(left), 0) - 1);
				}
				left++;
			}
			right++;
			
		}
		return ansL == -1 ? "" : s.substring(ansL, ansR);
	}
	private boolean check() {
		Iterator iter = mapOri.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Character key = (Character) entry.getKey();
			Integer val = (Integer) entry.getValue();
			if (mapCnt.getOrDefault(key, 0) < val) {
				return false;
			}
		}
		return true;
	}
}