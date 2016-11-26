package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoBody2D;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;

public class ParserToBody_Corredor implements TipoBody2D{

    @Override
    public Body meTransformeEmBody(Object objeto) {
        Lataria lataria = (Lataria) objeto;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(lataria.getLargura() / 2, lataria.getAltura() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(corredor.getPosicaoJogX(), corredor.getPosicaoJogY());

        Body body = MeuBox2D2.getInstancia().getWorld().createBody(bodyDef);
        body.createFixture(fixtureDef);

        shape.dispose();
        return body;
    }
}
