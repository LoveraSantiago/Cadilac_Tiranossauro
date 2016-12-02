package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_helper;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;

class ParserToTable_MenuHelper implements TipoParseavel, Disposable{

    private TextureAtlas atlas;

    @Override
    public Object meTransforme(Object objeto) {
        return null;
    }

    @Override
    public void dispose() {
        this.atlas.dispose();
    }
}
