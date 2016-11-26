package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida.PistaDeCorrida2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

final class SingletonsManager_TelaJogo implements Disposable{

    private CameraManager cameraManagerTemp;
    private MeuBox2D2 meuBox2DTemp;

    public void iniciliazarSingletons(){
        new mSpriteBatch().inicializar();
        new CameraManager().inicializar();

        new FaseManager2().inicializar();

        //BOX2D DEVE SER INICIALIZADO ANTES DE CORREDOR E PISTA
        new MeuBox2D2().inicializar();
        new PistaDeCorrida2().inicializar();
        new CorredorManager().inicializar();
    }

    public void render(float delta){
        this.cameraManagerTemp = CameraManager.getInstance();
        this.meuBox2DTemp = MeuBox2D2.getInstancia();

        this.cameraManagerTemp.atualizar();
        this.meuBox2DTemp.atualizar();

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManagerTemp.updateSpriteBatch_CamJogo();
        PistaDeCorrida2.getInstancia().meDesenhar(null);

        //UPDATE DO SPRITEBATCH COM CAMERA NORMAL
        this.cameraManagerTemp.updateSpriteBatch_CamProj();
        CorredorManager.getInstancia().meDesenhar(null);

        //SENDO CHAMADO POR ULTIMO PARA PODER SER VISUALIZADO POR CIMA DA TELA
        this.meuBox2DTemp.meDesenhar(null);
    }

    @Override
    public void dispose() {
        mSpriteBatch.getInstance().dispose();
        MeuBox2D2.getInstancia().dispose();
    }
}
