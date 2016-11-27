package lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro.utils.OrthogonalTiledMapRendererFixed;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

public class PistaDeCorrida2 implements TipoParseavel, TipoDesenhavel, TipoSingleton{

    private static PistaDeCorrida2 pista;

    private TiledMapRenderer renderer;

    @Override
    public void inicializar() {
        pista = this;

        TiledMap map = new TmxMapLoader().load("maps/mapacorrida.tmx");
        this.renderer = new OrthogonalTiledMapRendererFixed(map, 1f / AjustadorDeTela2.ESCALA);

        meTransforme(map);
    }

    public static PistaDeCorrida2 getInstancia() {
        return pista;
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.renderer.setView(CameraManager.getInstance().getCameraJogo());
        this.renderer.render();
    }

    @Override
    public Body meTransforme(Object tiledMap) {
        return new ParserToBody_Pista().meTransforme(tiledMap);
    }
}
