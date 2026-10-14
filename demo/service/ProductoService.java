package service;

import java.util.List;
import modelo.Producto;

public class ProductoService {
// coleccion en memoria que guarda los productos
    private List<Producto> productos= new Arraylist<>();
}

// contador asiga ID's unicos. Es static porque pertenece a la clase y no a uan instancia y garantiza que le id sea unico aunque hubiera varias instancias de productos

private static int contadorID = 1;
