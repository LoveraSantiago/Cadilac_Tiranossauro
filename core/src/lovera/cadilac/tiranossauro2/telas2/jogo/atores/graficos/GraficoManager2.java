package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos;

import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial.DesenhadorGraf_Exponencial2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo.DesenhadorGraf_Logaritmo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola.DesenhadorGraf_Parabola;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor.DesenhadorGraf_Vetor;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;

import static lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2.ACEITAR_ENTRADA;
import static lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2.JOGANDO;

public final class GraficoManager2 implements TipoDesenhavel, Disposable{

    private GraficosEnum2 graficoEnumAtual;
    private EntradaGrafica graficoAtual;

    private final Map<GraficosEnum2, EntradaGrafica> mapaEntradaGraficas;

    private final FaseManager2 faseManager2;

    public GraficoManager2() {
        this.mapaEntradaGraficas = new HashMap<GraficosEnum2, EntradaGrafica>(4);
        this.mapaEntradaGraficas.put(GraficosEnum2.VETOR      , new EntradaGrafica(DesenhadorGraf_Vetor.class));
        this.mapaEntradaGraficas.put(GraficosEnum2.PARABOLOIDE, new EntradaGrafica(DesenhadorGraf_Parabola.class));
        this.mapaEntradaGraficas.put(GraficosEnum2.EXPONENCIAL, new EntradaGrafica(DesenhadorGraf_Exponencial2.class));
        this.mapaEntradaGraficas.put(GraficosEnum2.LOGARITMO  , new EntradaGrafica(DesenhadorGraf_Logaritmo2.class));

        this.graficoAtual = new EntradaGrafica();

        this.faseManager2 = FaseUnico.getInstancia().getFaseManager2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(faseManager2.isUmaDasFaseAtual(ACEITAR_ENTRADA, JOGANDO)){
            this.graficoAtual.meDesenhar(null);
        }
    }

    public void setGraficoEscolhido(GraficosEnum2 graficoEnum){
        this.graficoAtual = this.mapaEntradaGraficas.get(graficoEnum);
        this.graficoAtual.updateAreaCamera(graficoEnum);
        this.graficoEnumAtual = graficoEnum;
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

    public GraficosEnum2 getGraficoEnumAtual() {
        return graficoEnumAtual;
    }

    public boolean isGraficoEnumAtual(GraficosEnum2 graficoEnum2){
        return this.graficoEnumAtual.equals(graficoEnum2);
    }
}
