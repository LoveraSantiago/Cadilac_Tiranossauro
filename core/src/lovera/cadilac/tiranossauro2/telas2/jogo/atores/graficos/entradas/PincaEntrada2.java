package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

public final class PincaEntrada2 extends Entrada2 {

    private final FaseManager2 faseManager;
    private final OrthographicCamera cameraProjecao;

    private final Vector3 ptSuperior;
    private final Vector3 ptLateral;
    private final Vector3 ptSuperiorProjetado;
    private final Vector3 copiaPtSuperior;
    private final Vector3 copiaPtLateral;

    private final Corredor2 corredor;

    public PincaEntrada2() {
        this.faseManager = FaseManager2.getInstancia();
        this.cameraProjecao = CameraUnico.getCameraManager().getCameraProjecao();

        this.ptSuperior          = new Vector3();
        this.ptLateral           = new Vector3();
        this.ptSuperiorProjetado = new Vector3();
        this.copiaPtSuperior     = new Vector3();
        this.copiaPtLateral      = new Vector3();

        this.corredor = CorredorManager.getInstancia().getCorredorP();
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if(!this.faseManager.isFaseAtual(Fase2.JOGANDO) && !this.faseManager.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            return true;
        }

        determinarPontosMaximos(pointer1, pointer2);

        if(isPtValidos()){
            this.faseManager.setFaseAtual(Fase2.JOGANDO);
        }
        else{
            this.faseManager.setFaseAtual(Fase2.ACEITAR_ENTRADA);
            this.corredor.resetAngulo();
        }
        return false;
    }

    @Override
    public void pinchStop() {
        if(!this.faseManager.isFaseAtual(Fase2.JOGANDO)){
            return;
        }

        if(isPtValidos()) {
//            this.corredor.prepararParaAcao();
//            this.msg.finalizadoFromInput();

            this.faseManager.setFaseAtual(Fase2.ACAO);
        }
        else{
            this.faseManager.setFaseAtual(Fase2.ACEITAR_ENTRADA);
            this.corredor.resetAngulo();
        }
    }

    private void determinarPontosMaximos(Vector2 p1, Vector2 p2){
        if(p1.y < p2.y){
            this.ptSuperior.set(p1, 0);
            this.ptLateral.set(p2, 0);
        }
        else{
            this.ptSuperior.set(p2, 0);
            this.ptLateral.set(p1, 0);
        }
    }

    private boolean isPtValidos(){
        this.ptSuperiorProjetado.set(this.ptSuperior);
        this.cameraProjecao.unproject(this.ptSuperiorProjetado);

        return this.ptSuperiorProjetado.y > this.corredor.getPosicaoJogo().y + 1;
    }

    @Override
    public Vector3 getPtSuperior() {
        this.copiaPtSuperior.set(this.ptSuperior);
        this.cameraProjecao.unproject(this.copiaPtSuperior);
        return this.copiaPtSuperior;
    }

    @Override
    public Vector3 getPtLateral() {
        this.copiaPtLateral.set(this.ptLateral);
        this.cameraProjecao.unproject(this.copiaPtLateral);
        return this.copiaPtLateral;
    }

    @Override
    public Vector3 getPosicaoFinal() {
        throw new UnsupportedOperationException("Posicao final não pode ser chamada na entrada de Pinça");
    }
}
