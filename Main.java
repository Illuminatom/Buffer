package src;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Pedir por consola un n�mero de "productores" M
        System.out.print("Introduce el n�mero de productores M: ");
        int M = scanner.nextInt();

        // Pedir por consola un n�mero de "repartidores" N
        System.out.print("Introduce el n�mero de repartidores N: ");
        int N = scanner.nextInt();

        // Pedir por consola un tama�o de bodega TAM
        System.out.print("Introduce el tama�o de bodega TAM: ");
        int TAM = scanner.nextInt();

        // No pedimos el n�mero de productos a producir porque no lo utilizamos en este segmento, pero puedes a�adirlo si lo necesitas en otra parte del programa.

        // Creaci�n de bodega y mini bodega
        Bodega bodegaPrincipal = new Bodega(TAM);
        MiniBodega minibodega = new MiniBodega();  // MiniBodega tiene una capacidad fija de 1 producto seg�n lo indicado

        // Creaci�n de Despachador
        Despachador despachador = new Despachador(bodegaPrincipal, minibodega);

        // Creaci�n de productores
        Productor[] productores = new Productor[M];
        for(int i = 0; i < M; i++) {
            productores[i] = new Productor(i, bodegaPrincipal);  
            productores[i].start(); 
        }

        // Creaci�n de repartidores
        Repartidor[] repartidores = new Repartidor[N];
        for(int i = 0; i < N; i++) {
            repartidores[i] = new Repartidor(minibodega);
        }

        // A partir de aqu�, puedes comenzar la simulaci�n o l�gica deseada.
    }
}