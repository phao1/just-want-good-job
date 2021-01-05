
// 212. Word Search II
// Given a 2D board and a list of words from the dictionary, find all words in the board.
//
// Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
// Example:
//
// Input:
// words = ["oath","pea","eat","rain"] and board =
// [
//   ['o','a','a','n'],
//   ['e','t','a','e'],
//   ['i','h','k','r'],
//   ['i','f','l','v']
// ]
//
// Output: ["eat","oath"]
// Note:
// You may assume that all inputs are consist of lowercase letters a-z.
// cannot handle following case
// Input: [["a","b"],["a","a"]]
// ["aba","baa","bab","aaab","aaa","aaaa","aaba"]
// Output:
// ["aba","aaa","aaba","aaaa","aaab","bab","baa"]
// Expected:
// ["aaa","aaab","aaba","aba","baa"]
class Solution {
	public List<String> findWords(char[][] board, String[] words) {
		int m = board.length;
		int n = board[0].length;
		HashSet<String> res = new HashSet<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (String word : words) {
					if (dfs(board, i, j, word, 0))
						res.add(word);
				}
			}
		}
		return new ArrayList<>(res);
	}

	public boolean dfs(char[][] board, int i, int j, String word, int k) {
		int m = board.length;
		int n = board[0].length;
		if (k == word.length())
			return true;
		if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '.')
			return false;
		char ch = board[i][j];
		if (ch != word.charAt(k))
			return false;
		board[i][j] = '.';
		boolean res = dfs(board, i + 1, j, word, k + 1) || dfs(board, i - 1, j, word, k + 1)
				|| dfs(board, i, j - 1, word, k + 1) || dfs(board, i, j + 1, word, k + 1);
		board[i][j] = ch;
		return res;
	}
}