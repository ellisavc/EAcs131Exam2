class BinaryTree {

    // TreeNode class
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // Print tree (visual indentation)
    static void printTree(TreeNode root, String indent) {
        if (root == null)
            return;
        System.out.println(indent + root.val);
        printTree(root.left, indent + "| ");
        printTree(root.right, indent + "| ");
    }

    // Mirror (recursive)
    static TreeNode mirror(TreeNode root) {
        if (root == null)
            return null;
        TreeNode temp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(temp);
        return root;
    }

    // Correct Upside-down Flip (recursive)
    static TreeNode upsideDown(TreeNode root) {
        if (root == null || root.left == null)
            return root;

        TreeNode newRoot = upsideDown(root.left);

        root.left.left = root.right; // original right becomes left child
        root.left.right = root; // original root becomes right child

        root.left = null;
        root.right = null;

        return newRoot;
    }

    // Print tree visually upside down
    static void printTreeUpsideDown(TreeNode root, String indent) {
        if (root == null)
            return;
        printTree(root.right, indent + " ");
        printTree(root.left, indent + " ");
        System.out.println(indent + root.val);
    }

    // Build sample tree
    static TreeNode buildSampleTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        return root;
    }

    public static void main(String[] args) {
        TreeNode original = buildSampleTree();

        System.out.println("Original:");
        printTree(original, "");

        TreeNode mirrored = mirror(cloneTree(original));
        System.out.println("\nMirrored:");
        printTree(mirrored, "");

        TreeNode flipped = upsideDown(cloneTree(original));
        System.out.println("\nUpside-down version 1:");
        printTree(flipped, "");

        TreeNode flipped2 = cloneTree(mirrored);
        System.out.println("\nUpside-down flip version 2:");
        printTreeUpsideDown(flipped2, "");
    }

    // Helper: deep copy of tree
    static TreeNode cloneTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode copy = new TreeNode(root.val);
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        return copy;
    }
}