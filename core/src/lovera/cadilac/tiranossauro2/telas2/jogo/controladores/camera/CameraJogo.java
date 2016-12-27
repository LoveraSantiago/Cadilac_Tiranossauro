package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoCamera;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSubCamera;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

//CAMERAJOGO E ROTACIONADA CONTEM AS POSICOES VERDADEIRA DO JOGO
final class CameraJogo implements TipoSubCamera{

    private final OrthographicCamera camera;
    private final Viewport viewport;

    private final Vector2 posicaoTemp;

    private final SpriteBatch spriteBatch;

    private final Vector2 diferenca;

    public CameraJogo() {
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(AjustadorDeTela2.LARGURA_TELA, AjustadorDeTela2.ALTURA_TELA, this.camera);
        this.viewport.apply();

        this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2, 0);
        this.spriteBatch = mSpriteBatch.getInstancia();

        this.posicaoTemp = new Vector2();
        this.diferenca = new Vector2();
    }

    @Override
    public void atualizar() {
        this.camera.update();
    }

    @Override
    public void updateSpriteBatch(){
       this.spriteBatch.setProjectionMatrix(this.camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public Vector2 getPosicao() {
        return this.posicaoTemp.set(this.camera.position.x, this.camera.position.y);
    }

    @Override
    public void setPosicao(float x, float y) {
        this.camera.position.set(x, y, 0);
    }

    @Override
    public void setDiferenca() {
        this.posicaoTemp.set(CorredorManager.getInstancia().getCorredorP().getPosicaoJogo());
        this.diferenca.set(this.camera.position.x - this.posicaoTemp.x,this.camera.position.y - this.posicaoTemp.y);
    }

    @Override
    public Vector2 getDiferenca() {
        return diferenca;
    }
}
