package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;

final class CameraAngulo {

    private float anguloAtual;

    private final Vector2 ptDeRotacao;//TODO apagar esse cara?
    private final Vector2 posicaoCorredor;

    private final Vector3 eixoZ;
    private final Vector3 posicaoTemp;

    private final CameraJogo cameraJogo;

    public CameraAngulo(CameraJogo cameraJogo) {
        this.cameraJogo = cameraJogo;
        this.posicaoCorredor = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();

        this.anguloAtual = 0;
        this.ptDeRotacao = new Vector2();
        this.posicaoTemp = new Vector3();
        this.eixoZ       = new Vector3(0, 0, 1);
    }

    public void rotacionarCameraEmVoltaDoPonto(float angulo){
        this.posicaoTemp.set(this.posicaoCorredor, 0);

        this.cameraJogo.getCamera().rotateAround(this.posicaoTemp, this.eixoZ, angulo);
        this.anguloAtual = this.anguloAtual + angulo;
        this.ptDeRotacao.set(this.posicaoTemp.x, this.posicaoTemp.y);
    }

    public void normatizarAngulo(){
        if(this.anguloAtual < 0){
            while(this.anguloAtual < 0){
                this.anguloAtual += 360;
            }
            return;
        }
        else if(this.anguloAtual > 0){
            while(this.anguloAtual > 360){
                this.anguloAtual -= 360;
            }
            return;
        }
    }
}
