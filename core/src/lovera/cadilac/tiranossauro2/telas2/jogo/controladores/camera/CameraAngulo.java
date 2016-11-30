package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

class CameraAngulo {

    private float anguloAtual;

    public void normatizarAngulo(){
        if(this.anguloAtual < 0){
            while(this.anguloAtual < 0){
                this.anguloAtual += 360;
            }
            return;
        }
        else if(this.anguloAtual > 0){
            while(this.anguloAtual > 360){
                this.anguloAtual -= 360;
            }
            return;
        }
    }
}
