package lovera.cadilac.tiranossauro2.telas2.jogo.atores;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro.utils.OrthogonalTiledMapRendererFixed;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoBody2D;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

public class PistaDeCorrida2 implements TipoBody2D, TipoSingleton{

    private static PistaDeCorrida2 pista;

    private final TiledMapRenderer renderer;

    private PistaDeCorrida2() {
        TiledMap map = new TmxMapLoader().load("maps/mapacorrida.tmx");
        this.renderer = new OrthogonalTiledMapRendererFixed(map, 1f / AjustadorDeTela2.ESCALA);

        MapObject mapObject = map.getLayers().get("colisao").getObjects().get("contorno");
        MapProperties properties = mapObject.getProperties();

        meTransformeEmBody(new Vector2(Float.parseFloat(properties.get("x").toString()), Float.parseFloat(properties.get("y").toString())));
    }

    @Override
    public void inicializar() {
        pista = new PistaDeCorrida2();
    }

    public static PistaDeCorrida2 getInstancia() {
        return pista;
    }

    /**
     * @param ptZeroParaBody - obrigatoriamente e unicamente deve ser casteado para Vector2
     */
    @Override
    public void meTransformeEmBody(Object ptZeroParaBody) {

    }
}
