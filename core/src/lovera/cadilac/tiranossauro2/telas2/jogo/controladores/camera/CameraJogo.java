package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoCamera;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

//CAMERAJOGO E ROTACIONADA CONTEM AS POSICOES VERDADEIRA DO JOGO
class CameraJogo implements TipoCamera{

    private final OrthographicCamera camera;
    private final Viewport viewport;

    public CameraJogo() {
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(AjustadorDeTela2.LARGURA_TELA, AjustadorDeTela2.ALTURA_TELA, this.camera);
        this.viewport.apply();

        this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2, 0);
    }

    @Override
    public void atualizar() {
        this.camera.update();
    }

    @Override
    public void updateSpriteBatch(){
        mSpriteBatch.getInstance().setProjectionMatrix(this.camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
