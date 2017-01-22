package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;

public final class DesenhadorGraf_Logaritmo implements TipoDesenhadorGrafico{

    private final Entrada2 entrada2;

    public DesenhadorGraf_Logaritmo() {
        this.entrada2 = new ArrastarEntrada2();
    }

    @Override
    public void meDesenhar(Object objeto) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public Entrada2 getEntrada() {
        return entrada2;
    }
}
