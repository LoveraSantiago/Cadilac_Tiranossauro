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

import lovera.cadilac.tiranossauro.atores.Bussola;
import lovera.cadilac.tiranossauro.atores.graficos.utils.GraficosEnum;
import lovera.cadilac.tiranossauro.contratos.Controlavel;
import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.contratos.MensagemBussola;
import lovera.cadilac.tiranossauro.contratos.MensagemDeMenus;
import lovera.cadilac.tiranossauro.controladores.Fase;

class MenuGraficos implements Desenhavel, Controlavel, Disposable, MensagemBussola{

    //Estados da posicao da Barra
    private final byte ENTRADA = 0;
    private final byte FIXO    = 1;
    private final byte SAIDA   = 2;
    private byte estadoPosicaoBarra;

    private final short FORA_DA_TELA = -162;
    private short posicaoBarra = 0;

    private final short VELOCIDADE_BARRA = 500;

    private final MensagemDeMenus msg;

    private final Stage stage;
    private final Actor table;//ok

    private final Bussola bussola;

    private final TextureAtlas atlas;

    public MenuGraficos(MensagemDeMenus msg, SpriteBatch spriteBatch, Viewport viewport) {
        this.msg = msg;

        this.stage = new Stage(viewport, spriteBatch);//ok
        this.atlas = new TextureAtlas("imgs_botoes_duplos/btn_img_dbl.atlas");//ok
        this.bussola = new Bussola(this);//ok

        this.table = gerarTableBarraHud();//ok
        this.stage.addActor(this.table);
        setBarraPosicao_Inicial();//ok
    }

    private Table gerarTableBarraHud(){//ok
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));//ok
        skin.addRegions(this.atlas);//ok

        final ImageButton btnVetor = gerarBotao("reta_duplo"       , skin, GraficosEnum.VETOR      );//ok
        final ImageButton btnExp   = gerarBotao("exponencial_duplo", skin, GraficosEnum.EXPONENCIAL);//ok
        final ImageButton btnLog   = gerarBotao("logaritmo_duplo"  , skin, GraficosEnum.LOGARITMO  );//ok
        final ImageButton btnParab = gerarBotao("paraboloide_duplo", skin, GraficosEnum.PARABOLOIDE);//ok

        Table tableResult =  new Table();//ok
//        tableResult.setDebug(true);
        tableResult.bottom();//ok
        tableResult.setFillParent(true);//ok

        tableResult.add(bussola).width(MenuUtils.LARGURA_BTN * 4).height(MenuUtils.HALTURA_BTN * 2).colspan(4);//ok
        tableResult.row();//ok

        tableResult.add(btnVetor).width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padRight(1).padBottom(5);//ok
        tableResult.add(btnExp)  .width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padRight(1).padBottom(5);//ok
        tableResult.add(btnLog)  .width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padRight(1).padBottom(5);//ok
        tableResult.add(btnParab).width(MenuUtils.LARGURA_BTN).height(MenuUtils.HALTURA_BTN).padBottom(5);//ok
        return tableResult;//ok
    }

    private ImageButton gerarBotao(String img, Skin skin, final GraficosEnum graficoEnum){//ok

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));//ok
        style.imageUp = skin.getDrawable(img);//ok
        style.imageDown = skin.getDrawable(img);//ok

        ImageButton btn = new ImageButton(style);//ok
        btn.addListener(new ClickListener(){//ok

            @Override//ok
            public void clicked(InputEvent event, float x, float y) {//ok

                if(estadoPosicaoBarra != FIXO) return;//ok
                msg.graficoEscolhido(graficoEnum);//ok
                setBarraPosicao_Saida();//ok
            }
        });

        return btn;//ok
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        analisarPosicaoDaBarra();//ok
        this.table.setPosition(this.posicaoBarra, 0);//ok
        this.stage.draw();//ok
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.stage;
    }

    private void analisarPosicaoDaBarra() {//ok
        switch (this.estadoPosicaoBarra){//ok
            case FIXO://ok
                return;//ok
            case ENTRADA://ok
                procedimentoEntradaBarra();//ok
                break;//ok
            case SAIDA://ok
                procedimentoSaidaBarra();//ok
                break;//ok
        }
    }

    private void procedimentoEntradaBarra(){//ok
        if(this.posicaoBarra >= 0){//ok
            setBarraPosicao_Normal();//ok
        }//ok
        else{//ok
            this.posicaoBarra += this.VELOCIDADE_BARRA * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);//ok
        }//ok
    }//ok

    private void procedimentoSaidaBarra(){//ok
        if(this.posicaoBarra >= this.FORA_DA_TELA){//ok
            this.posicaoBarra -= this.VELOCIDADE_BARRA * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);//ok
        }//ok
        else{//ok
            setBarraPosicao_Inicial();//ok
            msg.setFaseToFaseManager(Fase.ACEITAR_ENTRADA);//ok
        }//ok
    }//ok

    private void setBarraPosicao_Inicial(){//ok
        this.estadoPosicaoBarra = this.ENTRADA;//ok
        this.posicaoBarra = this.FORA_DA_TELA;//ok
    }

    private void setBarraPosicao_Normal(){//ok
        this.estadoPosicaoBarra = this.FIXO;//ok
        this.posicaoBarra = 0;//ok
        this.bussola.calcularPontos();//ok
    }

    private void setBarraPosicao_Saida(){//ok
        this.estadoPosicaoBarra = this.SAIDA;//ok
        this.posicaoBarra = 0;//ok
    }

    @Override
    public boolean isBarraHudFixa() {
        return this.estadoPosicaoBarra == this.FIXO;
    }

    @Override
    public void rotacionandoCamera(float angulo) {
        this.msg.rotacionandoCamera(angulo);
    }

    @Override
    public void normatizarAngulo() {
        this.msg.normatizarAngulo();
    }

    @Override
    public void dispose() {
        this.stage.dispose();//ok
        this.atlas.dispose();//ok
        this.bussola.dispose();//ok
    }
}
