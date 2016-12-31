package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

public final class Box2DManager implements TipoAtualizavel, TipoDesenhavel, Disposable{

    private final World world;
    private final Box2DDebugRenderer renderer;

    private final Matrix4 matrizCameraJogo;

    public Box2DManager() {
        this.world = new World(new Vector2(), true);
        this.renderer = new Box2DDebugRenderer();
        this.matrizCameraJogo = CameraUnico.getCameraManager().getCamera_CamJogo().combined;
    }

    @Override
    public void atualizar() {
        this.world.step(Math.min(Gdx.graphics.getDeltaTime(), 0.1f),3,3);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.renderer.render(this.world, this.matrizCameraJogo);
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void dispose() {
        this.world.dispose();
    }
}
