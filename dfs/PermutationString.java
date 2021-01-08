/**
 * 567. Permutation in String Given two strings s1 and s2, write a function to
 * return true if s2 contains the permutation of s1. In other words, one of the
 * first string's permutations is the substring of the second string.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s1 = "ab" s2 = "eidbaooo" Output: True Explanation: s2 contains one
 * permutation of s1 ("ba"). Example 2:
 * 
 * Input:s1= "ab" s2 = "eidboaoo" Output: False
 * 
 * @author hao
 *
 */
class Solution {
	HashMap<Character, Integer> mapOri = new HashMap<>();
	HashMap<Character, Integer> mapCnt = new HashMap<>();

	public boolean checkInclusion(String s1, String s2) {
		if (s1 == "") {
			return true;
		}
		for (char c : s1.toCharArray()) {
			mapOri.put(c, mapOri.getOrDefault(c, 0) + 1);
		}
		int right = 0;
		int left = 0;
		int s2length = s2.length();
		int valid = 0;
		while (right < s2length) {
			char c = s2.charAt(right);
			right++;
			if (mapOri.containsKey(c)) {
				System.out.println(" containsKey " + c);
				int currentAdd = mapCnt.getOrDefault(c, 0) + 1;
				mapCnt.put(c, currentAdd);
				if (currentAdd == mapOri.get(c)) {
					valid++;
					System.out.println(" valid " + c + " " + valid);
				}
			}
			while (right - left >= s1.length()) {
				if (valid == mapOri.size()) {
					System.out.println(" finish " + valid);
					return true;
				}

				char c2 = s2.charAt(left);
				left++;
				if (mapOri.containsKey(c2)) {
					if (mapCnt.getOrDefault(c2, 0) == mapOri.get(c2)) {
						valid--;
						System.out.println(" invalid " + c2);
					}
					int currentMinus = mapCnt.getOrDefault(c2, 0) - 1;
					mapCnt.put(c2, currentMinus);
				}
			}
		}
		return false;
	}
}