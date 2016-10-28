package lovera.cadilac.tiranossauro.controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import java.util.HashMap;
import java.util.Map;

import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.utils.GraficosEnum;
import lovera.cadilac.tiranossauro.contratos.Controlavel;
import lovera.cadilac.tiranossauro.telas.menus.MenuManager;

public final class ControleManager {

    private final InputMultiplexer inputMultiplexer;

    private Controlavel controleMenuGrafico;
    private Controlavel controleMenuHelper;
    private Controlavel controlavelAtual;

    private final Map<GraficosEnum, Controlavel> mapaControlaveis;

    public ControleManager(MenuManager menuManager, Map<GraficosEnum, Grafico> mapaGraficos) {
        this.inputMultiplexer = new InputMultiplexer();

        this.controleMenuGrafico = menuManager.getControlavel_MenuGrafico();
        this.controleMenuHelper  = menuManager.getControlavel_MenuHelper();

        adicionarInputProcessor(this.controleMenuGrafico);

        //SETANDO ALGUEM TEMPORARIAMENTE OBRIGATORIO EVITAR NULL POINTER E CHECAGEM A TODOS RENDER
        this.controlavelAtual = this.controleMenuGrafico;

        this.mapaControlaveis = new HashMap<GraficosEnum, Controlavel>(mapaGraficos.size());
        for (Map.Entry<GraficosEnum, Grafico> chaveValor : mapaGraficos.entrySet()) {

            this.mapaControlaveis.put(chaveValor.getKey(), chaveValor.getValue());
        }

        iniciar();
    }

    private void adicionarInputProcessor(Controlavel controlavel){
        this.inputMultiplexer.addProcessor(controlavel.passarInputProcessor());
    }

    private void removerInputProcessor(Controlavel controlavel){
        this.inputMultiplexer.removeProcessor(controlavel.passarInputProcessor());
    }

    private void removerInputProcessors(){
        removerInputProcessor(this.controlavelAtual);
        removerInputProcessor(this.controleMenuGrafico);
    }

    public void setarControlavelAtual(GraficosEnum graficoEnum){
        removerInputProcessors();

        this.controlavelAtual = this.mapaControlaveis.get(graficoEnum);
        adicionarInputProcessor(this.controlavelAtual);
        adicionarInputProcessor(this.controleMenuHelper);
        iniciar();
    }

    private void iniciar(){
        Gdx.input.setInputProcessor(this.inputMultiplexer);
    }

    public void voltarMenuGrafico() {
        removerInputProcessor(this.controlavelAtual);
        this.inputMultiplexer.addProcessor(0, this.controleMenuGrafico.passarInputProcessor());
        iniciar();
    }
}
