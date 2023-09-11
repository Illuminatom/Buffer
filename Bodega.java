

import java.util.LinkedList;
import java.util.Queue;

public class Bodega {
    private final int capacidad;
    private final Queue<Producto> productos;
    
    public Bodega(int capacidad) {
        this.capacidad = capacidad;
        this.productos = new LinkedList<>();
    }
    
    public synchronized void agregar(Producto producto, String id) {
        System.out.println("El productor con id " + id + " trata de agregar un producto con id "+producto.getId()+" a la bodega");
        while (productos.size() == capacidad) {
            try {
                // Espera hasta que haya espacio en la bodega
                System.out.println("La bodega esta llena, el producto con id " + producto.getId() + " no puede se agregado \n\tEl productor con id "+id+ " se ira a dormir\n");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.add(producto);
        System.out.println("Se agrego un producto con id "+producto.getId()+" a la bodega. \n Espacios restantes en la bodega: "+ (capacidad-getCantidadActual())+" \n");
        notifyAll();  // Notificar al despachador que hay un nuevo producto
    }
    
    public synchronized Producto retirar() {
        if (productos.isEmpty()) {
            return null;
        }
        Producto producto = productos.poll();
        System.out.println("El producto con id "+ producto.getId() +" sera retirado de la bodega");
        notifyAll();  // Notificar a los productores que hay espacio en la bodega
        return producto;
    }


    public synchronized int getCantidadActual() {
        return productos.size();
    }
}