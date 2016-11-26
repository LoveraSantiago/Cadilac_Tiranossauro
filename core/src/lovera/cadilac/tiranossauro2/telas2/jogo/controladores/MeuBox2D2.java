package lovera.cadilac.tiranossauro2.telas2.jogo.controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

public final class MeuBox2D2 implements TipoSingleton, TipoAtualizavel, TipoDesenhavel, Disposable{

    private static MeuBox2D2 meuBox;

    private World world;
    private Box2DDebugRenderer renderer;

    @Override
    public void inicializar() {
        meuBox = this;
        this.world = new World(new Vector2(), true);
        this.renderer = new Box2DDebugRenderer();
    }

    public static MeuBox2D2 getInstancia() {
        return meuBox;
    }

    @Override
    public void atualizar() {
        this.world.step(Math.min(Gdx.graphics.getDeltaTime(), 0.1f),3,3);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.renderer.render(this.world, CameraManager.getInstance().getCameraJogo().combined);
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void dispose() {
        this.world.dispose();
    }
}
