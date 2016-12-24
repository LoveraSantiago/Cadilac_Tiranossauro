package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoCamera;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class CameraManager implements TipoCamera{

    private final CameraJogo cameraJogo;
    private final CameraProjecao cameraProjecao;
    private final CameraAngulo cameraAngulo;

    public CameraManager() {
        this.cameraProjecao = new CameraProjecao();
        this.cameraJogo = new CameraJogo();
        this.cameraAngulo = new CameraAngulo(this.cameraJogo);
    }

    @Override
    public void resize(int width, int height) {
        this.cameraJogo.resize(width, height);
        this.cameraProjecao.resize(width, height);
    }

    @Override
    public void atualizar() {
        this.cameraJogo.atualizar();
        this.cameraProjecao.atualizar();
    }

    public void updateSpriteBatch_CamJogo() {
        this.cameraJogo.updateSpriteBatch();
    }

    public void updateSpriteBatch_CamProj() {
        this.cameraProjecao.updateSpriteBatch();
    }

    public void normatizarAngulo(){
        this.cameraAngulo.normatizarAngulo();
    }

    public void rotacionarCameraEmVoltaDoPonto(float angulo){
        this.cameraAngulo.rotacionarCameraEmVoltaDoPonto(angulo);
    }

    public OrthographicCamera getCamera_CamProj() {
        return cameraProjecao.getCamera();
    }

    public Viewport getViewPort_CamProj(){
        return this.cameraProjecao.getViewport();
    }

    public OrthographicCamera getCamera_CamJogo() {
        return cameraJogo.getCamera();
    }

    public float getMaiorPtY_CamProj(){
        return this.cameraProjecao.getPtYMaior();
    }

    public float getAngulo_CamJogo(){
        return this.cameraAngulo.getAnguloAtual();
    }

    public Vector2 getPosicao_CamProj(){
        return this.cameraProjecao.getPosicao();
    }

    public void setPosicao_CamProj(float x, float y){
        this.cameraProjecao.setPosicao(x, y);
    }

    public Vector2 getPosicao_CamJogo(){
        return this.cameraJogo.getPosicao();
    }

    public void setPosicao_CamJogo(float x, float y){
        this.cameraJogo.setPosicao(x, y);
    }

}
