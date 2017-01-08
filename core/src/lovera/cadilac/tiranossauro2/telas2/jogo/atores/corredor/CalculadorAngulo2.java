package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.NormatizadorDeAngulos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.lado.DirecaoEnum;

final class CalculadorAngulo2 {

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

    public CalculadorAngulo2(Body corredor) {
        this.corredor = corredor;

        this.anguloNorte = 90;
        resetAngulo();

        this.normatizador = new NormatizadorDeAngulos();
        this.contadorAngulo = this.corredor.getAngle();
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
        printagemDbg("ROTACIONAR PARADO INICIO");

        normatizarComponentes();
        if(isMesmoAngulo()){
            this.corredor.setTransform(this.corredor.getPosition(),this.anguloCalculado * MathUtils.degreesToRadians);
            return;
        }

        if((this.anguloCalculado - getAnguloCorredor_Graus() + 360) % 360 < 180){
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo += MathUtils.degreesToRadians);
        }
        else{
            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo -= MathUtils.degreesToRadians);
        }
        printagemDbg("ROTACIONAR PARADO FIM");
    }

    public void calcularAngulo(float ptFuturoX, float ptFuturoY){
        printagemDbg("CALCULAR ANGULO INICIO");

        this.anguloCalculado = (float) (Math.atan2(ptFuturoY - this.corredor.getPosition().y, ptFuturoX - this.corredor.getPosition().x)) * MathUtils.radiansToDegrees;
        this.anguloCalculado = Math.round(this.anguloCalculado);

        this.contadorAngulo = this.corredor.getAngle();

        printagemDbg("CALCULAR ANGULO FIM");
    }

    public void telaAngulada(float angulo) {
        printagemDbg("TELA ANGULADA INICIO");

        this.anguloNorte += angulo;
        this.anguloNorte = this.normatizador.normatizar(this.anguloNorte);
        resetAngulo();

        printagemDbg("TELA ANGULADA FIM");
        normatizarComponentes();
    }

    public void normatizarComponentes(){
        this.corredor.setTransform(this.corredor.getPosition(), this.normatizador.normatizar(getAnguloCorredor_Graus()) * MathUtils.degreesToRadians);
        this.anguloCalculado = this.normatizador.normatizar(this.anguloCalculado);

        printagemDbg("COMPONENTES NORMATIZADOS");
    }

    public void resetAngulo() {
        this.anguloCalculado = this.anguloNorte;
    }

    private int getAnguloCorredor_Graus(){
        return Math.round(this.corredor.getAngle() * MathUtils.radiansToDegrees);
    }

    private boolean isMesmoAngulo(){
        this.anguloCorredorGraus = getAnguloCorredor_Graus();
        return Math.round(this.anguloCalculado) % this.anguloCorredorGraus == 0;

        //PROBLEMA AQUI 0 % qualquer coisa da zero
    }

    private void setFps(){
        this.fps = Gdx.graphics.getFramesPerSecond();
        this.fps = this.fps < 60 ? 60 : this.fps;
    }

    private void printagemDbg(String texto){
        System.out.println("*****" + texto + "*****");
        System.out.println("angulo norte      : " + this.anguloNorte);
        System.out.println("angulo calculado  : " + this.anguloCalculado);
        System.out.println("contador de angulo: " + (this.contadorAngulo * MathUtils.radiansToDegrees));
        System.out.println("angulo corredor   : " + getAnguloCorredor_Graus());
    }
}
