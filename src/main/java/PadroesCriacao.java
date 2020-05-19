
public class PadroesCriacao {
    public static void main(String[] args) {
        final var factoryBanco = new FactoryBanco();

        final var banco = factoryBanco.factoryMethod();

        banco.createConta(new Conta(1, true, 1000, Conta.Tipo.RENDA_FIXA));
        banco.createConta(banco.getContas().get(1).clone());

        banco.getContas().forEach((Integer k, Conta v) -> {
            System.out.println(v.getNumero());
        });
    }
}
