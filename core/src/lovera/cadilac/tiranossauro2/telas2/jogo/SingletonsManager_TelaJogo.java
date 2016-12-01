package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida.PistaDeCorrida2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.ControleManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MenuManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

final class SingletonsManager_TelaJogo implements Disposable{

    private CameraManager cameraManagerTemp;
    private MeuBox2D2 meuBox2DTemp;
    private CorredorManager corredorManager;
    private PistaDeCorrida2 pistaDeCorrida2;
    private MenuManager2 menuManager2;

    public void iniciliazarSingletons(){
        new mSpriteBatch().inicializar();
        new CameraManager().inicializar();

        new FaseManager2().inicializar();

        new MeuBox2D2().inicializar();

        this.pistaDeCorrida2 = new PistaDeCorrida2();
        this.pistaDeCorrida2.inicializar();

        this.corredorManager = new CorredorManager();
        this.corredorManager.inicializar();

        this.menuManager2 = new MenuManager2();
        this.menuManager2.inicializar();

        new ControleManager2().inicializar();
    }

    public void render(float delta){
        this.cameraManagerTemp = CameraManager.getInstance();
        this.meuBox2DTemp = MeuBox2D2.getInstancia();

        this.cameraManagerTemp.atualizar();
        this.meuBox2DTemp.atualizar();

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManagerTemp.updateSpriteBatch_CamJogo();
        this.pistaDeCorrida2.meDesenhar(null);

        //UPDATE DO SPRITEBATCH COM CAMERA NORMAL
        this.cameraManagerTemp.updateSpriteBatch_CamProj();
        this.corredorManager.meDesenhar(null);

        this.menuManager2.meDesenhar(null);

        //SENDO CHAMADO POR ULTIMO PARA PODER SER VISUALIZADO POR CIMA DA TELA
        this.meuBox2DTemp.meDesenhar(null);
    }

    @Override
    public void dispose() {
        mSpriteBatch.getInstance().dispose();
        MeuBox2D2.getInstancia().dispose();
        this.menuManager2.dispose();
    }
}
