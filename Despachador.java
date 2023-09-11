package src;

public class Despachador extends Thread {
    private Bodega bodegaPrincipal;
    private MiniBodega miniBodega;
    private int total;
    private int despachados;
    
    public Despachador(Bodega bodegaPrincipal, MiniBodega miniBodega, int total) {
        this.bodegaPrincipal = bodegaPrincipal;
        this.miniBodega = miniBodega;
        this.total = total;
        despachados = 0;
    }

    @Override
    public void run() {
        while (despachados<total) {
            // Retirar producto de la bodega principal
            Producto producto = bodegaPrincipal.retirar();

            // Si el producto es null, la bodega esta vacia, asi que hacemos un yield
            if (producto == null) {
                //System.out.println("No hay objetos en la bodega, se hace espera semiactiva yield...");
                Thread.yield();
                continue;  // Vuelve al inicio del ciclo para intentar retirar de nuevo
            }

            // Colocar el producto en la mini bodega
            miniBodega.colocar(producto);
            despachados +=1;
        }
        System.out.println("Despachador termino su ejecucion");
    }

}