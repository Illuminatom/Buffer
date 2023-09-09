

import java.util.Scanner;

public class Main {
    
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

        // No pedimos el n�mero de productos a producir porque no lo utilizamos en este segmento, pero puedes a�adirlo si lo necesitas en otra parte del programa.

        // Creaci�n de bodega y mini bodega
        Bodega bodegaPrincipal = new Bodega(TAM);
        MiniBodega minibodega = new MiniBodega();  // MiniBodega tiene una capacidad fija de 1 producto seg�n lo indicado

        // Creacion de Despachador
        Despachador despachador = new Despachador(bodegaPrincipal, minibodega);

        // Creacion de productores
        Productor[] productores = new Productor[M];
        for(int i = 0; i < M; i++) {
            System.out.println("Se inicia el Thread con id " + i);
            productores[i] = new Productor(i, bodegaPrincipal);  
            productores[i].start(); 
        }

        // Creacion de repartidores
        Repartidor[] repartidores = new Repartidor[N];
        for(int i = 0; i < N; i++) {
            repartidores[i] = new Repartidor(minibodega);
        }

        // A partir de aqu�, puedes comenzar la simulaci�n o l�gica deseada.
        scanner.close();
    }
}