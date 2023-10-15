public class Libero extends Jugador {
    private int recibosEfectivos;

    public Libero(String nombre, String pais, int errores, int aces, int totalServicios, int recibosEfectivos) {
        super(nombre, pais, errores, aces, totalServicios);
        this.recibosEfectivos = recibosEfectivos;
    }

    public int getRecibosEfectivos() {
        return recibosEfectivos;
    }

    public void setRecibosEfectivos(int recibosEfectivos) {
        this.recibosEfectivos = recibosEfectivos;
    }

    @Override
    public double calcularEfectividad() {
        // Cálculo de efectividad específico para líberos
        return ((recibosEfectivos - getErrores()) * 100.0) / (recibosEfectivos + getErrores()) + (getAces() * 100.0) / getTotalServicios();
    }
}