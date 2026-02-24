package model.exceptions;

public class BusinessException extends RuntimeException {
    /* O serialVersionUID é importante para a serialização de objetos.
       Garante que uma exceção salva em um arquivo ou enviada pela rede
       seja compatível com a classe atual.
    */
    private static final long serialVersionUID = 1L;

    // Construtor que recebe a mensagem e a repassa para a superclasse (RuntimeException)
    public BusinessException(String msg) {
        super(msg);
    }
}