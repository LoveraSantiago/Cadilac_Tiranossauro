package lovera.cadilac.tiranossauro.atores.graficos.utils.entradas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro.controladores.Fase;

public final class Pinca_Entrada extends Entrada {

    private final Vector3 ptSuperior;
    private final Vector3 ptLateral;
    private final Vector3 copiaPtSuperior;
    private final Vector3 copiaPtLateral;
    private final Vector3 ptSuperiorProjetado;

    private final Corredor corredor;

    private MensagemGraficos msg;

    public Pinca_Entrada(Corredor corredor) {
        this.corredor = corredor;

        this.ptSuperior          = new Vector3();
        this.ptLateral           = new Vector3();
        this.copiaPtLateral      = new Vector3();
        this.copiaPtSuperior     = new Vector3();
        this.ptSuperiorProjetado = new Vector3();
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Debugagem.dbgPan(x, y, deltaX, deltaY);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if(this.msg.getFaseFromFaseManager() != Fase.JOGANDO &&
                this.msg.getFaseFromFaseManager() != Fase.ACEITAR_ENTRADA) return true;

        determinarPontosMaximos(pointer1, pointer2);

        if(isPtValidos()){
            this.msg.setFaseToFaseManager(Fase.JOGANDO);
        }
        else{
            this.msg.setFaseToFaseManager(Fase.ACEITAR_ENTRADA);
        }
        return false;
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

    @Override
    public void pinchStop() {
        if(this.msg.getFaseFromFaseManager() != Fase.JOGANDO) return;

        if(isPtValidos()) {
            this.msg.finalizadoFromInput();
            this.msg.setFaseToFaseManager(Fase.ACAO);
        }
         else{
            this.msg.setFaseToFaseManager(Fase.ACEITAR_ENTRADA);
        }
    }

    private boolean isPtValidos(){
        this.ptSuperiorProjetado.set(this.ptSuperior);
        this.corredor.getCameraManipulador().getCameraProjecao().unproject(this.ptSuperiorProjetado);

        return this.ptSuperiorProjetado.y > this.corredor.getPosicaoProjetada().y + 1;
    }

    @Override
    public Vector3 getPtSuperior() {
        this.copiaPtSuperior.set(this.ptSuperior);
        this.corredor.getCameraManipulador().getCameraProjecao().unproject(this.copiaPtSuperior);
        return this.copiaPtSuperior;
    }

    @Override
    public Vector3 getPtLateral() {
        this.copiaPtLateral.set(this.ptLateral);
        this.corredor.getCameraManipulador().getCameraProjecao().unproject(this.copiaPtLateral);
        return this.copiaPtLateral;
    }

    @Override
    public Vector3 getPosicaoFinal() {
        throw new UnsupportedOperationException("Posicao final não pode ser chamada na entrada de Pinça");
    }

    @Override
    public void setMensageiro(MensagemGraficos msg) {
        this.msg = msg;
    }
}
