package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos.MenuGraficos2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_helper.MenuHelper2;

public final class MenuManager2 implements TipoSingleton, TipoDesenhavel, Disposable{

    private static MenuManager2 menuManager2;

    private final MenuGraficos2 menuGraficos;
    private final MenuHelper2 menuHelper;

    public MenuManager2() {
        this.menuGraficos = new MenuGraficos2();
        this.menuHelper = new MenuHelper2();
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
        switch (FaseManager2.getInstancia().getFaseAtual()){
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
