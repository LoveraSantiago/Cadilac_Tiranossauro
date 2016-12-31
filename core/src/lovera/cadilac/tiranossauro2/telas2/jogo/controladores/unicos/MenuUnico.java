package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.MenuManager2;

public class MenuUnico implements TipoSingleton {

    private static MenuUnico menuUnico;
    private final MenuManager2 menuManager;


    public MenuUnico() {
        this.menuManager = new MenuManager2();
    }

    @Override
    public void inicializar() {
        menuUnico = this;
    }

    public static MenuUnico getInstancia() {
        return menuUnico;
    }

    public MenuManager2 getMenuManager() {
        return menuManager;
    }
}
