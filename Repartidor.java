import java.util.Random;

public class Repartidor extends Thread {
    private MiniBodega miniBodega;
    private Random random;

    public Repartidor(MiniBodega miniBodega) {
        this.miniBodega = miniBodega;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            // Retirar producto de la minibodega
            Producto producto = miniBodega.retirar();

            // Simular el tiempo de entrega
            try {
                int tiempoEntrega = 3000 + random.nextInt(8000); // Valor entre 3000ms (3s) y 11000ms (11s) - para obtener rango [3,10] segundos.
                Thread.sleep(tiempoEntrega);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Notificar al productor que el producto ha sido entregado
            producto.entregar();
        }
    }
}
