package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

import java.awt.Point;
import java.awt.geom.Point2D;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida.PistaDeCorrida2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;

final class MouseJoint_Corredor implements TipoAtualizavel{

    private final MouseJointDef def;
    private MouseJoint mouseJoint;

    private final World worldTemp;

    private final Body corredor;

    public MouseJoint_Corredor(Body corredor) {
        this.worldTemp = MeuBox2D2.getInstancia().getWorld();
        this.corredor = corredor;

        this.def = new MouseJointDef();
        configurarMouseJointDef(corredor);
    }

    private void configurarMouseJointDef(Body corredor){
        def.bodyA = PistaDeCorrida2.getInstancia().getPista();
        def.bodyB = corredor;
        def.collideConnected = true;
        def.target.set(corredor.getPosition());
        def.maxForce = 1000.0f * corredor.getMass();
    }

    @Override
    public void atualizar() {
    }

    public void criarMouseJoint(){
        this.mouseJoint = (MouseJoint) this.worldTemp.createJoint(this.def);
        this.corredor.setAwake(true);
    }

    public void destruirMouseJoint(){
        this.worldTemp.destroyJoint(this.mouseJoint);
        this.corredor.setLinearVelocity(0,0);
    }
}
