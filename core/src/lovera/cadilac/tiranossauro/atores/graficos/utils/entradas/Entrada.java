package lovera.cadilac.tiranossauro.atores.graficos.utils.entradas;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.contratos.Controlavel;

public abstract class Entrada extends GestureDetector.GestureAdapter implements Controlavel {

    protected final GestureDetector gestureDetector;

    public Entrada() {
        this.gestureDetector = new GestureDetector(this);
    }

    public abstract void setMensageiro(MensagemGraficos msg);

    public abstract Vector3 getPosicaoFinal();
    public abstract Vector3 getPtSuperior();
    public abstract Vector3 getPtLateral();

    @Override
    public InputProcessor passarInputProcessor() {
        return this.gestureDetector;
    }
}
