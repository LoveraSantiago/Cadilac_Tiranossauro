package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.utils.Debugagem;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;

public final class ArrastarEntrada2 extends Entrada2 {

    private final Vector2 ptToque;
    private final Vector2 ptToqueCopia;

    private final Vector3 ptToqueProjetado;

    public ArrastarEntrada2() {
        super();

        this.ptToque = new Vector2();
        this.ptToqueCopia = new Vector2();
        this.ptToqueProjetado = new Vector3();
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(!faseManager.isFaseAtual(Fase2.JOGANDO) && !faseManager.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            return true;
        }

        this.ptToque.set(x, y);
        unProjetarPontos();

        if(isPtValidos()){
            faseManager.setFaseAtual(Fase2.JOGANDO);
        }
        else{
            faseManager.setFaseAtual(Fase2.ACEITAR_ENTRADA);
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if(!faseManager.isFaseAtual(Fase2.JOGANDO)){
            return true;
        }

        if(isPtValidos()){
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
        return true;
    }

    private void unProjetarPontos(){
        this.ptToqueProjetado.set(this.ptToque, 0);
        cameraProjecao.unproject(this.ptToqueProjetado);
    }

    private boolean isPtValidos(){
        return this.ptToqueProjetado.y < this.corredor.getPosicaoJogo().y;
    }

    @Override
    public Vector2 getPtToque() {
//        this.ptToqueCopia.set()
        return null;
    }

    @Override
    public Vector2 getPtSuperior() {
        throw new UnsupportedOperationException("Ponto Superior não pode ser chamada na entrada de Pinça");
    }

    @Override
    public Vector2 getPtLateral() {
        throw new UnsupportedOperationException("Ponto Lateral não pode ser chamada na entrada de Pinça");
    }
}
