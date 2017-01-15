package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromColisao;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.Box2DUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;

final class Colisao implements ContactListener {

    private final MsgFromColisao msgMovimentador;
    private final MsgFromColisao msgCalcAngulo;

    private final FaseManager2 faseManager2;

    private final Body corredor;

    public Colisao(Body corredor, MsgFromColisao msgMovimentador, MsgFromColisao msgCalcAngulo) {
        this.corredor = corredor;

        this.msgMovimentador = msgMovimentador;
        this.msgCalcAngulo = msgCalcAngulo;

        Box2DUnico.getInstancia().getBox2DManager().getWorld().setContactListener(this);

        this.faseManager2 = FaseUnico.getInstancia().getFaseManager2();
    }

    @Override
    public void beginContact(Contact contact) {
        if(contactTemCorredor(contact) && this.faseManager2.isFaseAtual(Fase2.ACAO)){
            this.msgMovimentador.colisaoAconteceu();
            this.msgCalcAngulo.colisaoAconteceu();
        }
    }

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if(contactTemCorredor(contact) && !this.faseManager2.isFaseAtual(Fase2.ACAO)){
            System.out.println("Colisao acontecendo fora da fase acao");
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        if(contactTemCorredor(contact) && !this.faseManager2.isFaseAtual(Fase2.ACAO)){
            System.out.println("Colisao acontecendo fora da fase acao");
        }
    }

    private boolean contactTemCorredor(Contact contact){
        return contact.getFixtureA().getBody() == this.corredor || contact.getFixtureB().getBody() == this.corredor;
    }

}
