package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;

public final class PincaEntrada2 extends Entrada2 {

    private final Vector3 ptSuperior;
    private final Vector3 ptLateral;
    private final Vector3 ptSuperiorProjetado;
    private final Vector3 copiaPtSuperior;
    private final Vector3 copiaPtLateral;

    public PincaEntrada2() {
        super();
        this.ptSuperior          = new Vector3();
        this.ptLateral           = new Vector3();
        this.ptSuperiorProjetado = new Vector3();
        this.copiaPtSuperior     = new Vector3();
        this.copiaPtLateral      = new Vector3();
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if(!faseManager.isFaseAtual(Fase2.JOGANDO) && !faseManager.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            return true;
        }

        determinarPontosMaximos(pointer1, pointer2);
        unProjetarPontos();

        if(isPtValidos()){
            faseManager.setFaseAtual(Fase2.JOGANDO);
        }
        else{
            faseManager.setFaseAtual(Fase2.ACEITAR_ENTRADA);
            corredor.resetAngulo();
        }
        return false;
    }

    @Override
    public void pinchStop() {
        if(!faseManager.isFaseAtual(Fase2.JOGANDO)){
            return;
        }

        if(isPtValidos()) {
            System.out.println("*****Pinca finalizado*****");
            Debugagem.dbgPontoVector3("ptLateral:", this.getPtLateral());
            Debugagem.dbgPontoVector3("ptSuperior:", this.getPtSuperior());
            System.out.println("**************************");
            corredor.prepararParaAcao(InformacaoManager.getInstancia().getInformacao());
            faseManager.setFaseAtual(Fase2.ACAO);
        }
        else{
            faseManager.setFaseAtual(Fase2.ACEITAR_ENTRADA);
            corredor.resetAngulo();
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

    private void unProjetarPontos(){
        this.ptSuperiorProjetado.set(this.ptSuperior);
        cameraProjecao.unproject(this.ptSuperiorProjetado);
    }

    private boolean isPtValidos(){
        return this.ptSuperiorProjetado.y > corredor.getPosicaoJogo().y + 1;
    }

    @Override
    public Vector3 getPtSuperior() {
        this.copiaPtSuperior.set(this.ptSuperior);
        cameraProjecao.unproject(this.copiaPtSuperior);
        return this.copiaPtSuperior;
    }

    @Override
    public Vector3 getPtLateral() {
        this.copiaPtLateral.set(this.ptLateral);
        cameraProjecao.unproject(this.copiaPtLateral);
        return this.copiaPtLateral;
    }

    @Override
    public Vector3 getPosicaoFinal() {
        throw new UnsupportedOperationException("Posicao final não pode ser chamada na entrada de Pinça");
    }
}
