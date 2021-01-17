import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class test {
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	
	/**
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
		
		Note: A leaf is a node with no children.
		
		Example:
		
		Given the below binary tree and sum = 22,
		
		      5
		     / \
		    4   8
		   /   / \
		  11  13  4
		 /  \    / \
		7    2  5   1
		Return:
		
		[
		   [5,4,11,2],
		   [5,8,4,5]
		]
		
		来源：力扣（LeetCode）
		链接：https://leetcode-cn.com/problems/path-sum-ii
		著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @author hao
	 *
	 */
	class Solution1 {
		List<List<Integer>> res = new ArrayList<>();
		Deque<Integer> path = new LinkedList<Integer>();

		public List<List<Integer>> pathSum(TreeNode root, int sum) {
			if (root == null) {
				return res;
			}
			dfs(root, sum);
			return res;
		}

		private void dfs(TreeNode root, int sum) {
			if (root == null) {
				return;
			}
			path.offerLast(root.val);
			sum -= root.val;
			if (root.left == null && root.right == null && sum == 0) {
				res.add(new LinkedList<Integer>(path));
			}
			dfs(root.left, sum);
			dfs(root.right, sum);
			path.pollLast();
		}
	}
	
	
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode() {}
	 *     TreeNode(int val) { this.val = val; }
	 *     TreeNode(int val, TreeNode left, TreeNode right) {
	 *         this.val = val;
	 *         this.left = left;
	 *         this.right = right;
	 *     }
	 * }
	 */
	 class Solution2_1 {
	        public int pathSum(TreeNode root, int sum) {
	            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	            //设置路径和符合条件即res+1的前提（0=pathSum-sum）
	            map.put(0, 1);
	            return helper(root, map, sum, 0);
	        }
	        int helper(TreeNode root, HashMap<Integer, Integer> map, int sum, int pathSum){
	            int res = 0;
	            if(root == null) return 0;
	            //将当前所在节点的值加到走过的路径值的和中
	            pathSum += root.val;
	            //getOrDefault(Object key,V defaultValue)
	            // 以上方法为返回指定键（Object key）所映射的值，若无则直接返回所设置的默认值（V defaultValue）
	            //累加上到当前节点为止有多少条路径和符合条件（此处若是pathSum-sum==0,则返回1，在map中若存在当前pathSum-sum对应值
	            //的key则对应value的值则必不为0，为1或大于1，若无此key则返回方法默认值0）
	            res += map.getOrDefault(pathSum - sum, 0);
	            //此处是计数到当前节点为止有多少条自上而下的节点路径和等于pathSum，并将其存入map
	            // （亦或是更新pathSum对应的路径数，若先前有和值为pathSum的路径则取出其条数先前加上当前的一条）
	            map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
	            //往左子树以及右子树依次统计
	            // 再加上res-->到当前节点为止可能出现的和值符合pathSum的路径数（统计范围即为头节点到当前节点）
	            res = helper(root.left, map, sum, pathSum) + helper(root.right, map, sum, pathSum) + res;
	            // 在返回前，将到当前节点为止的和值pathSum的条数计-1，防止影响后面其他未走完路径的统计
	            //由于路径和值只能自上而下，所以在当前节点返回前（节点返回条件为下一节点为空，
	            // 即为最后节点或者最后节点返回后遍历完依次往上递归返回，返回意味着pathSum到当前节点已自上而下的累加遍历完）
	            map.put(pathSum, map.get(pathSum) - 1);
	            return res;
	        }
	    }
	class Solution2_2 {
	        int res = 0;
	        public int pathSum(TreeNode root, int sum) {
	            if (root == null) {
	                return res;
	            }
	            dfs(root, sum);
	            return res;
	        }

	        private void dfs(TreeNode root, int sum) {
	            if (root == null) {
	                return;
	            }
	            dfs2(root, sum);
	            dfs(root.left, sum);
	            dfs(root.right, sum);
	        }

	        private void dfs2(TreeNode root, int sum) {
	            if (root == null) {
	                return;
	            }
	            sum -= root.val;
	            if (sum == 0) {
	                res++;
	            }
	            dfs2(root.left, sum);
	            dfs2(root.right, sum);
	        }
	    }
}