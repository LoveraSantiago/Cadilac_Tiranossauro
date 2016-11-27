package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;

public class ControleManager2 implements TipoSingleton{

    private static ControleManager2 controleManager;

    @Override
    public void inicializar() {
        controleManager = this;
    }

    public static ControleManager2 getInstance() {
        return controleManager;
    }

    public void setGraficoEscolhido(GraficosEnum2 graficoEnum){

    }
}
