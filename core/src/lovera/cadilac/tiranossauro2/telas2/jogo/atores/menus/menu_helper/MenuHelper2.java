package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_helper;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;

public final class MenuHelper2 implements TipoControlavel {

    private final ParserToTable_MenuHelper parserTable;

    private final Stage stage;

    public MenuHelper2() {
        this.parserTable = new ParserToTable_MenuHelper();
        this.stage = null;
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.stage;
    }
}
