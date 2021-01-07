/**
 * # Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. # An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. # You may assume all four edges of
 * the grid are all surrounded by water. # # Example 1: # # Input: # 11110 #
 * 11010 # 11000 # 00000 # # Output: 1 # Example 2: # # Input: # 11000 # 11000 #
 * 00100 # 00011 # # Output: 3 # idea - iterate through each of the cell and if
 * it is an island, # do dfs to mark all adjacent islands, then increase the
 * counter by 1. # 所有连着的1算一个岛, 所以有一个，其他的就不再计算了
 */
class Solution {
	int cnt = 0;

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length < 0) {
			return cnt;
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}

	private void dfs(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
			return;
		}
		grid[i][j] = '0';
		dfs(grid, i + 1, j);
		dfs(grid, i, j + 1);
		dfs(grid, i - 1, j);
		dfs(grid, i, j - 1);
	}
}