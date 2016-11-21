package lovera.cadilac.tiranossauro.controladores;

import com.badlogic.gdx.Gdx;
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
import lovera.cadilac.tiranossauro.utils.Debugagem;

public class MeuBox2D implements Desenhavel{

    private final World world;
    private final Box2DDebugRenderer renderer;

    private final OrthographicCamera camera;

    private Corredor corredor;

    private Body corredorBody;
    private Body pistaBody;

    public MeuBox2D(OrthographicCamera camera, Corredor corredor) {
        this.camera = camera;
        this.corredor = corredor;

        this.world = new World(new Vector2(), true);
        this.renderer = new Box2DDebugRenderer();

        criarCorredorBody();
    }

    private void criarCorredorBody(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(corredor.getWidth() / 2,corredor.getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(corredor.getPosicaoJogX(), corredor.getPosicaoJogY());

        this.corredorBody = this.world.createBody(bodyDef);
        corredorBody.createFixture(fixtureDef);

        shape.dispose();
    }

    public void setPistaDeCorrida(PistaDeCorrida pista){
        ChainShape shape = new ChainShape();
        shape.createChain(pista.getContornoPista().getVertices());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        this.pistaBody = this.world.createBody(bodyDef);
        pistaBody.createFixture(fixtureDef);
        pistaBody.setTransform(pista.getPontoInicial(), 0);

        shape.dispose();
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        this.world.step(Math.min(Gdx.graphics.getDeltaTime(), 0.1f),3,3);
        this.renderer.render(this.world, this.camera.combined);
    }

    public Body getCorredorBody() {
        return corredorBody;
    }

    public World getWorld() {
        return world;
    }

    public Body getPistaBody() {
        return pistaBody;
    }
}
