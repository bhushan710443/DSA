package Problems.Tree;

public class DFS{

    public static void main(String[] args){

        TreeNode root= new TreeNode(8);

        root.left= new TreeNode(3);
        root.right= new TreeNode(10);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);

        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        System.out.println(root);
        System.out.println(maxAncestorDiff(root));
    }


    public static int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.value, root.value);
    }

    private static int dfs(TreeNode node, int min, int max) {

        if (node == null) {
            return max - min;
        }

        min= Math.min(min,node.value);
        max= Math.max(max,node.value);

        int left=dfs(node.left,min, max);
        int right=dfs(node.right,min, max);

        return Math.max(left,right);
    }














        static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "TreeNode{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}