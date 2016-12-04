package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.DirecaoEnum;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.equacoes.EquacaoQuadratica2;

final class Definidor_Helper {

    private DirecaoEnum lado;

    private final Entrada2 entrada;
    private Vector3 entradaPtSuperior;
    private Vector3 entradaPtLateral;

    private final Vector2 posicaoCorredorP;



    public Definidor_Helper(Entrada2 entrada) {
        this.entrada = entrada;
        this.posicaoCorredorP = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
    }

    public DirecaoEnum definirDirecao(){
        this.lado = this.entrada.getPtLateral().x > this.posicaoCorredorP.x ? DirecaoEnum.DIREITA : DirecaoEnum.ESQUERDA;
        return getLado();
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    public void definirEquacaoQuadratica(EquacaoQuadratica2 quadratica){
        this.entradaPtSuperior = this.entrada.getPtSuperior();
        this.entradaPtLateral  = this.entrada.getPtLateral();

        this.entrada.getPtSuperior().x =  entradaPtSuperior.y - this.posicaoCorredorP.y;
        entradaPtSuperior.y = 0;

        if(this.lado == DirecaoEnum.DIREITA){
            entradaPtLateral.y = entradaPtLateral.x - this.posicaoCorredorP.x;
        }
        else{
            entradaPtLateral.y = this.posicaoCorredorP.x - entradaPtLateral.x;
        }
        entradaPtLateral.x = entradaPtSuperior.x / 2;
        quadratica.definirEquacaoQuadratica(entradaPtLateral.x, entradaPtLateral.y, entradaPtSuperior.x, entradaPtSuperior.y);
    }

    public DirecaoEnum getLado() {
        return lado;
    }

    public boolean isLado(DirecaoEnum lado){
        return this.lado == lado;
    }
}
