package tareas.tercer_avance.arbol_binario;

public class TreeNode {

    Producto producto;
    TreeNode left, right;

    public TreeNode(Producto producto) {
        this.producto = producto;
        left = right = null;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

}
