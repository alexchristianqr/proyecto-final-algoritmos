package tareas.tercer_avance.arbol_binario;

public class Main {

    /*
        Caso: Gestión de Inventario de Productos "TAMBO"
     */
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        System.out.print("-------------\n");
        System.out.println("LOG: INSERTAR");
        System.out.print("-------------\n");

//        inventario.insertar(new Producto(50, "Producto A"));
//        inventario.insertar(new Producto(30, "Producto B"));
//        inventario.insertar(new Producto(20, "Producto C"));
//        inventario.insertar(new Producto(40, "Producto D"));
//        inventario.insertar(new Producto(70, "Producto E"));
//        inventario.insertar(new Producto(60, "Producto F"));
//        inventario.insertar(new Producto(80, "Producto G"));
        inventario.insertar(new Producto(50, "Gaseosa"));
        inventario.insertar(new Producto(30, "Chicle"));
        inventario.insertar(new Producto(20, "Galleta"));
        inventario.insertar(new Producto(40, "Maní"));
        inventario.insertar(new Producto(70, "Chocolate"));
        inventario.insertar(new Producto(60, "Turrón"));
        inventario.insertar(new Producto(80, "Caramelo"));

        System.out.print("------------------------\n");
        System.out.println("ARBOL BINARIO HORIZONTAL");
        System.out.print("------------------------\n");
        System.out.println("Leyenda: arriba o izquierda => mayores y abajo o derecha => menores");
        System.out.print("\n");

        inventario.imprimirArbol();
        System.out.print("\n");

        System.out.print("-----------\n");
        System.out.println("LOG: BUSCAR");
        System.out.print("-----------\n");

        // Buscar producto en arbol binario
        int codigoBusqueda = 40;
        TreeNode resultado = inventario.buscar(codigoBusqueda);
        System.out.print("\n");

        System.out.print("---------------\n");
        System.out.println("LOG: ENCONTRADO");
        System.out.print("---------------\n");

        if (resultado != null) {
            System.out.println("Producto encontrado: Código " + resultado.getProducto().getCodigo() + ", Nombre " + resultado.getProducto().getNombre());
        } else {
            System.out.println("Producto con código " + codigoBusqueda + " no encontrado en el inventario.");
        }
    }
}
