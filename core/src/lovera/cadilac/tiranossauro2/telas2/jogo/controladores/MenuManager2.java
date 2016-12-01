package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos.MenuGraficos2;

public class MenuManager2 implements TipoSingleton, TipoDesenhavel, Disposable{

    private static MenuManager2 menuManager2;

    private MenuGraficos2 menuGraficos;

    @Override
    public void inicializar() {
        menuManager2 = this;

        this.menuGraficos = new MenuGraficos2();
    }

    public static MenuManager2 getInstancia() {
        return menuManager2;
    }

    @Override
    public void meDesenhar(Object objeto) {
        switch (FaseManager2.getInstance().getFaseAtual()){
            case ESCOLHENDO_GRAFICO :
                this.menuGraficos.meDesenhar(null);
                break;
            case ACEITAR_ENTRADA :
//                this.menuHelper.meDesenhar(spriteBatch);
                break;
        }
    }

    @Override
    public void dispose() {
        this.menuGraficos.dispose();
    }
}
