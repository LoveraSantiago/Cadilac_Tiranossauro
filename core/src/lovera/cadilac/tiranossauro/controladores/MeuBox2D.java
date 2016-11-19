package lovera.cadilac.tiranossauro.controladores;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.PistaDeCorrida;
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

    public void setCorredor(Corredor corredor){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(corredor.getWidth() / 2,corredor.getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(corredor.getPosicaoJogX(), corredor.getPosicaoJogY());

        Body body = this.world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void setPistaDeCorrida(PistaDeCorrida pista){
        ChainShape shape = new ChainShape();
        shape.createChain(pista.getContornoPista().getVertices());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = this.world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setTransform(pista.getPontoInicial(), 0);

        shape.dispose();
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        this.renderer.render(this.world, this.camera.combined);
    }
}
