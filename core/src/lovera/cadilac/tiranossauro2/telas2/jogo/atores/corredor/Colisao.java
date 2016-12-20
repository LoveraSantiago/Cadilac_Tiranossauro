package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

final class Colisao implements ContactListener {

    private final Body corredor;

    public Colisao(Body corredor) {
        this.corredor = corredor;
    }

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getBody() == this.corredor || contact.getFixtureB().getBody() == this.corredor){

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
