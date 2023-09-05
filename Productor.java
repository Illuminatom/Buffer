class Productor extends Thread {
    private int id;
    private Bodega bodega;
    private Producto productoActual;

    public Productor(int id, Bodega bodega) {
        this.id = id;
        this.bodega = bodega;
    }

    @Override
    public void run() {
        while (true) {
            // Esperar hasta que el producto actual haya sido entregado
            if (productoActual != null) {
                try {
                    productoActual.esperarHastaEntrega();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Producir un nuevo producto
            productoActual = new Producto(id, this);
            bodega.agregar(productoActual);

            // Luego, deberías tener lógica para que el Despachador mueva los productos y así sucesivamente.
        }
    }
}
