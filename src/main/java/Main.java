import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final var factoryBanco = new FactoryBanco();

        var banco = factoryBanco.factoryMethod();

        //banco = new Decorator(banco);

        banco.createConta(new Conta(1, true, 1000, Conta.Tipo.CONTA_CORRENTE));
        banco.depositar(1, 100);
        banco.sacar(1,120);

        System.out.println(Serasa.getTamnho());
        banco.tirarEXtrato(1);

        //((Decorator) banco).close();
    }
}
