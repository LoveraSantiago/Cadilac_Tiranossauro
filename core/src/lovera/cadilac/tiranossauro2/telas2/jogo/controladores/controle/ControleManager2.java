package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.controle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import java.util.HashMap;
import java.util.Map;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.MenuManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.MenuUnico;

public final class ControleManager2{

    private final InputMultiplexer inputMultiplexer;

    private final TipoControlavel controleMenuGrafico;
    private final TipoControlavel controleMenuHelper;
    private TipoControlavel controlavelAtual;

    private final Map<GraficosEnum2, TipoControlavel> mapaControlaveis;

    public ControleManager2(Map<GraficosEnum2, EntradaGrafica> mapaEntradaGraficas) {
        this.inputMultiplexer = new InputMultiplexer();

        this.mapaControlaveis = new HashMap<GraficosEnum2, TipoControlavel>(mapaEntradaGraficas.size());
        for (Map.Entry<GraficosEnum2, EntradaGrafica> chaveValor : mapaEntradaGraficas.entrySet()) {

            this.mapaControlaveis.put(chaveValor.getKey(), chaveValor.getValue());
        }

        MenuManager2 menuManagerTemp = MenuUnico.getInstancia().getMenuManager();
        this.controleMenuGrafico = menuManagerTemp.getControlavelMenuGraficos();
        this.controleMenuHelper  = menuManagerTemp.getControlavelMenuHelper();

        adicionarInputProcessor(this.controleMenuGrafico);

        //SETANDO ALGUEM TEMPORARIAMENTE OBRIGATORIO EVITAR NULL POINTER E CHECAGEM A TODOS RENDER
        this.controlavelAtual = this.controleMenuGrafico;
        iniciar();
    }

    private void adicionarInputProcessor(TipoControlavel controlavel){
        this.inputMultiplexer.addProcessor(controlavel.passarInputProcessor());
    }

    private void removerInputProcessor(TipoControlavel controlavel){
        this.inputMultiplexer.removeProcessor(controlavel.passarInputProcessor());
    }

    private void removerInputProcessors(){
        removerInputProcessor(this.controlavelAtual);
        removerInputProcessor(this.controleMenuGrafico);
    }

    public void setarControlavelAtual(GraficosEnum2 graficoEnum){
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
