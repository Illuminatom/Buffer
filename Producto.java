package src;


public class Producto {
    private String id;
    private Productor productor;
    private boolean entregado;

    public Producto(Productor productor) {
        this.id = productor.getIdentificador()+"-"+productor.getProducidos();
        this.productor = productor;
        this.entregado = false;  
    }

    public synchronized void entregar(int tiempo) {
        this.entregado = true;
        System.out.println("El producto con el id "+id+" ha sido entregado en " + tiempo+ "segundos \n");
        notify();  // notificar al productor
    }
    

    public synchronized void esperarHastaEntrega() throws InterruptedException {
        while (!entregado) {
            wait();
        }
    }

    public String getId() {
        return id;
    }
}