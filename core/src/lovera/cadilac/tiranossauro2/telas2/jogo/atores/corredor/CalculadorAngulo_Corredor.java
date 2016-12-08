package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;

final class CalculadorAngulo_Corredor implements TipoAtualizavel{

    private float anguloCalculado;
    private float contadorAngulo;

    private final Body corredor;

    public CalculadorAngulo_Corredor(Body corredor) {
        this.corredor = corredor;
        this.anguloCalculado = 90;
    }

    @Override
    public void atualizar() {
        System.out.println("angulo calculado " + this.anguloCalculado);
        System.out.println("angulo corredor rad:" + this.corredor.getAngle());
        System.out.println("angulo corredor gra:" + this.corredor.getAngle() * MathUtils.radiansToDegrees);
        System.out.println("*******************************************");
        if(this.anguloCalculado == Math.round(this.corredor.getAngle() * MathUtils.radiansToDegrees)) return;
        if(this.corredor.getAngle() < this.anguloCalculado){
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo += MathUtils.degreesToRadians);
        }
        else{
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo -= MathUtils.degreesToRadians);
        }
    }

    public void calcularAngulo(float ptFuturoX, float ptFuturoY){
        this.anguloCalculado = (float) Math.toDegrees(Math.atan2(ptFuturoY - this.corredor.getPosition().y, ptFuturoX - this.corredor.getPosition().x));
        if(this.anguloCalculado < 0 && this.anguloCalculado > -90){
            this.anguloCalculado += 270;
        }
        else if(this.anguloCalculado < 0 && this.anguloCalculado <= -90){
            this.anguloCalculado += 90;
        }
        this.anguloCalculado = Math.round(this.anguloCalculado);
    }

    public void calcularAngulo(Vector2 ptFuturo){
        calcularAngulo(ptFuturo.x, ptFuturo.y);
    }
}
