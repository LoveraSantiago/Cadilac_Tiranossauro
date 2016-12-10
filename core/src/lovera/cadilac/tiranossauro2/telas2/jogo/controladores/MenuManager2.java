package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos.MenuGraficos2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_helper.MenuHelper2;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

public final class MenuManager2 implements TipoSingleton, TipoDesenhavel, Disposable{

    private static MenuManager2 menuManager2;

    private final MenuGraficos2 menuGraficos;
    private final MenuHelper2 menuHelper;

    private final FaseManager2 faseManager;

    public MenuManager2() {
        this.faseManager = FaseManager2.getInstancia();

        Viewport viewport = new StretchViewport(AjustadorDeTela2.LARGURA_TELA_MENU, AjustadorDeTela2.ALTURA_TELA_MENU);
        this.menuGraficos = new MenuGraficos2(viewport);
        this.menuHelper = new MenuHelper2(viewport);
    }

    @Override
    public void inicializar() {
        menuManager2 = this;
    }

    public static MenuManager2 getInstancia() {
        return menuManager2;
    }

    //CASOS ACEITAR ENTRADA E JOGANDO PARA POSICIONAR FORA E DENTRO DA TELA ATE TER O SEU PROPRIO COMPORTAMENTO PERSONALIZADO
    @Override
    public void meDesenhar(Object objeto) {
        switch (this.faseManager.getFaseAtual()){
            case ESCOLHENDO_GRAFICO :
                this.menuGraficos.meDesenhar(null);
                break;
            case ACEITAR_ENTRADA :
                this.menuHelper.meDesenhar(0f);
                break;
            case JOGANDO:
                this.menuHelper.meDesenhar(-160f);
                break;
        }
    }

    public TipoControlavel getControlavelMenuGraficos(){
        return this.menuGraficos;
    }

    public TipoControlavel getControlavelMenuHelper(){
        return this.menuHelper;
    }

    @Override
    public void dispose() {
        this.menuGraficos.dispose();
        this.menuHelper.dispose();
    }
}
