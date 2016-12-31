package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

final class VolanteListener extends ActorGestureListener{

    private boolean toqueAcontecendo;
    private boolean panAcontecendo;

    private float ultimoAngulo;
    private float anguloFixo;
    private float anguloMovel;
    private float resultAnguloTemp;
    private float anguloResult;

    private final VolanteControle controle;
    private final Deslizador deslizador;

    private final Actor atorVolante;

    private final CameraManager cameraManager;
    private final Corredor2 corredor;


    public VolanteListener(Actor atorVolante, Deslizador deslizador, VolanteControle controle) {
        this.atorVolante = atorVolante;
        this.deslizador = deslizador;
        this.controle = controle;

        this.cameraManager = CameraUnico.getCameraManager();
        this.corredor = CorredorUnico.getInstancia().getCorredorManager().getCorredorP();
    }

    @Override
    public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(this.deslizador.isPosicaoBarraFixa()){
            this.anguloFixo = getAngulo(this.controle.getPtMedioBarra().x, this.controle.getPtMedioBarra().y,
                                   x + this.atorVolante.getX(), y + this.atorVolante.getY());
            this.ultimoAngulo = anguloFixo;
            this.toqueAcontecendo = true;
        }
    }

    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
        if(this.deslizador.isPosicaoBarraFixa()){
            this.panAcontecendo = true;
            this.anguloMovel = getAngulo(this.controle.getPtMedioBarra().x, this.controle.getPtMedioBarra().y, x + this.atorVolante.getX(), y + this.atorVolante.getY());

            if(this.ultimoAngulo != this.anguloMovel){
                this.anguloResult = -(anguloMovel - ultimoAngulo);

                this.cameraManager.rotacionarCameraEmVoltaDoPonto(this.anguloResult);
                this.corredor.telaAngulada(this.anguloResult);
            }
            ultimoAngulo = anguloMovel;
        }
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        this.anguloFixo = 0;
        this.anguloMovel = 0;
        this.toqueAcontecendo = false;
        this.panAcontecendo = false;
        this.cameraManager.normatizarAngulo();
    }

    private float getAngulo(float x0, float y0, float x1, float y1){
        this.resultAnguloTemp = (float) Math.toDegrees(Math.atan2(y1 - y0, x1 - x0));
        if(this.resultAnguloTemp < 0 && this.resultAnguloTemp > -90){
            return this.resultAnguloTemp = 0f;
        }
        else if(this.resultAnguloTemp < 0 && this.resultAnguloTemp <= -90){
            return this.resultAnguloTemp = 180f;
        }
        return this.resultAnguloTemp;
    }

    public float getAnguloFixo() {
        return anguloFixo;
    }

    public float getAnguloMovel() {
        return anguloMovel;
    }

    public boolean isToqueAcontecendo() {
        return toqueAcontecendo;
    }

    public boolean isPanAcontecendo() {
        return panAcontecendo;
    }
}
