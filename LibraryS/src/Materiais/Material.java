package Materiais;


import java.util.*;

/**
 * Classe abstract para objetos do tipo Material, onde serão contidos, valores e métodos para o mesmo.
 * Classe generica da hieraquia, entre ela, Livros e Multimidia.
 * @author LGDantas
 * @version 1.0
 */
public abstract class Material {
    
     private int nchamada;
     private int usercode;
     private String corestante;
     private int codprateleira;
     private Date data;
     private String titulo;
     private int exemplar;
     private int volume;
     private String local;
     private int anopublicacao;
     private String formadeaquisicao;
     private String observacao;
     private String status;
     
    /**
     * Construtor padrão.
     */
    public Material() {
    }
    
    /**
     * Construtor completo que recebe argumentos para inicializar os campos da classe.
     * @param nchamada Número de chamada.
     * @param usercode Código do usuário relacionado ao BD.
     * @param corestante Cor da estante relacionado ao BD.
     * @param codprateleira Código da prateleira relacionado ao BD.
     * @param data Data de cadastro do material.
     * @param titulo Título.
     * @param exemplar Número de exemplar.
     * @param volume Número do volume.
     * @param local Local de publicação.
     * @param anopublicacao Ano publicação.
     * @param formadeaquisicao Forma de Aquisição.
     * @param observacao Observação sobre material.
     * @param status Status do material.
     */
    public Material(int nchamada, int usercode, String corestante, int codprateleira, Date data, String titulo, int exemplar, int volume, String local, int anopublicacao, String formadeaquisicao, String observacao, String status) {
        this.nchamada = nchamada;
        this.usercode = usercode;
        this.corestante = corestante;
        this.codprateleira = codprateleira;
        this.data = data;
        this.titulo = titulo;
        this.exemplar = exemplar;
        this.volume = volume;
        this.local = local;
        this.anopublicacao = anopublicacao;
        this.formadeaquisicao = formadeaquisicao;
        this.observacao = observacao;
        this.status = status;
    }

    /**
     * Método de acesso à nChamada.
     * @return int - Número de chamada.
     */       
    public int getNchamada() {
        return this.nchamada;
    }
    
    /**
    * Método modificador para nchamada.
    * @param nchamada Número de chamada.
    */
    public void setNchamada(int nchamada) {
        this.nchamada = nchamada;
    }
    
    /**
     * Método de acesso à usercode relacionado no BD. 
     * @return int - Código de usuário.
     */
    public int getUsercode() {
        return usercode;
    }
    
    /**
     * Método modificador de usercode relacionado no BD.
     * @param usercode Código de usuário.
     */
    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }
    
    /**
     * Método de acesso à corestante relacionado no BD.
     * @return String - Cor da estante.
     */
    public String getCorestante() {
        return corestante;
    }

    /**
     * Método modificador de corestante relacionado no BD.
     * @param corestante Cor da estante.
     */
    public void setCorestante(String corestante) {
        this.corestante = corestante;
    }
    
    /**
     * Método de acesso à codprateleira relacionado no BD.
     * @return int - Código da prateleira.
     */
    public int getCodprateleira() {
        return this.codprateleira;
    }
    
    /**
     * Método modificador de codprateleira relacionado no BD. 
     * @param codprateleira Código da prateleira.
     */
    public void setCodprateleira(int codprateleira) {
        this.codprateleira = codprateleira;
    }
        
    /**
     * Método de acesso à data.
     * @return Date - Data de cadastro.
     */
    public Date getData() {
        return this.data;
    }
    
    /**
     * Método modificador de data.
     * @param data Data de cadastro.
     */
    public void setData(Date data) {
        this.data = data;
    }
    
    /**
     * Método de acesso à titulo.
     * @return - String Título do material.
     */
    public String getTitulo() {
        return this.titulo;
    }
    
    /**
     * Método modificador de titulo.
     * @param titulo Título do material.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Método de acesso à exemplar.
     * @return int - Número do exemplar.
     */
    public int getExemplar() {
        return this.exemplar;
    }
    
    /**
     * Método modificador de exemplar.
     * @param exemplar Número de exemplar.
     */
    public void setExemplar(int exemplar) {
        this.exemplar = exemplar;
    }
    
    /**
     * Método de acesso à volume.
     * @return int - Número do exemplar.
     */
    public int getVolume() {
        return this.volume;
    }
    
    /**
     * Método modificador de volume.
     * @param volume Número do exemplar.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    /**
     * Método de acesso à local.
     * @return String - Nome do local de publicação.
     */
    public String getLocal() {
        return this.local;
    }
    
    /**
     * Método modificador de local.
     * @param local Nome do local de publicação.
     */
    public void setLocal(String local) {
        this.local = local;
    }
    
    /**
     * Método de acesso à anopublicacao.
     * @return int - Ano de publicação.
     */
    public int getAnopublicacao() {
        return this.anopublicacao;
    }
    
    /**
     * Método modificador de anopublicacao.
     * @param anopublicacao Ano de publicação.
     */
    public void setAnopublicacao(int anopublicacao) {
        this.anopublicacao = anopublicacao;
    }
    
    /**
     * Método de acesso à formadeaquisicao.
     * @return String - Forma de Aquisição.
     */
    public String getFormadeaquisicao() {
        return this.formadeaquisicao;
    }
    
    /**
     * Método modificador de formadeaquisicao.
     * @param formadeaquisicao Forma de Aquisição.
     */
    public void setFormadeaquisicao(String formadeaquisicao) {
        this.formadeaquisicao = formadeaquisicao;
    }
    
    /**
     * Método de acesso à observacao.
     * @return String - Observação sobre material.
     */
    public String getObservacao() {
        return this.observacao;
    }
    
    /**
     * Método modificador de observacao.
     * @param observacao Observação sobre material.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    /**
     * Método de acesso à status.
     * @return String - Status do material.
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * Método modificador de status.
     * @param status Status do material.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Material{" + "nchamada=" + nchamada + ", usercode=" + usercode + ", corestante=" + corestante + ", codprateleira=" + codprateleira + ", data=" + data + ", titulo=" + titulo + ", exemplar=" + exemplar + ", volume=" + volume + ", local=" + local + ", anopublicacao=" + anopublicacao + ", formadeaquisicao=" + formadeaquisicao + ", observacao=" + observacao + ", status=" + status + '}';
    }    
    
    /**
     * Método abstract responsável por cadastrar um objeto Material no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public abstract boolean cadastrarMaterial();
    
    /**
     * Método abstract responsável por atualizar um objeto Material no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public abstract boolean updateMaterial();
    
    /**
     * Método abstract responsável em obter uma lista de objetos do tipo Material do banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de objetos do tipo Material, caso contrário retorna null.
     */
    public abstract List listarMaterial();
    
    /**
     * Método abstract responsável por deletar um objeto Material no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public abstract boolean deleteMaterial();
    
    /**
     * Método abstract responsável em obter uma lista de objetos do tipo Livro no banco de dados, 
     * filtrados conforme campos digitados e adicionados ao objeto enviado como parâmetro.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de objetos do tipo Material, caso contrário retorna null.
     */
    public abstract List filtrarMaterialCMP();
    
    /**
     * Método abstract responsável em obter um objeto do tipo Material no banco de dados,
     * sendo este com nchamada igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @return Material - Caso a operação for realizada com sucesso retorna um objeto Material, caso contrário retorna null.
     */
    public abstract Material buscarMaterialNC();    
}
