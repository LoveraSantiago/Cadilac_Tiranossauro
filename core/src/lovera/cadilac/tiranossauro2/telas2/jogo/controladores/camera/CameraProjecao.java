package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoCamera;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

final class CameraProjecao implements TipoCamera{

    private final OrthographicCamera camera;
    private final Viewport viewport;

    private float ptYMaior;

    public CameraProjecao() {
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
    public void updateSpriteBatch() {

    }

    public float getPtYMaior(){
        this.ptYMaior = -1;

        for(int i = 0; i < this.camera.frustum.planePoints.length; i++) {
            ptYMaior = Math.max(ptYMaior, this.camera.frustum.planePoints[i].x);
        }
        return ptYMaior;
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
