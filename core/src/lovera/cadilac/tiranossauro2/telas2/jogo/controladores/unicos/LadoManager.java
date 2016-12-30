package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;


import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Lado;

//Todo: arrumar singletons nesse padrao
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

    public static LadoManager getInstancia() {
        return ladoManager;
    }

    public Lado getLado() {
        return lado;
    }
}
