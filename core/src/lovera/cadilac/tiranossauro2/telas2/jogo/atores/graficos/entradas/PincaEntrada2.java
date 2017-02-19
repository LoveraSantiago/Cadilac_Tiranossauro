package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

public class PincaEntrada2 extends Entrada2 {

    private final Vector3 ptSuperior;
    private final Vector3 ptLateral;

    protected final Vector3 ptSuperiorProjetado;
    private final Vector3 ptLateralProjetado;

    private final Vector2 copiaPtSuperior;
    private final Vector2 copiaPtLateral;

    public PincaEntrada2() {
        super();
        this.ptSuperior          = new Vector3();
        this.ptLateral           = new Vector3();

        this.ptSuperiorProjetado = new Vector3();
        this.ptLateralProjetado = new Vector3();

        this.copiaPtSuperior     = new Vector2();
        this.copiaPtLateral      = new Vector2();
    }

//    @Override
//    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
//        if(!faseManager.isFaseAtual(Fase2.JOGANDO) && !faseManager.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
//            return true;
//        }
//
//        determinarPontosMaximos(pointer1, pointer2);
//        unProjetarPontos();
//
//        if(isPtValidos()){
//            faseManager.setFaseAtual(Fase2.JOGANDO);
//        }
//        else{
//            faseManager.setFaseAtual(Fase2.ACEITAR_ENTRADA);
//            corredor.movimentacaoEncerrada();
//        }
//        return false;
//    }

    //NOVA TENTATIVA
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        determinarPontosMaximos(pointer1, pointer2);
        unProjetarPontos();

        if(isPtValidos()){
            super.setJogadaValida(true);
        }
        else{
            super.setJogadaValida(false);
            corredor.movimentacaoEncerrada();
        }
        return false;
    }

//    @Override
//    public void pinchStop() {
//        if(!faseManager.isFaseAtual(Fase2.JOGANDO)){
//            return;
//        }
//
//        if(isPtValidos()) {
//            corredor.prepararParaAcao(InformacaoUnico.getInstancia().getInformacaoManager());
//            faseManager.setFaseAtual(Fase2.ACAO);
//            cameraManager.setDiferenca();
//        }
//        else{
//            faseManager.setFaseAtual(Fase2.ACEITAR_ENTRADA);
//            corredor.movimentacaoEncerrada();
//        }
//    }

    //NOVA TENTATIVA
    @Override
    public void pinchStop() {
        if(isPtValidos()) {
            corredor.prepararParaAcao(InformacaoUnico.getInstancia().getInformacaoManager());
            super.setJogadaValida(true);
            faseManager.setFaseAtual(Fase2.ACAO);
            cameraManager.setDiferenca();
        }
        else{
            super.setJogadaValida(false);
            corredor.movimentacaoEncerrada();
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

        this.ptLateralProjetado.set(this.ptLateral);
        cameraProjecao.unproject(this.ptLateralProjetado);
    }

    protected boolean isPtValidos(){
        return this.ptSuperiorProjetado.y > corredor.getPosicaoJogo().y + 1;
    }

    @Override
    public Vector2 getPtSuperior() {
        this.copiaPtSuperior.set(this.ptSuperiorProjetado.x, this.ptSuperiorProjetado.y);
        return this.copiaPtSuperior;
    }

    @Override
    public Vector2 getPtLateral() {
        this.copiaPtLateral.set(this.ptLateralProjetado.x, this.ptLateralProjetado.y);
        return this.copiaPtLateral;
    }

    @Override
    public Vector2 getPtToque() {
        throw new UnsupportedOperationException("Ponto toque não pode ser chamada na entrada de Pinça");
    }
}
