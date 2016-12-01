package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

public final class ParserToBody_Corredor implements TipoParseavel {

    @Override
    public Body meTransforme(Object objeto) {
        Lataria lataria = (Lataria) objeto;
        CameraManager cameraManagerTemp = CameraManager.getInstance();
        float xInicial = cameraManagerTemp.getCameraProjecao().viewportWidth  / 2;
        float yInicial = cameraManagerTemp.getCameraProjecao().viewportHeight / 6;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(lataria.getLargura() / 2, lataria.getAltura() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(xInicial, yInicial);

        Body body = MeuBox2D2.getInstancia().getWorld().createBody(bodyDef);
        body.createFixture(fixtureDef);

        shape.dispose();
        return body;
    }
}
