/**
 * 300. Longest Increasing Subsequence Given an integer array nums, return the
 * length of the longest strictly increasing subsequence.
 * 
 * A subsequence is a sequence that can be derived from an array by deleting
 * some or no elements without changing the order of the remaining elements. For
 * example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [10,9,2,5,3,7,101,18] Output: 4 Explanation: The longest
 * increasing subsequence is [2,3,7,101], therefore the length is 4.
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
                int length = nums.length;
                if (length == 0) {
                    return 0;
                }
                int[] dp = new int[length];
                Arrays.fill(dp, 1);
                for (int i = 0; i < nums.length; i++) {
                    for (int j = 0; j < i; j++) {
                            if (nums[i] > nums[j]) {
                                    dp[i] = Math.max(dp[i], dp[j] + 1);
                            }
                    }
                }
                int res = 0;
                for (int i = 0; i < dp.length; i++) {
                        res = Math.max(res, dp[i]);
                }
                return res;
        }
}


// class Solution {
//         class Pair {
//             int fir, sec;
//             Pair(int fir, int sec) {
//                 this.fir = fir;
//                 this.sec = sec;
//             }
//         }
//         public int stoneGames(int[] piles) {
//             int n = piles.length;
//             Pair[][] dp = new Pair[n][n];
//             for (int i = 0; i < n; i++) {
//                 for (int j =0; j < i; j++) {
//                     dp[i][j] = new Pair(0, 0);
//                 }
//             }
//             for (int i = 0; i < n; i ++) {
//                 dp[i][i].fir = piles[i];
//                 dp[i][i].sec = 0;
//             }
//             for (int l = 2; l <= n; l++) {
//                 for (int i = 0; i <= n - 1; i++) {
//                     int j = l + i -1;
//                     int left = piles[i] + dp[i+1][j].sec;
//                     int right = piles[j] + dp[i][j-1].sec;
//                     if (left > right) {
//                         dp[i][j].fir = left;
//                         dp[i][j].sec = dp[i+1][j].fir;
//                     } else {
//                         dp[i][j].fir = right;
//                         dp[i][j].sec = dp[i][j-1].fir;
//                     }
//                 }
//             }
//             Pair res =dp[0][n-1];
//             return res.fir - res.sec;
//         }
//     }

/**
 * 673. Number of Longest Increasing Subsequence Given an integer array nums,
 * return the number of longest increasing subsequences.
 * 
 * Notice that the sequence has to be strictly increasing.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,5,4,7] Output: 2 Explanation: The two longest increasing
 * subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 */

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int[] dp = new int[length];
        int[] dpn = new int[length];
        Arrays.fill(dp, 1);
        Arrays.fill(dpn, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        dpn[i] = dpn[j];
                    } else if (dp[i] == dp[j] + 1){
                        dpn[i] += dpn[j];
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        int count = 0;
        for (int i = 0; i < dp.length; i++) {
            if (max == dp[i]) {
                count += dpn[i];
            }
        }
        return count;
    }
}
/**
 * 1143. Longest Common Subsequence Given two strings text1 and text2, return
 * the length of their longest common subsequence.
 * 
 * A subsequence of a string is a new string generated from the original string
 * with some characters(can be none) deleted without changing the relative order
 * of the remaining characters. (eg, "ace" is a subsequence of "abcde" while
 * "aec" is not). A common subsequence of two strings is a subsequence that is
 * common to both strings.
 * 
 * 
 * 
 * If there is no common subsequence, return 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: text1 = "abcde", text2 = "ace" Output: 3 Explanation: The longest
 * common subsequence is "ace" and its length is 3.
 */

class Solution {
    // 备忘录，消除重叠子问题
int[][] memo;

/* 主函数 */
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    // 备忘录值为 -1 代表未曾计算
    memo = new int[m][n];
    for (int[] row : memo)
        Arrays.fill(row, -1);
    // 计算 s1[0..] 和 s2[0..] 的 lcs 长度
    return dp(text1, 0, text2, 0);
}

// 定义：计算 s1[i..] 和 s2[j..] 的最长公共子序列长度
int dp(String s1, int i, String s2, int j) {
    // base case
    if (i == s1.length() || j == s2.length()) {
        return 0;
    }
    // 如果之前计算过，则直接返回备忘录中的答案
    if (memo[i][j] != -1) {
        return memo[i][j];
    }
    // 根据 s1[i] 和 s2[j] 的情况做选择
    if (s1.charAt(i) == s2.charAt(j)) {
        // s1[i] 和 s2[j] 必然在 lcs 中
        memo[i][j] = 1 + dp(s1, i + 1, s2, j + 1);
    } else {
        // s1[i] 和 s2[j] 至少有一个不在 lcs 中
        memo[i][j] = Math.max(
                dp(s1, i + 1, s2, j),
                dp(s1, i, s2, j + 1)
        );
    }
    return memo[i][j];
}
}

/**
 * 718. Maximum Length of Repeated Subarray Given two integer arrays A and B,
 * return the maximum length of an subarray that appears in both arrays.
 * 
 * Example 1:
 * 
 * Input: A: [1,2,3,2,1] B: [3,2,1,4,7] Output: 3 Explanation: The repeated
 * subarray with maximum length is [3, 2, 1].
 * 
 */
class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        int la = A.length;
        int lb = B.length;
        int dp[][] = new int[la + 1][lb + 1];
        int max = 0;
        String st = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < la + 1; i++) {
            for (int j = 1; j < lb + 1; j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                    String string = "";
//                    if (max == dp[i][j]) {
//                        for (int k = i - max; k <i; k++) {
//                            string += A[k];
//                        }
//                        st = string;
//                    }

                } else {
                    dp[i][j] = 0;
                }
            }
        }
        System.out.println(st);
        return max;
    }
}