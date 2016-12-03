package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor.EntradaGrafico_Vetor;

public final class GraficoManager2 implements TipoSingleton, TipoDesenhavel, Disposable{
    
    private static GraficoManager2 graficoManager;

    private final FaseManager2 faseManager2;

    private final Map<GraficosEnum2, EntradaGrafica> mapaEntradaGraficas;

    public GraficoManager2() {
        this.mapaEntradaGraficas = new HashMap<GraficosEnum2, EntradaGrafica>(1);
        this.mapaEntradaGraficas.put(GraficosEnum2.VETOR, new EntradaGrafico_Vetor());

        this.faseManager2 = FaseManager2.getInstancia();
    }

    @Override
    public void inicializar() {
        graficoManager = this;
    }

    public static GraficoManager2 getInstancia() {
        return graficoManager;
    }
    private EntradaGrafica graficoAtual;    

    @Override
    public void meDesenhar(Object objeto) {
       if(this.faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
           this.graficoAtual.meDesenhar(null);
       }
    }   

    public void setGraficoEscolhido(GraficosEnum2 graficoEnum){
        this.graficoAtual = this.mapaEntradaGraficas.get(graficoEnum);
    }

    public Map<GraficosEnum2, EntradaGrafica> getMapaEntradaGraficas() {
        return mapaEntradaGraficas;
    }

    @Override
    public void dispose() {
        for(EntradaGrafica entradaGrafica : this.mapaEntradaGraficas.values()){
            entradaGrafica.dispose();
        }
    }
}
