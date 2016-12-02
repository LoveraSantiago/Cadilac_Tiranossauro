package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import java.util.Map;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;

public final class ControleManager2 implements TipoSingleton{

    private static ControleManager2 controleManager;

    private InputMultiplexer inputMultiplexer;

    private TipoControlavel controleMenuGrafico;
    private TipoControlavel controleMenuHelper;
    private TipoControlavel controlavelAtual;

    private Map<GraficosEnum2, TipoControlavel> mapaControlaveis;


    @Override
    public void inicializar() {
        controleManager = this;
        this.inputMultiplexer = new InputMultiplexer();

        MenuManager2 menuManagerTemp = MenuManager2.getInstancia();
        this.controleMenuGrafico = menuManagerTemp.getControlavelMenuGraficos();
        this.controleMenuHelper  = menuManagerTemp.getControlavelMenuHelper();

        adicionarInputProcessor(this.controleMenuGrafico);

        //SETANDO ALGUEM TEMPORARIAMENTE OBRIGATORIO EVITAR NULL POINTER E CHECAGEM A TODOS RENDER
        this.controlavelAtual = this.controleMenuGrafico;

//        this.mapaControlaveis = new HashMap<GraficosEnum2, TipoControlavel>(mapaGraficos.size());
//        for (Map.Entry<GraficosEnum2, Grafico> chaveValor : mapaGraficos.entrySet()) {
//
//            this.mapaControlaveis.put(chaveValor.getKey(), chaveValor.getValue());
//        }

        iniciar();
    }

    public static ControleManager2 getInstance() {
        return controleManager;
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

//        this.controlavelAtual = this.mapaControlaveis.get(graficoEnum);
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
