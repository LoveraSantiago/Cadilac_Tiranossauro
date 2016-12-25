package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.areajogavel.AreaJogavel2;

public abstract class EntradaGrafica implements TipoControlavel, TipoDesenhavel, Disposable {

    private static AreaJogavel2 areaJogavel2;

    public EntradaGrafica() {
        if(areaJogavel2 == null){
            areaJogavel2 = new AreaJogavel2();
        }
    }

    public AreaJogavel2 getAreaJogavel2() {
        return areaJogavel2;
    }

    public void configurarAreaJogavel(GraficosEnum2 graficoEnum){
        areaJogavel2.configurarAreaJogavel(graficoEnum);
    }

    @Override
    public void dispose() {
        areaJogavel2.dispose();
    }
}
