
public class MiniBodega {
    private Producto producto;

    public synchronized void colocar(Producto nuevoProducto) {
        while (producto != null) {
            try {
                // Espera hasta que la minibodega esté vacía
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.producto = nuevoProducto;
        notifyAll();  // Notificar a los repartidores que hay un producto disponible
    }

    public synchronized Producto retirar() {
        while (producto == null) {
            try {
                // Espera hasta que haya un producto en la minibodega
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto temp = producto;
        producto = null;  // Vaciar la minibodega
        notifyAll();  // Notificar al despachador que la minibodega está vacía
        return temp;
    }
}
