package src;

public class Despachador extends Thread {
    private Bodega bodegaPrincipal;
    private MiniBodega miniBodega;

    public Despachador(Bodega bodegaPrincipal, MiniBodega miniBodega) {
        this.bodegaPrincipal = bodegaPrincipal;
        this.miniBodega = miniBodega;
    }

    @Override
    public void run() {
        while (true) {
            // Retirar producto de la bodega principal
            Producto producto = bodegaPrincipal.retirar();

            // Si el producto es null, la bodega está vacía, así que hacemos un yield
            if (producto == null) {
                Thread.yield();
                continue;  // Vuelve al inicio del ciclo para intentar retirar de nuevo
            }

            // Colocar el producto en la mini bodega
            miniBodega.colocar(producto);
        }
    }

}