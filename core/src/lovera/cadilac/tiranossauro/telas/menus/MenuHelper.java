package lovera.cadilac.tiranossauro.telas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro.contratos.Controlavel;
import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.contratos.MensagemDeMenus;

class MenuHelper implements Desenhavel, Controlavel, Disposable {

    private final MensagemDeMenus msg;

    private final Stage stage;
    private final Actor table;

    private final TextureAtlas atlas;

    public MenuHelper(MensagemDeMenus msg, SpriteBatch spriteBatch, Viewport viewport) {
        this.msg = msg;

        this.stage = new Stage(viewport, spriteBatch);
        this.atlas = new TextureAtlas("imgs_botoes_menuhelper/btn_menu_helper.atlas");

        this.table = gerarTableBarraHud();
        this.stage.addActor(this.table);
    }

    private Table gerarTableBarraHud(){
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        skin.addRegions(this.atlas);

        final ImageButton btnVoltar = gerarBotao("voltar", skin, MenuManager.BTN_VOLTAR);
        final ImageButton btnHelp   = gerarBotao("duvida", skin, MenuManager.BTN_HELPER);

        Table tableResult = new Table();
        tableResult.top().left();
        tableResult.setFillParent(true);

        tableResult.add(btnHelp)  .width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padLeft(1).padRight(1).padTop(5);
        tableResult.add(btnVoltar).width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padTop(5);
        return tableResult;
    }

    private ImageButton gerarBotao(final String img, Skin skin, final byte btnEscolhido){
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        style.imageUp = skin.getDrawable(img);
        style.imageDown = skin.getDrawable(img);

        ImageButton btn = new ImageButton(style);
        btn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                msg.helperEscolhido(btnEscolhido);
            }
        });
        return btn;
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
//        analisarPosicaoDaBarra();
        this.table.setPosition(0, 0);
        this.stage.draw();
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.stage;
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.atlas.dispose();
    }
}

