package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

public final class Rotacionador {

    private float angulo;
    private float cos;
    private float sen;

    private float tempX;
    private float tempY;

    private float resultX;
    private float resultY;

    private final static CameraManager cameraManager;

    static{
        cameraManager = CameraUnico.getCameraManager();
    }

    public Rotacionador() {
    }

    public void atualizarAnguloDoJogo(){
        this.angulo = this.cameraManager.getAngulo_CamJogo();

        this.cos = MathUtils.cosDeg(this.angulo);
        this.sen = MathUtils.sinDeg(this.angulo);
    }

    public void rotacionar(float pt1x, float pt1y, float pt2x, float pt2y){
        this.tempX = pt1x - pt2x;
        this.tempY = pt1y - pt2y;

        this.resultX = ((this.tempX * this.cos) - (this.tempY * this.sen)) + pt2x;
        this.resultY = ((this.tempY * this.cos) + (this.tempX * this.sen)) + pt2y;
    }

    public void rotacionar(float pt1x, float pt1y, Vector2 pt2){
        rotacionar(pt1x, pt1y, pt2.x, pt2.y);
    }

    public float getResultX() {
        return resultX;
    }

    public float getResultY() {
        return resultY;
    }
}
