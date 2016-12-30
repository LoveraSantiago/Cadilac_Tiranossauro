package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.componente.tela.SpriteBatchManager;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromDeslizador;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

public final class MenuGraficos2 implements TipoDesenhavel, Disposable, MsgFromDeslizador, TipoControlavel{

    private final ParserToTable_MenuGraficos2 parserTable;
    private final Deslizador deslizador;

    private final Stage stage;
    private final Actor table;
    private final Volante volante;

    public MenuGraficos2(Viewport viewport) {
        this.deslizador = new Deslizador(this);

        this.volante = new Volante(this.deslizador);

        this.parserTable = new ParserToTable_MenuGraficos2(this.volante, this.deslizador);
        this.table = this.parserTable.meTransforme(this.deslizador);

        this.stage = new Stage(viewport, SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch());
        this.stage.addActor(this.table);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.deslizador.analisarPosicaoDaBarra();
        this.table.setPosition(this.deslizador.getPosicaoBarra(), 0);
        this.stage.draw();
    }

    @Override
    public void setBarraPosicao_Normal() {
        this.volante.calcularPontos();
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.stage;
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.parserTable.dispose();
        this.volante.dispose();
    }
}
