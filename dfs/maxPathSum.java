import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class test {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	/**
	 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

		The path sum of a path is the sum of the node's values in the path.
		
		Given the root of a binary tree, return the maximum path sum of any path.
		
		
		
		来源：力扣（LeetCode）
		链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
		著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @author hao
	 *
	 */
	class Solution1_1 {
        int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
        }

        public int maxGain(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // 递归计算左右子节点的最大贡献值
            // 只有在最大贡献值大于 0 时，才会选取对应子节点
            int leftGain = Math.max(maxGain(node.left), 0);
            int rightGain = Math.max(maxGain(node.right), 0);

            // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
            int priceNewpath = node.val + leftGain + rightGain;

            // 更新答案
            maxSum = Math.max(maxSum, priceNewpath);

            // 返回节点的最大贡献值
            return node.val + Math.max(leftGain, rightGain);
        }
    }
	
	/**
	 * 129. Sum Root to Leaf Numbers
		Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
		
		An example is the root-to-leaf path 1->2->3 which represents the number 123.
		
		Find the total sum of all root-to-leaf numbers.
		
		Note: A leaf is a node with no children.
		
		Example:
		
		Input: [1,2,3]
		    1
		   / \
		  2   3
		Output: 25
		Explanation:
		The root-to-leaf path 1->2 represents the number 12.
		The root-to-leaf path 1->3 represents the number 13.
		Therefore, sum = 12 + 13 = 25.
	 * }
	 */
	class Solution2_1 {
	        public int sumNumbers(TreeNode root) {
	            return dfs(root, 0);
	        }

	        public int dfs(TreeNode root, int prevSum) {
	            if (root == null) {
	                return 0;
	            }
	            int sum = prevSum * 10 + root.val;
	            if (root.left == null && root.right == null) {
	                return sum;
	            } else {
	                return dfs(root.left, sum) + dfs(root.right, sum);
	            }
	        }
	    }
}
