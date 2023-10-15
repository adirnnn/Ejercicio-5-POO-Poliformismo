import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Torneo {
    private ArrayList<Jugador> jugadores;

    public Torneo() {
        jugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void mostrarJugadores() {
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
    }

    public void mostrarMejoresLiberos(int cantidad) {
        jugadores.sort(Comparator.comparing(Jugador::calcularEfectividad).reversed());
        int contador = 0;
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Libero) {
                System.out.println(jugador);
                contador++;
                if (contador >= cantidad) {
                    break;
                }
            }
        }
    }

    public int contarPasadoresEfectivos() {
        int contador = 0;
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Pasador) {
                Pasador pasador = (Pasador) jugador;
                if (pasador.calcularEfectividad() > 80.0) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public void guardarCatalogoCSV(String archivo) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            for (Jugador jugador : jugadores) {
                writer.write(jugador.toCSV());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarCatalogoCSV(String archivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String line;
            jugadores.clear();
            while ((line = reader.readLine()) != null) {
                Jugador jugador = Jugador.fromCSV(line);
                if (jugador != null) {
                    jugadores.add(jugador);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}