package lovera.cadilac.tiranossauro.controladores;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.Map;

import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.utils.GraficosEnum;
import lovera.cadilac.tiranossauro.contratos.Desenhavel;

public final class GraficoManager implements Desenhavel, Disposable {

    private Grafico graficoAtual;
    private final Map<GraficosEnum, Grafico> mapaGraficos;


    public GraficoManager(Map<GraficosEnum, Grafico> mapaGraficos) {
        this.mapaGraficos = mapaGraficos;
    }

    public void setarGraficoAtual(GraficosEnum graficoEnum){
        this.graficoAtual = this.mapaGraficos.get(graficoEnum);
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        if(this.graficoAtual == null) return;

        this.graficoAtual.meDesenhar(spriteBatch);
    }

    @Override
    public void dispose() {
        for(Grafico grafico : this.mapaGraficos.values()){
            grafico.dispose();
        }
    }
}
