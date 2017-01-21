package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.InputProcessor;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;

public final class EntradaGrafico_Vetor extends EntradaGrafica{

    private final Entrada2 entrada;
    private final DesenhadorGraf_Vetor desenhador;

    public EntradaGrafico_Vetor() {
        super();
        this.entrada = new PincaEntrada2();
        this.desenhador = new DesenhadorGraf_Vetor(this.entrada);
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            super.getAreaJogavel2().meDesenhar(null);
        }
        else if(faseManager2.isFaseAtual(Fase2.JOGANDO)){
            super.getAreaJogavel2().meDesenhar(null);
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
