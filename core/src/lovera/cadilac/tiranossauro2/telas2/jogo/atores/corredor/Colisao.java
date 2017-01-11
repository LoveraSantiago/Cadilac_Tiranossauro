package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromColisao;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.Box2DUnico;

final class Colisao implements ContactListener {

    private boolean aconteceuColisao;

    private final MsgFromColisao msg;
    private final Body corredor;

    public Colisao(Body corredor, MsgFromColisao msg) {
        this.corredor = corredor;
        this.msg = msg;
        Box2DUnico.getInstancia().getBox2DManager().getWorld().setContactListener(this);

        resetColisao();
    }

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getBody() == this.corredor || contact.getFixtureB().getBody() == this.corredor){
            this.aconteceuColisao = true;
            this.msg.colisaoAconteceu();
        }
    }

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

    public void resetColisao(){
        this.aconteceuColisao = false;
    }

    public boolean isAconteceuColisao() {
        return aconteceuColisao;
    }
}
