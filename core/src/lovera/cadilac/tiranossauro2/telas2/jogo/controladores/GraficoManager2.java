package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;

public final class GraficoManager2 implements TipoSingleton{

    private static GraficoManager2 graficoManager;

    @Override
    public void inicializar() {
        graficoManager = this;
    }

    public static GraficoManager2 getInstancia() {
        return graficoManager;
    }

    public void setGraficoEscolhido(GraficosEnum2 graficoEnum){

    }

}
