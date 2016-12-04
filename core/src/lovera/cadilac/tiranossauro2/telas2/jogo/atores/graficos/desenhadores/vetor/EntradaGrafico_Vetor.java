package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.controladores.Fase;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

public final class EntradaGrafico_Vetor extends EntradaGrafica{

    private final Entrada2 entrada;
    private final Vetor_Desenhador desenhador;

    private final FaseManager2 faseManager2;

    private final Vector2 posicaoCorredorP;

    public EntradaGrafico_Vetor() {
        super();
        this.entrada = new PincaEntrada2();
        this.desenhador = new Vetor_Desenhador(this.entrada);

        this.faseManager2 = FaseManager2.getInstancia();
        this.posicaoCorredorP = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(this.faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            super.getAreaJogavel2().setarTamanhoEDesenhar(0, this.posicaoCorredorP.y, AjustadorDeTela2.LARGURA_TELA, AjustadorDeTela2.ALTURA_TELA - this.posicaoCorredorP.y);
        }
        else if(this.faseManager2.isFaseAtual(Fase2.JOGANDO)){
            super.getAreaJogavel2().setarTamanhoEDesenhar(0, this.posicaoCorredorP.y, AjustadorDeTela2.LARGURA_TELA, AjustadorDeTela2.ALTURA_TELA - this.posicaoCorredorP.y);
            this.desenhador.meDesenhar(null);
        }
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.entrada.passarInputProcessor();
    }

    @Override
    public void dispose() {
        this.desenhador.dispose();
        super.dispose();
    }
}
