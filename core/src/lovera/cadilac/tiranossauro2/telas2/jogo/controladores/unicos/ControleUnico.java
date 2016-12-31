package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import java.util.Map;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;

public class ControleUnico implements TipoSingleton{

    private static ControleUnico controleUnico;
    private final lovera.cadilac.tiranossauro2.telas2.jogo.controladores.controle.ControleManager2 controleManager2;

    public ControleUnico(Map<GraficosEnum2, EntradaGrafica> mapaEntradaGraficas) {
        this.controleManager2 = new lovera.cadilac.tiranossauro2.telas2.jogo.controladores.controle.ControleManager2(mapaEntradaGraficas);
    }

    @Override
    public void inicializar() {
        controleUnico = this;
    }

    public static ControleUnico getInstancia() {
        return controleUnico;
    }

    public lovera.cadilac.tiranossauro2.telas2.jogo.controladores.controle.ControleManager2 getControleManager2() {
        return controleManager2;
    }
}
