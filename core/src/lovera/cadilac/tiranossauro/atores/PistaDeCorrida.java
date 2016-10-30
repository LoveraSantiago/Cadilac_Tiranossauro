package lovera.cadilac.tiranossauro.atores;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.contratos.Contivel;
import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;
import lovera.cadilac.tiranossauro.utils.OrthogonalTiledMapRendererFixed;

public final class PistaDeCorrida implements Desenhavel, Contivel{

    private final OrthographicCamera cameraJogo;

    private final TiledMap map;
    private final TiledMapRenderer renderer;
    private final Polyline contornoPista;

    public PistaDeCorrida(OrthographicCamera camerajogo) {
        this.cameraJogo = camerajogo;

        this.map = new TmxMapLoader().load("maps/mapacorrida.tmx");
        this.renderer = new OrthogonalTiledMapRendererFixed(this.map, 1f / AjustadorDeTela.ESCALA);
        this.contornoPista = ((PolylineMapObject) map.getLayers().get("colisao").getObjects().get("contorno")).getPolyline();
    }

    private boolean isDentroDaPista(Vector2 posicao){
        return this.contornoPista.contains(posicao);
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        this.renderer.setView(this.cameraJogo);
        this.renderer.render();
    }

    @Override
    public boolean isContido(Vector2 posicao) {
        return isDentroDaPista(posicao);
    }
}
