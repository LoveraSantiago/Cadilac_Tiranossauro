package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficoManager2;

public final class GraficoUnico implements TipoSingleton{

    private static GraficoUnico graficoUnico;
    private final GraficoManager2 graficoManager2;

    public GraficoUnico() {
        this.graficoManager2 = new GraficoManager2();
    }

    @Override
    public void inicializar() {
        graficoUnico = this;
    }

    public static GraficoUnico getInstancia() {
        return graficoUnico;
    }

    public GraficoManager2 getGraficoManager2() {
        return graficoManager2;
    }
}
