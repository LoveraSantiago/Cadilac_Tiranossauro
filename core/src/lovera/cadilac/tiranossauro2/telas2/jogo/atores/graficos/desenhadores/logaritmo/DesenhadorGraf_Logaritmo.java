package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.logaritmo;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoLogaritmo2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

public final class DesenhadorGraf_Logaritmo implements TipoDesenhadorGrafico{

    private final Entrada2 entrada2;

    private final EquacaoLogaritmo2 eqLog;
    private final InformacaoManager informacao;

    private final Vector2 ptToque;

    public DesenhadorGraf_Logaritmo() {
        this.entrada2 = new ArrastarEntrada2();
        this.informacao = InformacaoUnico.getInstancia().getInformacaoManager();

        this.eqLog = new EquacaoLogaritmo2();

        this.ptToque = new Vector2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
//        definirDirecao();
    }

    private void resetarComponentes(){
        this.informacao.resetarInformacao();

        this.ptToque.set(this.entrada2.getPtToque());
//        this.ptToque.set(15.851f, 1.333f);
    }

    @Override
    public void dispose() {

    }

    @Override
    public Entrada2 getEntrada() {
        return entrada2;
    }
}
