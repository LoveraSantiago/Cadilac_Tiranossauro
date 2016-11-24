package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;

final class SingletonsManager_TelaJogo implements Disposable{

    public void iniciliazarSingletons(){
        TipoSingleton mSpriteBatch = new mSpriteBatch();
        mSpriteBatch.inicializar();

        TipoSingleton faseManager = new FaseManager2();
        faseManager.inicializar();
    }

    @Override
    public void dispose() {
        mSpriteBatch.getInstance().dispose();
    }
}
