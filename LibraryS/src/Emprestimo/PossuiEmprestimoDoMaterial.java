
package Emprestimo;

/**
 * Classe que representa uma excerção. 
 * É acionada quando se tenta realizar emprestimo de materiais iguais para uma pessoa.
 * @author luand
 */
public class PossuiEmprestimoDoMaterial extends Exception{

    private static final long serialVersionUID = 1L;

    public PossuiEmprestimoDoMaterial() {
    }
    
    public PossuiEmprestimoDoMaterial(String message) {
        super(message);
    }  
}
