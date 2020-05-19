import java.util.Map;

public interface Banco {


    void createConta(Conta conta);

    void excluirConta(int numero);

    void render();

    void depositar(int conta, double valor);

    void sacar(int conta, double valor);

    void transferir(int contaOrigem, int contaFim, double valor);

    void tirarEXtrato(int numero);

    Map<Integer, Conta> getContas();

}
