package lovera.cadilac.tiranossauro.telas.menus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro.atores.graficos.utils.GraficosEnum;
import lovera.cadilac.tiranossauro.contratos.Controlavel;
import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.contratos.MensagemDeMenus;
import lovera.cadilac.tiranossauro.controladores.Fase;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;

public class MenuManager implements Desenhavel, MensagemDeMenus, Disposable {

    private final MensagemDeMenus msg;

    private final MenuGraficos menuGraficos;
    private final MenuHelper menuHelper;

    public static byte BTN_VOLTAR = 0;
    public static byte BTN_HELPER = 1;

    public MenuManager(MensagemDeMenus msg, SpriteBatch spriteBatch) {
        this.msg = msg;

        Viewport viewport = new StretchViewport(AjustadorDeTela.LARGURA_TELA_MENU, AjustadorDeTela.ALTURA_TELA_MENU);

        this.menuGraficos = new MenuGraficos(this, spriteBatch, viewport);
        this.menuHelper   = new MenuHelper  (this, spriteBatch, viewport);
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        switch (this.msg.getFaseFromFaseManager()){
            case ESCOLHENDO_GRAFICO :
                this.menuGraficos.meDesenhar(spriteBatch);
                break;
            case ACEITAR_ENTRADA :
                this.menuHelper.meDesenhar(spriteBatch);
                break;
        }
    }

    @Override
    public void graficoEscolhido(GraficosEnum graficoEnum) {
        this.msg.graficoEscolhido(graficoEnum);
    }

    @Override
    public void helperEscolhido(byte btn) {
        this.msg.helperEscolhido(btn);
    }

    @Override
    public Fase getFaseFromFaseManager() {
        return null;
    }

    @Override
    public void setFaseToFaseManager(Fase faseAtual) {
        this.msg.setFaseToFaseManager(faseAtual);
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
        this.menuGraficos.dispose();
        this.menuHelper.dispose();
    }

    public Controlavel getControlavel_MenuGrafico(){
        return this.menuGraficos;
    }

    public Controlavel getControlavel_MenuHelper(){
        return this.menuHelper;
    }
}
