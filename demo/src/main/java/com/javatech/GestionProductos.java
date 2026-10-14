package com.javatech;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Provides the inventory's product management operations. */
public class GestionProductos {
    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (buscarProductoPorId(producto.getId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con el ID " + producto.getId() + ".");
        }
        productos.add(producto);
    }

    public List<Producto> listarProductos() {
        return List.copyOf(productos);
    }

    public Optional<Producto> buscarProductoPorId(int id) {
        return productos.stream()
                .filter(producto -> producto.getId() == id)
                .findFirst();
    }

    public void actualizarProducto(int id, String nombre, double precio, int stock) {
        Producto producto = buscarProductoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id + "."));

        // Validate all new values before changing the stored product.
        Producto productoActualizado = new Producto(id, nombre, precio, stock);
        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
    }

    public void eliminarProducto(int id) {
        Producto producto = buscarProductoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id + "."));
        productos.remove(producto);
    }
}
