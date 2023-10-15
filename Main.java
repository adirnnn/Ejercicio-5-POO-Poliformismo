import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        while (true) {
            System.out.println("Ingrese el tipo de jugador (líbero, pasador, opuesto, auxiliar), o 'exit' para salir: ");
            String tipoJugador = scanner.nextLine();

            if (tipoJugador.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Ingrese el nombre del jugador: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el país del jugador: ");
            String pais = scanner.nextLine();

            System.out.println("Ingrese la cantidad de errores: ");
            int errores = scanner.nextInt();

            System.out.println("Ingrese la cantidad de aces: ");
            int aces = scanner.nextInt();

            System.out.println("Ingrese el total de servicios: ");
            int totalServicios = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            Jugador jugador = null;

            if (tipoJugador.equalsIgnoreCase("líbero")) {
                System.out.println("Ingrese la cantidad de recibos efectivos: ");
                int recibosEfectivos = scanner.nextInt();
                jugador = new Libero(nombre, pais, errores, aces, totalServicios, recibosEfectivos);
            } else if (tipoJugador.equalsIgnoreCase("pasador")) {
                System.out.println("Ingrese la cantidad de pases efectivos: ");
                int pasesEfectivos = scanner.nextInt();
                System.out.println("Ingrese la cantidad de fintas efectivas: ");
                int fintasEfectivas = scanner.nextInt();
                jugador = new Pasador(nombre, pais, errores, aces, totalServicios, pasesEfectivos, fintasEfectivas);
            } else if (tipoJugador.equalsIgnoreCase("opuesto") || tipoJugador.equalsIgnoreCase("auxiliar")) {
                System.out.println("Ingrese la cantidad de ataques efectivos: ");
                int ataquesEfectivos = scanner.nextInt();
                System.out.println("Ingrese la cantidad de bloqueos efectivos: ");
                int bloqueosEfectivos = scanner.nextInt();
                System.out.println("Ingrese la cantidad de bloqueos fallidos: ");
                int bloqueosFallidos = scanner.nextInt();
                jugador = new AuxiliarOpuesto(nombre, pais, errores, aces, totalServicios, ataquesEfectivos, bloqueosEfectivos, bloqueosFallidos);
            } else {
                System.out.println("Tipo de jugador no válido.");
                continue;
            }

            jugadores.add(jugador);
        }

        // Operaciones
        System.out.println("Jugadores inscritos en el torneo:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre());
        }

        System.out.println("Los 3 mejores líberos:");
        jugadores.stream()
                .filter(jugador -> jugador instanceof Libero)
                .sorted((jugador1, jugador2) -> Double.compare(jugador2.calcularEfectividad(), jugador1.calcularEfectividad()))
                .limit(3)
                .forEach(jugador -> System.out.println(jugador.getNombre()));

        long pasadoresEficientes = jugadores.stream()
                .filter(jugador -> jugador instanceof Pasador)
                .filter(jugador -> jugador.calcularEfectividad() > 80.0)
                .count();
        System.out.println("Cantidad de pasadores con más de un 80% de efectividad: " + pasadoresEficientes);
    }
}