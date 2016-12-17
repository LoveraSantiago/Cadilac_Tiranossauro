package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

public abstract class Entrada2  extends GestureDetector.GestureAdapter implements TipoControlavel {

    protected static final FaseManager2 faseManager;
    protected static final OrthographicCamera cameraProjecao;
    protected static final Corredor2 corredor;

    private final GestureDetector gestureDetector;

    static{
        faseManager = FaseManager2.getInstancia();
        cameraProjecao =  CameraUnico.getCameraManager().getCameraProjecao();
        corredor = CorredorManager.getInstancia().getCorredorP();
    }

    protected Entrada2() {
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
