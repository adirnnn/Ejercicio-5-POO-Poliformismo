public class Pasador extends Jugador {
    private int pases;
    private int fintasEfectivas;

    public Pasador(String nombre, String pais, int errores, int aces, int totalServicios, int pases, int fintasEfectivas) {
        super(nombre, pais, errores, aces, totalServicios);
        this.pases = pases;
        this.fintasEfectivas = fintasEfectivas;
    }

    public int getPases() {
        return pases;
    }

    public void setPases(int pases) {
        this.pases = pases;
    }

    public int getFintasEfectivas() {
        return fintasEfectivas;
    }

    public void setFintasEfectivas(int fintasEfectivas) {
        this.fintasEfectivas = fintasEfectivas;
    }

    @Override
    public double calcularEfectividad() {
        // Cálculo de efectividad específico para pasadores
        return ((pases + fintasEfectivas - getErrores()) * 100.0) / (pases + fintasEfectivas + getErrores()) + (getAces() * 100.0) / getTotalServicios();
    }
}