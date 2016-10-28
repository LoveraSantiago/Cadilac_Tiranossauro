package lovera.cadilac.tiranossauro.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Classe extendendo OrthogonalTiledMapRenderer para sobreescrever metodo setView com o fix para rotação da tela.
 * Remover a presente classe com a proxima versão da Libgdx.
 */
public class OrthogonalTiledMapRendererFixed extends OrthogonalTiledMapRenderer {

    public OrthogonalTiledMapRendererFixed(TiledMap map, float unitScale) {
        super(map, unitScale);
    }

    @Override
    public void setView(OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);
        float width = camera.viewportWidth * camera.zoom;
        float height = camera.viewportHeight * camera.zoom;
        float w = width * Math.abs(camera.up.y) + height * Math.abs(camera.up.x);
        float h = height * Math.abs(camera.up.y) + width * Math.abs(camera.up.x);
        viewBounds.set(camera.position.x - w / 2, camera.position.y - h / 2, w, h);
    }
}
