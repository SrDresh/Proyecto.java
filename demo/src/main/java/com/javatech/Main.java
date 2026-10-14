package com.javatech;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/** Console interface for the product inventory. */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestionProductos gestionProductos = new GestionProductos();

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Selecciona una opción: ");

            try {
                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> listarProductos();
                    case 3 -> buscarProducto();
                    case 4 -> actualizarProducto();
                    case 5 -> eliminarProducto();
                    case 0 -> {
                        continuar = false;
                        System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    }
                    default -> System.out.println("Opción no válida. Selecciona una opción del menú.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== Gestión de inventario ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto por ID");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("0. Salir");
    }

    private static void agregarProducto() {
        int id = leerEntero("ID: ");
        String nombre = leerTexto("Nombre: ");
        double precio = leerDecimal("Precio: ");
        int stock = leerEntero("Stock: ");

        gestionProductos.agregarProducto(new Producto(id, nombre, precio, stock));
        System.out.println("Producto agregado correctamente.");
    }

    private static void listarProductos() {
        List<Producto> productos = gestionProductos.listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        System.out.printf("%-8s %-24s %12s %10s%n", "ID", "NOMBRE", "PRECIO", "STOCK");
        System.out.println("------------------------------------------------------------");
        for (Producto producto : productos) {
            System.out.printf("%-8d %-24s %12.2f %10d%n",
                    producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
        }
    }

    private static void buscarProducto() {
        int id = leerEntero("ID del producto: ");
        Optional<Producto> producto = gestionProductos.buscarProductoPorId(id);
        if (producto.isPresent()) {
            mostrarProducto(producto.get());
        } else {
            System.out.println("No existe un producto con el ID " + id + ".");
        }
    }

    private static void actualizarProducto() {
        int id = leerEntero("ID del producto a actualizar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        double precio = leerDecimal("Nuevo precio: ");
        int stock = leerEntero("Nuevo stock: ");

        gestionProductos.actualizarProducto(id, nombre, precio, stock);
        System.out.println("Producto actualizado correctamente.");
    }

    private static void eliminarProducto() {
        int id = leerEntero("ID del producto a eliminar: ");
        gestionProductos.eliminarProducto(id);
        System.out.println("Producto eliminado correctamente.");
    }

    private static void mostrarProducto(Producto producto) {
        System.out.printf("ID: %d | Nombre: %s | Precio: %.2f | Stock: %d%n",
                producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException exception) {
                System.out.println("Entrada inválida. Ingresa un número entero.");
                scanner.nextLine();
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException exception) {
                System.out.println("Entrada inválida. Ingresa un número decimal.");
                scanner.nextLine();
            }
        }
    }
}
