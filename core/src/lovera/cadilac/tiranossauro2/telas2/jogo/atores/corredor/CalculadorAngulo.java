package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;

final class CalculadorAngulo {

    private float anguloCorredorGraus;
    private float anguloCalculado;
    private float contadorAngulo;

    private final Body corredor;

    public CalculadorAngulo(Body corredor) {
        this.corredor = corredor;
        this.anguloCalculado = 90;
        this.contadorAngulo = corredor.getAngle();
    }
//    float nextAngle;
//    float totalRotation;
//    float desiredAngularVelocity;
//    float torque;
//    nextAngle = corredor.getAngle() + corredor.getAngularVelocity() / 60.0f;
//    totalRotation = 15 * MathUtils.degreesToRadians - nextAngle;
//    desiredAngularVelocity = totalRotation * 60.0f;
//    torque = corredor.getInertia() * desiredAngularVelocity / (1/60.0f);
//    corredor.applyTorque(torque, true);

    public void rotacionarParado() {
        this.anguloCorredorGraus = getAnguloCorredor_Graus();
        if(this.anguloCalculado == this.anguloCorredorGraus) return;

        if(this.anguloCorredorGraus > this.anguloCalculado){
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo -= MathUtils.degreesToRadians);
        }
        else{
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo += MathUtils.degreesToRadians);
        }
    }

    public void calcularAngulo(float ptFuturoX, float ptFuturoY){
        this.anguloCalculado = (float) (Math.atan2(ptFuturoY - this.corredor.getPosition().y, ptFuturoX - this.corredor.getPosition().x)) * MathUtils.radiansToDegrees;
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

    public void resetAngulo() {
        this.anguloCalculado = 90;
    }

    private int getAnguloCorredor_Graus(){
        return Math.round(this.corredor.getAngle() * MathUtils.radiansToDegrees);
    }
}
