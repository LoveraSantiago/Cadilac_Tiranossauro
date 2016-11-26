package lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida;


import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoBody2D;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;

class ParserToBody_Pista implements TipoBody2D{

    @Override
    public Body meTransformeEmBody(Object tiledMap) {
        //INFORMACOES DOS CONTIDOS EM TILED MAP PARA SEREM USADOS NO BOX2D
        MapObject mapObject = ((TiledMap)tiledMap).getLayers().get("colisao").getObjects().get("contorno");
        MapProperties properties = mapObject.getProperties();
        float x = Float.parseFloat(properties.get("x").toString());
        float y = Float.parseFloat(properties.get("y").toString());
        Polyline contornoPista = ((PolylineMapObject) mapObject).getPolyline();

        ChainShape shape = new ChainShape();
        shape.createChain(contornoPista.getVertices());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body pistaBody = MeuBox2D2.getInstancia().getWorld().createBody(bodyDef);
        pistaBody.createFixture(fixtureDef);
        pistaBody.setTransform(x, y, 0);

        shape.dispose();
        return pistaBody;
    }
}
