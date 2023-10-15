import java.util.StringJoiner;

public class Jugador {
    private String nombre;
    private String pais;
    private int errores;
    private int aces;
    private int totalServicios;

    public Jugador(String nombre, String pais, int errores, int aces, int totalServicios) {
        this.nombre = nombre;
        this.pais = pais;
        this.errores = errores;
        this.aces = aces;
        this.totalServicios = totalServicios;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public int getErrores() {
        return errores;
    }

    public int getAces() {
        return aces;
    }

    public int getTotalServicios() {
        return totalServicios;
    }

    public double calcularEfectividad() {
        return ((aces * 100.0 / totalServicios) - (errores * 100.0 / totalServicios));
    }

    public String toCSV() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(nombre);
        joiner.add(pais);
        joiner.add(String.valueOf(errores));
        joiner.add(String.valueOf(aces));
        joiner.add(String.valueOf(totalServicios));
        return joiner.toString();
    }

    public static Jugador fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 5) {
            return null; // Invalid CSV format
        }
        String nombre = parts[0];
        String pais = parts[1];
        int errores = Integer.parseInt(parts[2]);
        int aces = Integer.parseInt(parts[3]);
        int totalServicios = Integer.parseInt(parts[4]);
        return new Jugador(nombre, pais, errores, aces, totalServicios);
    }
}