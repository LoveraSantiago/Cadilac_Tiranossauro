package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

public final class TelaJogo2 implements Screen{

    SingletonsManager_TelaJogo singletonsManager;

    public TelaJogo2() {
        this.singletonsManager = new SingletonsManager_TelaJogo();
        this.singletonsManager.iniciliazarSingletons();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.singletonsManager.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        CameraUnico.getCameraManager().resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.singletonsManager.dispose();
    }
}
