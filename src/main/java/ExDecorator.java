import java.io.IOException;

public class ExDecorator {
    public static void main(String[] args) throws IOException {
        final var factoryBanco = new FactoryBanco();

        var banco = factoryBanco.factoryMethod();

        banco = new Decorator(banco);

        banco.createConta(new Conta(1, true, 1000, Conta.Tipo.CONTA_CORRENTE));
        banco.depositar(1, 100);
        banco.sacar(1,50);

        ((Decorator) banco).close();
    }
}
