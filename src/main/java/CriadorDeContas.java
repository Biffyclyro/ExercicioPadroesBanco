import java.util.Map;
import java.util.Random;

public class CriadorDeContas {
    private static final int MAX = 10000;

    public static void criarConta(Map<Integer, Conta> contas) {
        final var inicio = System.nanoTime();

        for (int i = MAX; i > 0; i--) {
            final var aleatorio = new Random();
            final var conta = new Conta(i, aleatorio.nextBoolean(),
                    aleatorio.nextDouble(),
                    Conta.Tipo.values()[aleatorio.nextInt(4)]);

            contas.put(conta.getNumero(), conta);
        }

        final var fim = System.nanoTime();

        System.out.println("Inserção: " + (fim - inicio));

    }

    public static void percorrerContas(Map<Integer, Conta> contas) {

        final var inicio = System.nanoTime();

        contas.forEach((k, v) -> {
        });

        final var fim = System.nanoTime();

        System.out.println("Percorrer: " + (fim - inicio));

    }

    public static void getTodas(Map<Integer, Conta> contas) {

        final var inicio = System.nanoTime();
        for (int i = 0; i < MAX; i++) {
            contas.get(i);
        }

        final var fim = System.nanoTime();

        System.out.println("Get: " + (fim - inicio));
    }
}
