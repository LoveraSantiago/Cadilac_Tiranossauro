package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro.atores.graficos.utils.GraficosEnum;
import lovera.cadilac.tiranossauro.telas.menus.MenuUtils;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;

class ParserToTable_MenuGraficos2 implements TipoParseavel, Disposable{

    private TextureAtlas atlas;

    //CRIANDO A TABLE DE MENUS
    @Override
    public Table meTransforme(Object objeto) {
        this.atlas = new TextureAtlas("imgs_botoes_duplos/btn_img_dbl.atlas");

        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        skin.addRegions(this.atlas);

        final ImageButton btnVetor = gerarBotao("reta_duplo"       , skin, GraficosEnum.VETOR      );
        final ImageButton btnExp   = gerarBotao("exponencial_duplo", skin, GraficosEnum.EXPONENCIAL);
        final ImageButton btnLog   = gerarBotao("logaritmo_duplo"  , skin, GraficosEnum.LOGARITMO  );
        final ImageButton btnParab = gerarBotao("paraboloide_duplo", skin, GraficosEnum.PARABOLOIDE);

        Table tableResult =  new Table();
//        tableResult.setDebug(true);
        tableResult.bottom();
        tableResult.setFillParent(true);

        tableResult.add(bussola).width(MenuUtils.LARGURA_BTN * 4).height(MenuUtils.HALTURA_BTN * 2).colspan(4);
        tableResult.row();

        tableResult.add(btnVetor).width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padRight(1).padBottom(5);
        tableResult.add(btnExp)  .width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padRight(1).padBottom(5);
        tableResult.add(btnLog)  .width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padRight(1).padBottom(5);
        tableResult.add(btnParab).width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padBottom(5);
        return tableResult;
    }

    private ImageButton gerarBotao(String img, Skin skin, final GraficosEnum graficoEnum){

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        style.imageUp = skin.getDrawable(img);
        style.imageDown = skin.getDrawable(img);

        ImageButton btn = new ImageButton(style);
        btn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(estadoPosicaoBarra != FIXO) return;
                msg.graficoEscolhido(graficoEnum);
                setBarraPosicao_Saida();
            }
        });
        return btn;
    }

    @Override
    public void dispose() {
        this.atlas.dispose();
    }
}
