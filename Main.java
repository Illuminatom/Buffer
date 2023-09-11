package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static List<Integer> distribuirProductos(int numProductos, int M) {
        List<Integer> distribucion = new ArrayList<>();

        // Cantidad base de productos para cada productor
        int base = numProductos / M;

        // Cuántos productores recibirán un producto adicional
        int remainder = numProductos % M;

        for (int i = 0; i < M; i++) {
            if (i < remainder) {
                // Estos productores obtienen la cantidad base + 1
                distribucion.add(base + 1);
            } else {
                // El resto obtiene solo la cantidad base
                distribucion.add(base);
            }
        }

        return distribucion;
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Pedir por consola un numero de "productores" M
        System.out.print("Introduce el numero de productores M: ");
        int M = scanner.nextInt();

        // Pedir por consola un numero de "repartidores" N
        System.out.print("Introduce el numero de repartidores N: ");
        int N = scanner.nextInt();

        // Pedir por consola un tamanio de bodega TAM
        System.out.print("Introduce el tamanio de bodega TAM: ");
        int TAM = scanner.nextInt();
        
        System.out.print("Introduce el numero de productos a producir: ");
        int numProductos = scanner.nextInt();
        
        List<Integer> distribucion = distribuirProductos(numProductos, M);
        

        // Creacion de bodega y mini bodega
        Bodega bodegaPrincipal = new Bodega(TAM);
        MiniBodega minibodega = new MiniBodega();  // MiniBodega tiene una capacidad fija de 1 producto seg�n lo indicado

        // Creacion de Despachador
        Despachador despachador = new Despachador(bodegaPrincipal, minibodega, numProductos);
        despachador.start();

        // Creacion de productores
        Productor[] productores = new Productor[M];
        for(int i = 0; i < M; i++) {
            System.out.println("Se inicia el productor con id " + i);
            productores[i] = new Productor(i, bodegaPrincipal, distribucion.get(i));
            productores[i].start(); 
        }

        // Creacion de repartidores
        Repartidor[] repartidores = new Repartidor[N];
        for(int i = 0; i < N; i++) {
            System.out.println("Se inicia el repartidor con ud "+i);
            repartidores[i] = new Repartidor(i , minibodega, numProductos);
            repartidores[i].start();
        }

        // A partir de aqui, comienza la simulacion deseada.
        scanner.close();
        
        
        for(int i = 0; i < M; i++) {
            try {
                productores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < N; i++) {
            try {
                repartidores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	 despachador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       

        System.out.println("El proceso terminó");
    }
}