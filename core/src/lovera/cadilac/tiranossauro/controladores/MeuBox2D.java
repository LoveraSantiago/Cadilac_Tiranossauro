package lovera.cadilac.tiranossauro.controladores;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import lovera.cadilac.tiranossauro.contratos.Desenhavel;

public class MeuBox2D implements Desenhavel{

    private final World world;
    private final Box2DDebugRenderer renderer;

    private final OrthographicCamera camera;

    public MeuBox2D(OrthographicCamera camera) {
        this.world = new World(new Vector2(), true);
        this.renderer = new Box2DDebugRenderer();

        this.camera = camera;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        this.renderer.render(this.world, this.camera.combined);
    }
}
