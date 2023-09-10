

public class Productor extends Thread {
    private int id;
    private Bodega bodega;
    private Producto productoActual;
    private int numeroAProducir;
    private int producidos;

    public Productor(int id, Bodega bodega, int numProductos) {
        this.id = id;
        this.bodega = bodega;
        numeroAProducir = numProductos;
        producidos =0;
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
            productoActual = new Producto(id, this);
            bodega.agregar(productoActual, id);
            producidos += 1;

            // Luego, deberías tener lógica para que el Despachador mueva los productos y así sucesivamente.
        }
        
        System.out.println("Productor " + id + " produjo todos sus correspondientes y terminará su ejecución");
    }
    
}