

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
            Producto producto = miniBodega.retirar();

            // Simular el tiempo de entrega
            try {
                producto.entregar();
                synchronized(LOCK) {
                    entregados++;
                }
                    int tiempoEntrega = 3000 + random.nextInt(7001); // Valor entre 3000ms (3s) y 11000ms (11s) - para obtener rango [3,10] segundos.
                    System.out.println("Entregando producto con id "+producto.getId()+".......");
                    Thread.sleep(tiempoEntrega);
                    System.out.println("Han sido entregados "+ entregados+" productos\n");

                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Notificar al productor que el producto ha sido entregado
            

        }
        System.out.println("El repartidor con id "+this.id+" termino la ejecucion...");
    }
}