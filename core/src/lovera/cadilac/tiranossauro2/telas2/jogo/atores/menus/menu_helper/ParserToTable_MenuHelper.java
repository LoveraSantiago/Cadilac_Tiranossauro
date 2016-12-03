package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.utils.MenuUtils2;

class ParserToTable_MenuHelper implements TipoParseavel, Disposable{

    private TextureAtlas atlas;

    @Override
    public Actor meTransforme(Object objeto) {
        this.atlas = new TextureAtlas("imgs_botoes_menuhelper/btn_menu_helper.atlas");

        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        skin.addRegions(this.atlas);

        final ImageButton btnVoltar = gerarBotao("voltar", skin, new BtnVoltarListener());
        final ImageButton btnHelp   = gerarBotao("duvida", skin, new BtnHelpListener());

        Table tableResult = new Table();
        tableResult.top().left();
        tableResult.setFillParent(true);

        tableResult.add(btnHelp)  .width(MenuUtils2.LARGURA_BTN).height(MenuUtils2.HALTURA_BTN).padLeft(1).padRight(1).padTop(5);
        tableResult.add(btnVoltar).width(MenuUtils2.LARGURA_BTN).height(MenuUtils2.HALTURA_BTN).padTop(5);
        return tableResult;
    }

    private ImageButton gerarBotao(final String img, Skin skin, ClickListener clickListener){
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        style.imageUp = skin.getDrawable(img);
        style.imageDown = skin.getDrawable(img);

        ImageButton btn = new ImageButton(style);
        btn.addListener(clickListener);
        return btn;
    }

    @Override
    public void dispose() {
        this.atlas.dispose();
    }
}
