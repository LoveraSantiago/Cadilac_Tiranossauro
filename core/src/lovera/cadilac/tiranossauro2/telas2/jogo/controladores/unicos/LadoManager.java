package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;


import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Lado;

public class LadoManager implements TipoSingleton{

    private static LadoManager ladoManager;
    private final Lado lado;

    public LadoManager() {
        this.lado = new Lado();
    }

    @Override
    public void inicializar() {
        ladoManager = this;
    }

    public Lado getLado() {
        return lado;
    }
}
