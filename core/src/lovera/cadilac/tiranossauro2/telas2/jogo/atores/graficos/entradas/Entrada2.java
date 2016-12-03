package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;

public abstract class Entrada2  extends GestureDetector.GestureAdapter implements TipoControlavel {

    protected final GestureDetector gestureDetector;

    public Entrada2() {
        this.gestureDetector = new GestureDetector(this);
    }

    public abstract Vector3 getPosicaoFinal();
    public abstract Vector3 getPtSuperior();
    public abstract Vector3 getPtLateral();

    @Override
    public InputProcessor passarInputProcessor() {
        return this.gestureDetector;
    }
}
