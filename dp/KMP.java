/**
 * 28. Implement strStr() Implement strStr().
 * 
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Clarification:
 * 
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 * 
 * For the purpose of this problem, we will return 0 when needle is an empty
 * string. This is consistent to C's strstr() and Java's indexOf().
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: haystack = "hello", needle = "ll" Output: 2
 */
class Solution {
        private int[][] dp;
        private String pat;
        /**
         * 状态
         */
        public int strStr(String haystack, String needle) {
            if (needle.equals("")) {
                return 0;
            }
            pat = needle;
            KMP(needle);
            return search(haystack);
        }

        public void KMP(String pat) {
            this.pat = pat;
            int M = pat.length();
            // dp[状态][字符] = 下个状态
            dp = new int[M][256];
            // base case
            dp[0][pat.charAt(0)] = 1;
            // 影子状态 X 初始为 0
            int X = 0;
            // 当前状态 j 从 1 开始
            for (int j = 1; j < M; j++) {
                for (int c = 0; c < 256; c++) {
                    if (pat.charAt(j) == c)
                        dp[j][c] = j + 1;
                    else
                        dp[j][c] = dp[X][c];
                }
                // 更新影子状态
                X = dp[X][pat.charAt(j)];
            }
        }
        public int search(String txt) {
                int M = pat.length();
                int N = txt.length();
                // pat 的初始态为 0
                int j = 0;
                for (int i = 0; i < N; i++) {
                    // 计算 pat 的下一个状态
                    j = dp[j][txt.charAt(i)];
                    // 到达终止态，返回结果
                    if (j == M) return i - M + 1;
                }
                // 没到达终止态，匹配失败
                return -1;
            }
    }