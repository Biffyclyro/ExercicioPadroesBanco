import java.io.*;
import java.util.Map;

public class Decorator implements Banco, Closeable {
    private Banco banco;
    private FileWriter fileWriter;

    public Decorator(Banco banco) {
        this.banco = banco;
        try {
            this.fileWriter = new FileWriter("log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createConta(Conta conta) {
        this.banco.createConta(conta);

        final var printWriter = new PrintWriter(fileWriter);
        printWriter.println("Criada conta número: " + conta.getNumero());


    }

    @Override
    public void excluirConta(int numero) {
        this.banco.excluirConta(numero);
    }

    @Override
    public void render() {
        this.render();
    }

    @Override
    public void depositar(int conta, double valor) {
        this.banco.depositar(conta, valor);

        final var printWriter = new PrintWriter(fileWriter);
        printWriter.println("Depositado: R$" + valor + " na conta: " + conta);

    }

    @Override
    public void sacar(int conta, double valor) {
        this.banco.sacar(conta, valor);

        final var printWriter = new PrintWriter(fileWriter);
        printWriter.println("Sacou: R$" + valor + " da conta: " + conta);
    }

    @Override
    public void transferir(int contaOrigem, int contaFim, double valor) {
        this.banco.transferir(contaOrigem, contaFim, valor);

        final var printWriter = new PrintWriter(fileWriter);
        printWriter.println("Transferiu: R$" + valor + " da conta: " + contaOrigem + " para a conta: " + contaFim);
    }

    @Override
    public void tirarEXtrato(int numero) {
        this.banco.tirarEXtrato(numero);

        final var printWriter = new PrintWriter(fileWriter);
        printWriter.println("Tirou extrato da conta: " + numero);
    }

    @Override
    public Map<Integer, Conta> getContas() {
        return this.banco.getContas();
    }


    @Override
    public void close() throws IOException {
        this.fileWriter.close();
    }
}
