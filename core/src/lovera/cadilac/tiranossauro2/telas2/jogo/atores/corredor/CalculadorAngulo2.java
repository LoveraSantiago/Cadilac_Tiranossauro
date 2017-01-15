package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.helpers.HelperUmaAcao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromColisao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromMovimentador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.NormatizadorDeAngulos;

final class CalculadorAngulo2 implements MsgFromMovimentador, MsgFromColisao {

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

    private HelperUmaAcao rotacaoMovimentoAtual;
    private final HelperUmaAcao rotacaoComColisao;
    private final HelperUmaAcao rotacaoSemColisao;

    public CalculadorAngulo2(Body corredor) {
        this.corredor = corredor;

        this.anguloNorte = 90;
        resetAngulo();

        this.normatizador = new NormatizadorDeAngulos();
        this.contadorAngulo = this.corredor.getAngle();

        this.rotacaoComColisao = new RotacaoComColisao();
        this.rotacaoSemColisao = new RotacaoSemColisao();
        this.rotacaoMovimentoAtual = this.rotacaoSemColisao;
    }

    public void rotacionarEmMovimento(){
        this.rotacaoMovimentoAtual.realizarAcao();
    }

    @Override
    public void colisaoAconteceu() {
        this.rotacaoMovimentoAtual = this.rotacaoComColisao;
    }

    @Override
    public void setPtFuturoProj(float x, float y) {
        throw new UnsupportedOperationException("CalculadorAngulo nao seta ponto futuro");
    }

    @Override
    public void movimentacaoEncerrada() {
        this.rotacaoMovimentoAtual = this.rotacaoSemColisao;
    }

    //TENTATIVA USANDO SET TRANSFORM
//    public void rotacionarParado() {
////        printagemDbg("ROTACIONAR PARADO INICIO");
//        normatizarComponentes();
//        if(isMesmoAngulo()){
//            this.corredor.setTransform(this.corredor.getPosition(),this.anguloCalculado * MathUtils.degreesToRadians);
//            return;
//        }
//
//        if((this.anguloCalculado - getAnguloCorredor_Graus() + 360) % 360 < 180){
//            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo += MathUtils.degreesToRadians);
//        }
//        else{
//            this.corredor.setTransform(this.corredor.getPosition(), this.contadorAngulo -= MathUtils.degreesToRadians);
//        }
////        printagemDbg("ROTACIONAR PARADO FIM");
//    }

    //TENTATIVA USANDO applyAngularImpulse
    public void rotacionarParado() {
//        printagemDbg("ROTACIONAR PARADO INICIO");
        normatizarComponentes();
        if(isMesmoAngulo()){
            this.corredor.applyAngularImpulse(0, true);
        }
        else{
            if((this.anguloCalculado - getAnguloCorredor_Graus() + 360) % 360 < 180){
                if(this.corredor.getAngularVelocity() <= 0){
                    this.corredor.applyAngularImpulse(10f, true);
                }
            }
            else{
                if(this.corredor.getAngularVelocity() >= 0){
                    this.corredor.applyAngularImpulse(-10f, true);
                }
            }
        }

        printagemDbg("ROTACIONAR PARADO FIM");
    }

//    //TENTATIVA USANDO TORQUE
//    public void rotacionarParado() {
//        normatizarComponentes();
//        if(isMesmoAngulo()){
//            setFps();
//            proxAngulo = corredor.getAngle() + corredor.getAngularVelocity() / fps;
//            diferencaAngulo = anguloCalculado * MathUtils.degreesToRadians - proxAngulo;
//            velocidadeAngularEsperada = diferencaAngulo * fps;
//            torque = corredor.getInertia() * velocidadeAngularEsperada / (1 / fps);
//            corredor.applyTorque(torque, true);
//        }
//        else{
//            if((this.anguloCalculado - getAnguloCorredor_Graus() + 360) % 360 < 180){
//                this.contadorAngulo += MathUtils.degreesToRadians;
//            }
//            else{
//                this.contadorAngulo -= MathUtils.degreesToRadians;
//            }
//            setFps();
//            proxAngulo = corredor.getAngle() + corredor.getAngularVelocity() / fps;
//            diferencaAngulo = this.contadorAngulo * MathUtils.degreesToRadians - proxAngulo;
//            velocidadeAngularEsperada = diferencaAngulo * fps;
//            torque = corredor.getInertia() * velocidadeAngularEsperada / (1 / fps);
//            corredor.applyTorque(torque, true);
//        }
//    }

