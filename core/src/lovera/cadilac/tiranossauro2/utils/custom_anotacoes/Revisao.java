package lovera.cadilac.tiranossauro2.utils.custom_anotacoes;

public @interface Revisao {

    String dataModificacao();
    String dataRevisao();

    boolean classePublica();
    String justificativa_classePublica();//Justificar casos positivos

    boolean classeFinal();
    String justificativa_classeFinal();//Justificar casos negativos

    String[] todos();

    //TODO metodos publicos desnecessarios Encapsulamento
}
