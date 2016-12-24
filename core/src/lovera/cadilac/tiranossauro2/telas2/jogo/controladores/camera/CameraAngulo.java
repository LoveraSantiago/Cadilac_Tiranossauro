package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;

final class CameraAngulo {

    private float anguloAtual;

    private final Vector3 eixoZ;
    private final Vector3 posicaoTemp;

    private final CameraJogo cameraJogo;

    public CameraAngulo(CameraJogo cameraJogo) {
        this.cameraJogo = cameraJogo;

        this.anguloAtual = 0;
        this.posicaoTemp = new Vector3();
        this.eixoZ       = new Vector3(0, 0, 1);
    }

    public void rotacionarCameraEmVoltaDoPonto(float angulo){
        this.posicaoTemp.set(CorredorManager.getInstancia().getCorredorP().getPosicaoJogo(), 0);

        this.cameraJogo.getCamera().rotateAround(this.posicaoTemp, this.eixoZ, angulo);
        this.anguloAtual = this.anguloAtual + angulo;
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

    public float getAnguloAtual() {
        return anguloAtual;
    }
}
