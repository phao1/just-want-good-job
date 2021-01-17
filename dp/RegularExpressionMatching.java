/**
 * 10. Regular Expression Matching Given an input string (s) and a pattern (p),
 * implement regular expression matching with support for '.' and '*' where:
 * 
 * '.' Matches any single character.​​​​ '*' Matches zero or more of the
 * preceding element. The matching should cover the entire input string (not
 * partial).
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aa", p = "a" Output: false Explanation: "a" does not match the
 * entire string "aa".
 */
class Solution {
        int[][] memo = null;
        public boolean isMatch(String s, String p) {
            int slen = s.length();
            int plen = p.length();
            memo = new int[slen + 1][plen + 1];
            for (int i = 0; i <= slen; i++) {
                for (int j = 0; j <= plen; j++) {
                    memo[i][j] = -1;
                }
            }
            return dp(0, 0, s, p);
        }
        private boolean dp(int i, int j, String s, String p){
            if (i > s.length() || j > p.length()) {
                return false;
            }
            if (memo[i][j] != -1) {
                return getBoolean(memo[i][j]);
            }
            if (j == p.length()) {
                return i == s.length();
            }
            boolean first = false;
            if (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) {
                first = true;
            }
            boolean ans = false;
            if (j <= p.length() - 2 && p.charAt(j + 1) == '*') {
                ans = dp(i, j + 2,s,p) || (first && dp (i+1, j,s,p));
            } else {
                ans = first && dp(i+1, j+1, s, p);
            }

            memo[i][j] = ans ? 0 : 1;
            return ans;
        }

        private boolean getBoolean(int i) {
            if (i == 0) {
                return true;
            }
            if (i == 1) {
                return false;
            }
            return false;
        }
    }