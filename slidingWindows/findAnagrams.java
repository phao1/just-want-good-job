import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 438. Find All Anagrams in a String Given a string s and a non-empty string p,
 * find all the start indices of p's anagrams in s.
 * 
 * Strings consists of lowercase English letters only and the length of both
 * strings s and p will not be larger than 20,100.
 * 
 * The order of output does not matter.
 * 
 * Example 1:
 * 
 * Input: s: "cbaebabacd" p: "abc"
 * 
 * Output: [0, 6]
 * 
 * Explanation: The substring with start index = 0 is "cba", which is an anagram
 * of "abc". The substring with start index = 6 is "bac", which is an anagram of
 * "abc". Example 2:
 * 
 * Input: s: "abab" p: "ab"
 * 
 * Output: [0, 1, 2]
 */
public class findAnagrams {
	HashMap<Character, Integer> mapCnt = new HashMap<>();
	HashMap<Character, Integer> mapOri = new HashMap<>();

	public List<Integer> findAnagrams(String s, String p) {
		if (p == null || p == "") {
			return null;
		}
		List<Integer> res = new ArrayList<>();
		int left = 0;
		int right = 0;
		int match = 0;
		for (char c : p.toCharArray()) {
			mapOri.put(c, mapOri.getOrDefault(c, 0) + 1);
		}
		int sLen = s.length();
		while (right < sLen) {
			char tempC = s.charAt(right);
			if (mapOri.containsKey(tempC)) {
				mapCnt.put(tempC, mapCnt.getOrDefault(tempC, 0) + 1);
				if (mapOri.get(tempC) == mapCnt.get(tempC)) {
					match++;
				}
			}
			right++;
			while (match == mapOri.size()) {
				if (left - right == p.length()) {
					res.add(left);
				}
				if (mapOri.containsKey(s.charAt(left))) {
					mapCnt.put(s.charAt(left), mapCnt.getOrDefault(s.charAt(left), 0) - 1);
					if (mapOri.get(tempC) > mapCnt.get(tempC)) {
						match--;
					}
				}
				left++;
			}
		}
		return res;
	}
}