    public void calcularAngulo(float ptFuturoX, float ptFuturoY){
//        printagemDbg("CALCULAR ANGULO INICIO");
        this.anguloCalculado = (float) (Math.atan2(ptFuturoY - this.corredor.getPosition().y, ptFuturoX - this.corredor.getPosition().x)) * MathUtils.radiansToDegrees;
        this.anguloCalculado = Math.round(this.anguloCalculado);

        this.contadorAngulo = this.corredor.getAngle();
//        printagemDbg("CALCULAR ANGULO FIM");
    }

    public void telaAngulada(float angulo) {
//        printagemDbg("TELA ANGULADA INICIO");
        this.anguloNorte += angulo;
        this.anguloNorte = this.normatizador.normatizar(this.anguloNorte);
        resetAngulo();
//        printagemDbg("TELA ANGULADA FIM");
        normatizarComponentes();
    }

    public void normatizarComponentes(){
        this.corredor.setTransform(this.corredor.getPosition(), this.normatizador.normatizar(getAnguloCorredor_Graus()) * MathUtils.degreesToRadians);
        this.anguloCalculado = this.normatizador.normatizar(this.anguloCalculado);
//        printagemDbg("COMPONENTES NORMATIZADOS");
    }

    public void resetAngulo() {
        this.anguloCalculado = this.anguloNorte;
    }

    private int getAnguloCorredor_Graus(){
        return Math.round(this.corredor.getAngle() * MathUtils.radiansToDegrees);
    }

    private boolean isMesmoAngulo(){
        this.anguloCorredorGraus = getAnguloCorredor_Graus();
        return this.normatizador.normatizar(Math.round(this.anguloCalculado)) == this.normatizador.normatizar(this.anguloCorredorGraus);
    }

    private void setFps(){
        this.fps = Gdx.graphics.getFramesPerSecond();
        this.fps = this.fps < 60 ? 60 : this.fps;
    }

    //TODO: Apagar qdo tiver seguranca que esta funcionando normalmente
    private void printagemDbg(String texto){
        System.out.println("*****" + texto + "*****");
        System.out.println("angulo norte      : " + this.anguloNorte);
        System.out.println("angulo calculado  : " + this.anguloCalculado);
        System.out.println("contador de angulo: " + (this.contadorAngulo * MathUtils.radiansToDegrees));
        System.out.println("angulo corredor   : " + getAnguloCorredor_Graus());
    }

    class RotacaoSemColisao implements HelperUmaAcao{

        @Override
        public void realizarAcao() {
            setFps();
            proxAngulo = corredor.getAngle() + corredor.getAngularVelocity() / fps;
            diferencaAngulo = anguloCalculado * MathUtils.degreesToRadians - proxAngulo;
            velocidadeAngularEsperada = diferencaAngulo * fps;
            torque = corredor.getInertia() * velocidadeAngularEsperada / (1 / fps);
            corredor.applyTorque(torque, true);

//            System.out.print("-fps " + fps);
//            System.out.print(" -proxAngulo " + proxAngulo);
//            System.out.print(" -difAngulo " + diferencaAngulo);
//            System.out.print(" -velEsp " + velocidadeAngularEsperada);
//            System.out.print(" -torque " + torque);
//            System.out.print(" -inercia " + corredor.getInertia());
//            System.out.print(" -angCalc " + anguloCalculado);
//            System.out.print(" -angCor " + getAnguloCorredor_Graus());
//            System.out.println();
        }
    }

    class RotacaoComColisao implements HelperUmaAcao{

        @Override
        public void realizarAcao() {

        }
    }
}
