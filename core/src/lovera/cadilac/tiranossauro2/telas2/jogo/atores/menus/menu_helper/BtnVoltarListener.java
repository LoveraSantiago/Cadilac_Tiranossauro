package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_helper;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.controle.ControleManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ControleUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;

public final class BtnVoltarListener extends ClickListener{

    @Override
    public void clicked(InputEvent event, float x, float y) {
        FaseManager2.getInstancia().setFaseAtual(Fase2.ESCOLHENDO_GRAFICO);
        ControleUnico.getInstancia().getControleManager2().voltarMenuGrafico();
    }
}
