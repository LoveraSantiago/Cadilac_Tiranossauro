package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_helper;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

public final class MenuHelper2 implements TipoControlavel, TipoDesenhavel, Disposable{

    private final ParserToTable_MenuHelper parserTable;

    private final Stage stage;
    private final Actor table;

    public MenuHelper2(Viewport viewport) {
        this.parserTable = new ParserToTable_MenuHelper();
        this.table = parserTable.meTransforme(null);

        this.stage = new Stage(viewport, SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch());
        this.stage.addActor(this.table);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.table.setPosition((Float) objeto, 0);
        this.stage.draw();
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.stage;
    }

    @Override
    public void dispose() {
        this.parserTable.dispose();
    }
}
