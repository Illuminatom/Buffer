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

            // Colocar el producto en la mini bodega
            miniBodega.colocar(producto);
        }
    }
}
