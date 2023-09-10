package src;

import java.util.Random;

public class Repartidor extends Thread {
    private MiniBodega miniBodega;
    private Random random;
    private int total;
    private static volatile int entregados =0;
    private static final Object LOCK= new Object();

    public Repartidor(MiniBodega miniBodega, int total) {
        this.miniBodega = miniBodega;
        this.random = new Random();
  
        this.total=total;
    }

    @Override
    public void run() {
        while (entregados<total) {
            // Retirar producto de la minibodega
            Producto producto = miniBodega.retirar();

            // Simular el tiempo de entrega
            try {
                int tiempoEntrega = 3000 + random.nextInt(7001); // Valor entre 3000ms (3s) y 11000ms (11s) - para obtener rango [3,10] segundos.
                Thread.sleep(tiempoEntrega);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Notificar al productor que el producto ha sido entregado
            
            producto.entregar();
            synchronized(LOCK) {
                entregados++;
            }
        }
        System.out.println("El repartidor termino la ejecucion...");
    }
    
}