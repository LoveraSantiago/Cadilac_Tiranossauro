package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

public final class EntradaGrafico_Vetor extends EntradaGrafica{

    private final Entrada2 entrada;
    private final DesenhadorGraf_Vetor desenhador;

    private final FaseManager2 faseManager2;

    public EntradaGrafico_Vetor() {
        super();
        this.entrada = new PincaEntrada2();
        this.desenhador = new DesenhadorGraf_Vetor(this.entrada);

        this.faseManager2 = FaseManager2.getInstancia();
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(this.faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            super.getAreaJogavel2().meDesenhar(null);
        }
        else if(this.faseManager2.isFaseAtual(Fase2.JOGANDO)){
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
