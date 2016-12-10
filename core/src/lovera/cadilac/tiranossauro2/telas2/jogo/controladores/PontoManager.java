package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.ponto.Ponto;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.ponto.PoolDePontos;

public class PontoManager implements TipoSingleton{

    private static PontoManager pontoManager;

    private final PoolDePontos poolDePontos;

    public PontoManager() {
        this.poolDePontos = new PoolDePontos();
    }

    @Override
    public void inicializar() {
        pontoManager = this;
    }

    public Ponto criarPonto(float x, float y){
        return this.poolDePontos.obtain().init(x, y);
    }

    public void destruirPonto(Ponto ponto){
        this.poolDePontos.free(ponto);
    }
}
