package src;
import java.util.LinkedList;
import java.util.Queue;

public class Bodega {
    private final int capacidad;
    private final Queue<Producto> productos;
    
    public Bodega(int capacidad) {
        this.capacidad = capacidad;
        this.productos = new LinkedList<>();
    }
    
    public synchronized void agregar(Producto producto) {
        while (productos.size() == capacidad) {
            try {
                // Espera hasta que haya espacio en la bodega
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.add(producto);
        notifyAll();  // Notificar a los consumidores (o al despachador) que hay un nuevo producto
    }
    
    public synchronized Producto retirar() {
        if (productos.isEmpty()) {
            return null;
        }
        Producto producto = productos.poll();
        notifyAll();  // Notificar a los productores que hay espacio en la bodega
        return producto;
    }


    public synchronized int getCantidadActual() {
        return productos.size();
    }
}