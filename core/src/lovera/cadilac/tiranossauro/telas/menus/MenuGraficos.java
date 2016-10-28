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
    private final Actor table;

    private final Bussola bussola;

    private final TextureAtlas atlas;

    public MenuGraficos(MensagemDeMenus msg, SpriteBatch spriteBatch, Viewport viewport) {
        this.msg = msg;

        this.stage = new Stage(viewport, spriteBatch);
        this.atlas = new TextureAtlas("imgs_botoes_duplos/btn_img_dbl.atlas");
        this.bussola = new Bussola(this);

        this.table = gerarTableBarraHud();
        this.stage.addActor(this.table);
        setBarraPosicao_Inicial();
    }

    private Table gerarTableBarraHud(){
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
    public void meDesenhar(SpriteBatch spriteBatch) {
        analisarPosicaoDaBarra();
        this.table.setPosition(this.posicaoBarra, 0);
        this.stage.draw();
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.stage;
    }

    private void analisarPosicaoDaBarra() {
        switch (this.estadoPosicaoBarra){
            case FIXO:
                return;
            case ENTRADA:
                procedimentoEntradaBarra();
                break;
            case SAIDA:
                procedimentoSaidaBarra();
                break;
        }
    }

    private void procedimentoEntradaBarra(){
        if(this.posicaoBarra >= 0){
            setBarraPosicao_Normal();
        }
        else{
            this.posicaoBarra += this.VELOCIDADE_BARRA * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
        }
    }

    private void procedimentoSaidaBarra(){
        if(this.posicaoBarra >= this.FORA_DA_TELA){
            this.posicaoBarra -= this.VELOCIDADE_BARRA * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
        }
        else{
            setBarraPosicao_Inicial();
            msg.setFaseToFaseManager(Fase.ACEITAR_ENTRADA);
        }
    }

    private void setBarraPosicao_Inicial(){
        this.estadoPosicaoBarra = this.ENTRADA;
        this.posicaoBarra = this.FORA_DA_TELA;
    }

    private void setBarraPosicao_Normal(){
        this.estadoPosicaoBarra = this.FIXO;
        this.posicaoBarra = 0;
        this.bussola.calcularPontos();
    }

    private void setBarraPosicao_Saida(){
        this.estadoPosicaoBarra = this.SAIDA;
        this.posicaoBarra = 0;
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
        this.stage.dispose();
        this.atlas.dispose();
        this.bussola.dispose();
    }
}
