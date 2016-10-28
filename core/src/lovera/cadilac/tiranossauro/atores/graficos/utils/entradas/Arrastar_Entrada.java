package lovera.cadilac.tiranossauro.atores.graficos.utils.entradas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.controladores.Fase;

public final class Arrastar_Entrada extends Entrada{

    private final Vector2 ptToque;

    private final Vector3 copiaPtToque;
    private final Vector3 ptToqueProjetado;

    private MensagemGraficos msg;

    private final Corredor corredor;

    public Arrastar_Entrada(Corredor corredor) {
        this.corredor = corredor;

        this.ptToque = new Vector2();
        this.copiaPtToque = new Vector3();
        this.ptToqueProjetado = new Vector3();
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(this.msg.getFaseFromFaseManager() != Fase.JOGANDO &&
                this.msg.getFaseFromFaseManager() != Fase.ACEITAR_ENTRADA) return true;

        this.ptToque.set(x, y);
        if(isPtValido()){
            this.msg.setFaseToFaseManager(Fase.JOGANDO);
        }
        else{
            this.msg.setFaseToFaseManager(Fase.ACEITAR_ENTRADA);
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if(this.msg.getFaseFromFaseManager() != Fase.JOGANDO) return true;

        if(isPtValido()){
            this.msg.finalizadoFromInput();
            this.msg.setFaseToFaseManager(Fase.ACAO);
        }
        else{
            this.msg.setFaseToFaseManager(Fase.ACEITAR_ENTRADA);
        }
        return true;
    }

    private boolean isPtValido(){
        this.ptToqueProjetado.set(this.ptToque, 0);
        this.corredor.getCameraManipulador().getCameraProjecao().unproject(this.ptToqueProjetado);
        return this.ptToqueProjetado.y < this.corredor.getPosicaoProjetada().y;
    }

    @Override
    public Vector3 getPosicaoFinal() {
        this.copiaPtToque.set(this.ptToque, 0);
        return this.copiaPtToque;
    }

    @Override
    public Vector3 getPtLateral() {
        throw new UnsupportedOperationException("Posicao final não pode ser chamada na entrada de Arrastar");
    }

    @Override
    public Vector3 getPtSuperior() {
        throw new UnsupportedOperationException("Posicao final não pode ser chamada na entrada de Arrastar");
    }

    @Override
    public void setMensageiro(MensagemGraficos msg) {
        this.msg = msg;
    }
}
