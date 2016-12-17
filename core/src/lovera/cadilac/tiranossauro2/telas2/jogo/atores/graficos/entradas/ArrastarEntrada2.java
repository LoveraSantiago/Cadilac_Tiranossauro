package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;

public final class ArrastarEntrada2 extends Entrada2 {

    private final Vector2 ptToque;

    public ArrastarEntrada2() {
        super();

        this.ptToque = new Vector2();
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(!faseManager.isFaseAtual(Fase2.JOGANDO) && !faseManager.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            return true;
        }

        this.ptToque.set(x, y);
        unProjetarPontos();

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return super.panStop(x, y, pointer, button);
    }

    private void unProjetarPontos(){

    }

    @Override
    public Vector3 getPosicaoFinal() {
        return null;
    }

    @Override
    public Vector3 getPtSuperior() {
        return null;
    }

    @Override
    public Vector3 getPtLateral() {
        return null;
    }
}
