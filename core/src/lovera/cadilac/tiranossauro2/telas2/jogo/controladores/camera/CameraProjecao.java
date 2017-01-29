package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSubCamera;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

//TODO refatorar com CameraJogo
final class CameraProjecao implements TipoSubCamera{

    private float ptYMaior;
    private float ptYMenor;
    private float ptXmenor;
    private float ptXMaior;

    private final Rectangle area;

    private final Vector2 diferenca;
    private final Vector2 posicaoTemp;

    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;


    private final Viewport viewport;
    public CameraProjecao() {
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(AjustadorDeTela2.LARGURA_TELA, AjustadorDeTela2.ALTURA_TELA, this.camera);
        this.viewport.apply();

        this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2, 0);
        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();

        this.posicaoTemp = new Vector2();
        this.diferenca = new Vector2();
        this.area = new Rectangle();
    }

    @Override
    public void atualizar() {
        this.camera.update();
    }

    @Override
    public void updateSpriteBatch() {
        this.spriteBatch.setProjectionMatrix(this.camera.combined);
    }

    //OTIMIZAR
    public float getPtYMaior(){
        this.ptYMaior = -1f;

        for(int i = 0; i < this.camera.frustum.planePoints.length; i++) {
            this.ptYMaior = Math.max(this.ptYMaior, this.camera.frustum.planePoints[i].y);
        }
        return ptYMaior;
    }

    //OTIMIZAR
    public float getPtXMaior(){
        this.ptXMaior = -1f;

        for(int i = 0; i < this.camera.frustum.planePoints.length; i++) {
            this.ptXMaior = Math.max(this.ptXMaior, this.camera.frustum.planePoints[i].x);
        }
        return ptXMaior;
    }

    //OTIMIZAR
    private void getPtsArea(){
        this.ptYMaior = -1f;
        this.ptXMaior = -1f;
        this.ptXmenor = 100f;
        this.ptYMenor = 100f;

        for(int i = 0; i < this.camera.frustum.planePoints.length; i++){
            this.ptYMaior = Math.max(this.ptYMaior, this.camera.frustum.planePoints[i].y);
            this.ptYMenor = Math.min(this.ptYMenor, this.camera.frustum.planePoints[i].y);
            this.ptXMaior = Math.max(this.ptXMaior, this.camera.frustum.planePoints[i].x);
            this.ptXmenor = Math.min(this.ptXmenor, this.camera.frustum.planePoints[i].x);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public Vector2 getPosicao() {
        return this.posicaoTemp.set(this.camera.position.x, this.camera.position.y);
    }

    @Override
    public void setPosicao(float x, float y) {
        this.camera.position.set(x, y, 0);
    }

    public Rectangle getArea(){
        getPtsArea();
        return this.area.set(this.ptXmenor, this.ptYMenor, this.ptXMaior - this.ptXmenor, this.ptYMaior - this.ptYMenor);
    }

    @Override
    public void setDiferenca() {
        this.posicaoTemp.set(CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getPosicaoJogo());
        this.diferenca.set(this.camera.position.x - this.posicaoTemp.x,this.camera.position.y - this.posicaoTemp.y);
    }

    @Override
    public Vector2 getDiferenca() {
        return diferenca;
    }
}
