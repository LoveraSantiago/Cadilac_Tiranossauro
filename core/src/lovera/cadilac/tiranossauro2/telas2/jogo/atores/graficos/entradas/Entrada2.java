package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

//TODO uniformizar acessos as variaveis de classe pai
public abstract class Entrada2  extends GestureDetector.GestureAdapter implements TipoControlavel {

    protected static final FaseManager2 faseManager;
    protected static final CameraManager cameraManager;
    protected static final OrthographicCamera cameraProjecao;
    protected static final Corredor2 corredor;

    private final GestureDetector gestureDetector;

    static{
        faseManager = FaseManager2.getInstancia();
        cameraManager = CameraUnico.getCameraManager();
        cameraProjecao =  cameraManager.getCamera_CamProj();
        corredor = CorredorManager.getInstancia().getCorredorP();
    }

    protected Entrada2() {
        this.gestureDetector = new GestureDetector(this);
    }

    public abstract Vector2 getPtToque();
    public abstract Vector2 getPtSuperior();
    public abstract Vector2 getPtLateral();

    @Override
    public InputProcessor passarInputProcessor() {
        return this.gestureDetector;
    }
}
