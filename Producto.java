


public class Producto {
    private int id;
    private Productor productor;
    private boolean entregado;

    public Producto(int id, Productor productor) {
        this.id = id;
        this.productor = productor;
        this.entregado = false;  
    }

    public synchronized void entregar() {
        this.entregado = true;
        System.out.println("El producto con el id "+id+" ha sido entregado, se notificara al productor\n");
        notify();  // notificar al productor
    }
    

    public synchronized void esperarHastaEntrega() throws InterruptedException {
        while (!entregado) {
            wait();
        }
    }

    public int getId() {
        return id;
    }
}