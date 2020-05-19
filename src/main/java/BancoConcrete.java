import java.util.HashMap;
import java.util.Map;

public class BancoConcrete implements Banco {
    private String nome;
    private Map<Integer, Conta> contas = new HashMap<>();
    private static Banco banco;

    private BancoConcrete() {

    }


    @Override
    public synchronized void createConta(Conta conta) {

        if (!contas.containsKey(conta.getNumero())) {

            this.contas.put(conta.getNumero(), conta);
        }

    }


    @Override
    public synchronized void excluirConta(int numero) {

        if (contas.containsKey(numero)) {
            this.contas.remove(numero);
        }

    }

    @Override
    public void render() {
        for (Conta conta : this.contas.values()) conta.render();
    }

    @Override
    public void depositar(int conta, double valor) {

        if (contas.containsKey(conta) && valor > 0) {
            final var c = this.contas.get(conta);
            synchronized (c) {
                c.depositar(valor);
            }
        } else if (valor <= 0) {
            throw new IllegalArgumentException("Não é possível depositar pois o valor: " + conta + " é menor que zero");
        } else {
            throw new IllegalArgumentException("Não existe conta com o número: " + conta);
        }
    }


    @Override
    public void sacar(int conta, double valor) {

        if (contas.containsKey(conta) && valor <= this.contas.get(conta).getSaldo()
                + this.contas.get(conta).getLimite()) {

            final var c = this.contas.get(conta);
            synchronized (c) {
                c.sacar(valor);
            }
        } else if (valor > this.contas.get(conta).getSaldo() + this.contas.get(conta).getLimite()) {
            throw new IllegalArgumentException("O valor: " + valor + " é maior que o limite");
        } else {
            throw new IllegalArgumentException("Não existe conta com o número: " + conta);
        }

    }

    @Override
    public void transferir(int contaOrigem, int contaFim, double valor) {

        final var de = this.contas.get(contaOrigem);
        final var para = this.contas.get(contaFim);
        de.sacar(valor);
        para.depositar(valor);
    }

    @Override
    public void tirarEXtrato(int numero) {

        final var conta = this.contas.get(numero);

        if (conta != null) {
            conta.tirarExtrato();
        }
    }

    @Override
    public Map<Integer, Conta> getContas() {
        return contas;
    }

    public static Banco getInstanceOf() {
        if (banco == null) {
            banco = new BancoConcrete();
        }

        return banco;
    }
}
