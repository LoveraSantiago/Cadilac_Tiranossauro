package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.NormatizadorDeAngulos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.lado.DirecaoEnum;

final class CalculadorAngulo {

    //VARIAVEIS UTILIZADAS PARA ESTADO PARADO
    private float anguloCorredorGraus;
    private float anguloCalculado;
    private float contadorAngulo;

    //VARIAVEIS UTILIZADAS PARA ESTADO PARADO
    private float anguloNorte;
    private float fps;
    private float proxAngulo;
    private float diferencaAngulo;
    private float velocidadeAngularEsperada;
    private float torque;

    private final Body corredor;

    private final NormatizadorDeAngulos normatizador;

    public CalculadorAngulo(Body corredor) {
        this.corredor = corredor;

        this.anguloNorte = 90;
        resetAngulo();

        this.normatizador = new NormatizadorDeAngulos();
    }

    public void rotacionarEmMovimento(){
        setFps();
        this.proxAngulo = this.corredor.getAngle() + this.corredor.getAngularVelocity() / this.fps;
        this.diferencaAngulo = this.anguloCalculado * MathUtils.degreesToRadians - this.proxAngulo;
        this.velocidadeAngularEsperada = this.diferencaAngulo * this.fps;
        this.torque = this.corredor.getInertia() * this.velocidadeAngularEsperada / (1 / this.fps);
        this.corredor.applyTorque(this.torque, true);
    }

    public void rotacionarParado() {
        if(isMesmoAngulo()){
            this.corredor.setTransform(this.corredor.getPosition(),this.anguloCalculado * MathUtils.degreesToRadians);
            return;
        }

        if(this.anguloCorredorGraus > this.anguloCalculado){
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo -= MathUtils.degreesToRadians);
            System.out.println("Incremento NEG contador angulo " + this.contadorAngulo * MathUtils.radiansToDegrees);
        }
        else{
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo += MathUtils.degreesToRadians);
            System.out.println("Incremento POS contador angulo " + this.contadorAngulo * MathUtils.radiansToDegrees);
        }
    }

    public void calcularAngulo(float ptFuturoX, float ptFuturoY){
        this.anguloCalculado = (float) (Math.atan2(ptFuturoY - this.corredor.getPosition().y, ptFuturoX - this.corredor.getPosition().x)) * MathUtils.radiansToDegrees;
        this.anguloCalculado = Math.round(this.anguloCalculado);
        this.anguloCalculado = this.normatizador.normatizar(this.anguloCalculado);

        this.contadorAngulo = this.corredor.getAngle();

        System.out.println("*****");
        System.out.println("angulo calculado " + this.anguloCalculado);
        System.out.println("contador angulo  " + this.contadorAngulo * MathUtils.radiansToDegrees);
    }

    public void telaAngulada(float angulo) {
        this.anguloNorte += angulo;
        resetAngulo();
    }

    public void resetAngulo() {
        this.anguloCalculado = this.anguloNorte;
    }

    private int getAnguloCorredor_Graus(){
        return Math.round(this.corredor.getAngle() * MathUtils.radiansToDegrees);
    }

    private boolean isMesmoAngulo(){
        this.anguloCorredorGraus = getAnguloCorredor_Graus();
        return Math.round(this.anguloCalculado) == this.anguloCorredorGraus;
    }

    private void setFps(){
        this.fps = Gdx.graphics.getFramesPerSecond();
        this.fps = this.fps < 60 ? 60 : this.fps;
    }
}
