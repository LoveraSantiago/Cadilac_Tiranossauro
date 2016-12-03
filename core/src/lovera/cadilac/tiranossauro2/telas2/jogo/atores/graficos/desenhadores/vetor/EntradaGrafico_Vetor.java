package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.InputProcessor;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;

public final class EntradaGrafico_Vetor extends EntradaGrafica{

    private final Entrada2 entrada;
    private final Vetor_Desenhador desenhador;

    public EntradaGrafico_Vetor() {
        this.entrada = new PincaEntrada2();
        this.desenhador = new Vetor_Desenhador(this.entrada);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.desenhador.meDesenhar(null);
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.entrada.passarInputProcessor();
    }
}
