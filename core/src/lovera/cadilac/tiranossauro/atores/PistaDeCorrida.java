package lovera.cadilac.tiranossauro.atores;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;
import lovera.cadilac.tiranossauro.utils.OrthogonalTiledMapRendererFixed;

public final class PistaDeCorrida implements Desenhavel{

    private final OrthographicCamera cameraJogo;

    private final TiledMap map;
    private final TiledMapRenderer renderer;

    public PistaDeCorrida(OrthographicCamera camerajogo) {
        this.cameraJogo = camerajogo;

        this.map = new TmxMapLoader().load("maps/mapacorrida.tmx");
        this.renderer = new OrthogonalTiledMapRendererFixed(this.map, 1f / AjustadorDeTela.ESCALA);
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        this.renderer.setView(this.cameraJogo);
        this.renderer.render();
    }
}
