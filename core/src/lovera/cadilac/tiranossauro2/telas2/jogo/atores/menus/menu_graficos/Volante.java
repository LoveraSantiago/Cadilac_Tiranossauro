package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

class Volante extends Actor implements TipoSingleton, Disposable{

    private static Volante volante;

    @Override
    public void inicializar() {
        volante = this;
    }

    public static Volante getInstancia() {
        return volante;
    }

    @Override
    public void dispose() {

    }
}
