package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.lado;

public final class LadoManager{

    private static LadoManager ladoManager;
    private final Lado lado;

    public LadoManager() {
        this.lado = new Lado();
    }

    public Lado getLado() {
        return lado;
    }
}
