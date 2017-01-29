package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.utils.MenuUtils2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ControleUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.GraficoUnico;

final class ParserToTable_MenuGraficos2 implements TipoParseavel, Disposable{

    private TextureAtlas atlas;

    private final Actor volante;
    private final Deslizador deslizador;

    public ParserToTable_MenuGraficos2(Actor volante, Deslizador deslizador) {
        this.volante = volante;
        this.deslizador = deslizador;
    }

    //CRIANDO A TABLE DE MENUS
    @Override
    public Table meTransforme(Object nulo) {
        this.atlas = new TextureAtlas("imgs_botoes_duplos/btn_img_dbl.atlas");

        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        skin.addRegions(this.atlas);

        final ImageButton btnVetor = gerarBotao("reta_duplo"       , skin, GraficosEnum2.VETOR      );
        final ImageButton btnExp   = gerarBotao("exponencial_duplo", skin, GraficosEnum2.EXPONENCIAL);
        final ImageButton btnLog   = gerarBotao("logaritmo_duplo"  , skin, GraficosEnum2.LOGARITMO  );
        final ImageButton btnParab = gerarBotao("paraboloide_duplo", skin, GraficosEnum2.PARABOLOIDE);

        Table tableResult =  new Table();
//        tableResult.setDebug(true);
        tableResult.bottom();
        tableResult.setFillParent(true);

        tableResult.add(this.volante).width(MenuUtils2.LARGURA_BTN * 4).height(MenuUtils2.HALTURA_BTN * 2).colspan(4);
        tableResult.row();

        tableResult.add(btnVetor).width(MenuUtils2.LARGURA_BTN).height(MenuUtils2.HALTURA_BTN).padRight(1).padBottom(5);
        tableResult.add(btnExp)  .width(MenuUtils2.LARGURA_BTN).height(MenuUtils2.HALTURA_BTN).padRight(1).padBottom(5);
        tableResult.add(btnLog)  .width(MenuUtils2.LARGURA_BTN).height(MenuUtils2.HALTURA_BTN).padRight(1).padBottom(5);
        tableResult.add(btnParab).width(MenuUtils2.LARGURA_BTN).height(MenuUtils2.HALTURA_BTN).padBottom(5);
        return tableResult;
    }

    private ImageButton gerarBotao(String img, Skin skin, final GraficosEnum2 graficoEnum){

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        style.imageUp = skin.getDrawable(img);
        style.imageDown = skin.getDrawable(img);

        ImageButton btn = new ImageButton(style);
        btn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(!deslizador.isPosicaoBarraFixa()) return;

                GraficoUnico.getInstancia().getGraficoManager2().setGraficoEscolhido(graficoEnum);
                ControleUnico.getInstancia().getControleManager2().setarControlavelAtual(graficoEnum);

                deslizador.setBarraPosicao_Saida();
            }
        });
        return btn;
    }

    @Override
    public void dispose() {
        this.atlas.dispose();
    }
}
