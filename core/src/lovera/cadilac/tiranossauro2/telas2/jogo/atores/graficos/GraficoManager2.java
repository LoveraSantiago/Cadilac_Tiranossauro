package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos;

import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial.DesenhadorGraficoGraf_Exponencial;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo.DesenhadorGraficoGraf_Logaritmo;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola.DesenhadorGraf_Parabola;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor.DesenhadorGraf_Vetor;

public final class GraficoManager2 implements TipoDesenhavel, Disposable{
    
    private EntradaGrafica graficoAtual;
    private final Map<GraficosEnum2, EntradaGrafica> mapaEntradaGraficas;

    public GraficoManager2() {
        this.mapaEntradaGraficas = new HashMap<GraficosEnum2, EntradaGrafica>(4);
        this.mapaEntradaGraficas.put(GraficosEnum2.VETOR      , new EntradaGrafica(DesenhadorGraf_Vetor.class));
        this.mapaEntradaGraficas.put(GraficosEnum2.PARABOLOIDE, new EntradaGrafica(DesenhadorGraf_Parabola.class));
        this.mapaEntradaGraficas.put(GraficosEnum2.EXPONENCIAL, new EntradaGrafica(DesenhadorGraficoGraf_Exponencial.class));
        this.mapaEntradaGraficas.put(GraficosEnum2.LOGARITMO  , new EntradaGrafica(DesenhadorGraficoGraf_Logaritmo.class));

        this.graficoAtual = new EntradaGrafica();
    }

    @Override
    public void meDesenhar(Object objeto) {
           this.graficoAtual.meDesenhar(null);
    }   

    public void setGraficoEscolhido(GraficosEnum2 graficoEnum){
        this.graficoAtual = this.mapaEntradaGraficas.get(graficoEnum);
        this.graficoAtual.configurarAreaJogavel(graficoEnum);
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
