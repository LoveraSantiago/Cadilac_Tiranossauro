package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

class VolanteListener extends ActorGestureListener{

    @Override
    public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(Deslizador.getInstancia().isPosicaoBarraFixa()){

        }
    }

    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
        if(Deslizador.getInstancia().isPosicaoBarraFixa()){

        }
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
    }
}
