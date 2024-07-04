package tareas.tercer_avance.arbol_binario;

public class Inventario {

    TreeNode root;

    Inventario() {
        root = null;
    }

    // Método para insertar un nuevo producto en el árbol
    void insertar(Producto producto) {
        System.out.println("Insertando item: " + producto.getNombre() + " con código " + producto.getCodigo());
        root = insertarRecursivamente(root, producto);
    }

    // Función recursiva para insertar un nuevo producto en el árbol
    TreeNode insertarRecursivamente(TreeNode root, Producto producto) {
        if (root == null) {
            char checkMark = '\u2713';
            System.out.println(" " + checkMark + "  Item " + producto.getNombre() + " insertado.");
            root = new TreeNode(producto);
            System.out.print("\n");
            return root;
        }

        int nodeCodigoProducto = root.getProducto().getCodigo();

        if (producto.getCodigo() < nodeCodigoProducto) {
            System.out.println("(-) Insertar " + producto.getNombre() + " a la izquierda de " + root.getProducto().getNombre());
            root.left = insertarRecursivamente(root.left, producto);
        } else if (producto.getCodigo() > nodeCodigoProducto) {
            System.out.println("(+) Insertar " + producto.getNombre() + " a la derecha de " + root.getProducto().getNombre());
            root.right = insertarRecursivamente(root.right, producto);
        }

        return root;
    }

    // Método para buscar un producto por código
    TreeNode buscar(int codigo) {
        System.out.println("Buscando producto con código " + codigo);
        return buscarRecursivamente(root, codigo);
    }

    // Función recursiva para buscar un producto por código
    TreeNode buscarRecursivamente(TreeNode root, int codigo) {
        if (root == null) {
            System.out.println("Producto con código " + codigo + " no encontrado.");
            return root;
        }

        int nodeCodigoProducto = root.getProducto().getCodigo();

        if (nodeCodigoProducto == codigo) {
            System.out.println("Producto " + root.getProducto().getNombre() + " encontrado.");
            return root;
        }
        if (codigo < nodeCodigoProducto) {
            System.out.println("Buscar a la izquierda de " + root.getProducto().getNombre());
            return buscarRecursivamente(root.left, codigo);
        }
        System.out.println("Buscar a la derecha de " + root.getProducto().getNombre());
        return buscarRecursivamente(root.right, codigo);
    }

    // Método para imprimir el árbol en una estructura visual ascendente
    void imprimirArbol() {
        imprimirArbolRecursivamente(root, 0);
    }

    // Función recursiva para imprimir el árbol en una estructura visual ascendente
    void imprimirArbolRecursivamente(TreeNode root, int nivel) {
        if (root != null) {
            imprimirArbolRecursivamente(root.right, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("                      ");// 06 tab
            }
            System.out.println(root.getProducto().getCodigo() + " (" + root.getProducto().getNombre() + ")");
            imprimirArbolRecursivamente(root.left, nivel + 1);
        }
    }
}
