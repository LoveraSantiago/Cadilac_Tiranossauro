package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

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
        this.ptToque.set(x, y);
        unProjetarPontos();

        if(isPtValidos()){
            super.setJogadaValida(true);
        }
        else{
            super.setJogadaValida(false);
            corredor.movimentacaoEncerrada();
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if(isPtValidos()){
            corredor.prepararParaAcao(InformacaoUnico.getInstancia().getInformacaoManager());
            super.setJogadaValida(true);
            faseManager.setFaseAtual(Fase2.ACAO);
            cameraManager.setDiferenca();
        }
        else{
            super.setJogadaValida(false);
            corredor.movimentacaoEncerrada();
        }
        return true;
    }

    private void unProjetarPontos(){
        this.ptToqueProjetado.set(this.ptToque, 0);
        cameraProjecao.unproject(this.ptToqueProjetado);
    }

    private boolean isPtValidos(){
        return this.ptToqueProjetado.y < this.corredor.getPosicaoJogo().y &&
               Math.abs(this.corredor.getPosicaoJogo().x - this.ptToqueProjetado.x) > 1;
    }

    @Override
    public Vector2 getPtToque() {
        this.ptToqueCopia.set(this.ptToqueProjetado.x, this.ptToqueProjetado.y);
        return this.ptToqueCopia;
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
