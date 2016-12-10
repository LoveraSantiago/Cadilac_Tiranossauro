package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.ponto.Pontos;

public class PontoManager implements TipoSingleton{

    private static PontoManager pontoManager;

    private final Pontos pontos;

    public PontoManager() {
        this.pontos = new Pontos();
    }

    @Override
    public void inicializar() {
        pontoManager = this;
    }

    public static PontoManager getInstancia() {
        return pontoManager;
    }

    public Pontos getPontos() {
        return pontos;
    }
}
