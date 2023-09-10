package src;


public class MiniBodega {
    private Producto producto;

    public synchronized void colocar(Producto nuevoProducto) {
        while (producto != null) {
            try {
                // Espera hasta que la minibodega este vacia
                System.out.println("La miniBodega esta llena, a dormir...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.producto = nuevoProducto;
        System.out.println("Se agrego un producto con id " +nuevoProducto.getId()+"a la miniBodega");
        notify();  // Notificar a los repartidores que hay un producto disponible
    }

    public synchronized Producto retirar() {
        while (producto == null) {
            try {
                // Espera hasta que haya un producto en la minibodega
            	System.out.println("La miniBodega esta vacia, a dormir...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Repartidor entrega el producto de la miniBodega");
        Producto temp = producto;
        producto = null;  // Vaciar la minibodega
        notifyAll();  // Notificar al despachador que la minibodega est� vac�a
        return temp;
    }
}