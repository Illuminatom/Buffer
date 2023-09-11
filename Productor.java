package src;

public class Productor extends Thread {
    private String identificador;
    private Bodega bodega;
    private Producto productoActual;
    private int numeroAProducir;
    private int producidos;

    public Productor(String id, Bodega bodega, int numProductos) {
        this.identificador = id;
        this.bodega = bodega;
        numeroAProducir = numProductos;
        producidos =0;
    }
    public String getIdentificador() {
        return identificador;
    }
    public int getProducidos() {
        return producidos;
    }

    @Override
    public void run() {
        while (producidos < numeroAProducir) {
            // Esperar hasta que el producto actual haya sido entregado
            if (productoActual != null) {
                try {
                    productoActual.esperarHastaEntrega();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Producir un nuevo producto
            productoActual = new Producto(this);
            bodega.agregar(productoActual, identificador);
            producidos += 1;

            // Luego, deberías tener lógica para que el Despachador mueva los productos y así sucesivamente.
        }
        
        System.out.println("Productor " + identificador + " produjo todos sus correspondientes y terminará su ejecución");
    }
    
}