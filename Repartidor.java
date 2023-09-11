

import java.util.Random;

public class Repartidor extends Thread {
    private int id;
    private MiniBodega miniBodega;
    private Random random;
    private int total;
    private static volatile int entregados =0;
    private static final Object LOCK= new Object();

    public Repartidor(int id,MiniBodega miniBodega, int total) {
        this.id = id;
        this.miniBodega = miniBodega;
        this.random = new Random();
  
        this.total=total;
    }
    
    @Override
    public void run() {
        while (entregados<total) {
            // Retirar producto de la minibodega
            Producto producto = miniBodega.retirar(this);

            // Simular el tiempo de entrega
            if(producto != null){
                try {
                synchronized(LOCK) {
                    entregados++;
                }
            	System.out.println("Entregando producto con id "+producto.getId()+".......");
            	int tiempoEntrega = 3000 + random.nextInt(7001); // Valor entre 3000ms (3s) y 11000ms (11s) - para obtener rango [3,10] segundos.	
            	Thread.sleep(tiempoEntrega);
                producto.entregar(tiempoEntrega);
                
                   
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}

            // Notificar al productor que el producto ha sido entregado
            
        }

        System.out.println("El repartidor con id "+this.id+" termino la ejecucion...");
        miniBodega.despertarDormidos();
    }

    public int getEntregados() {
        return entregados;
    }

    public int getTotal() {
        return total;
    }
}