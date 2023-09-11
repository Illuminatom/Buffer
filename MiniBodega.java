
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
        System.out.println("Se agrego un producto con id " +nuevoProducto.getId()+" a la miniBodega\n");
        notify();  // Notificar a los repartidores que hay un producto disponible
    }

    public synchronized void despertarDormidos(){
        notifyAll();   //Despierta a los repartidores que quedaron dormidos despues de que todos los productos se entregaran   
    }
    public synchronized Producto retirar(Repartidor repartidor) {
        while (producto == null && repartidor.getTotal() != repartidor.getEntregados()) {
            try {
                // Espera hasta que haya un producto en la minibodega
            	System.out.println("La miniBodega esta vacia, a dormir...\n");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(producto != null)
            System.out.println("Repartidor saca el producto con id "+producto.getId()+" de la miniBodega\n");
        Producto temp = producto;
        producto = null;  // Vaciar la minibodega
        notifyAll();  // Notificar al despachador que la minibodega esta vacia
        return temp;
    }
}