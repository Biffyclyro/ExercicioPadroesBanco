import java.util.HashMap;

public class Ex5 {
    public static void main(String[] args) {


       /* final var banco = new BancoConcrete("Novo Banco Modernoso", new HashMap<>());
        final var banco2 = new BancoConcrete("Novo Banco Mao Meno", new HashMap<>());

        banco.createConta(1, false, 1000, Conta.Tipo.CONTA_CORRENTE);

        Thread testeThread = new Thread(new Teste(banco));
        Thread testeThread2 = new Thread(new Teste(banco));
        Thread testeThread3 = new Thread(new Teste(banco));
        Thread testeThread4 = new Thread(new Teste(banco));

        tetadorThread(testeThread, testeThread2, testeThread3, testeThread4);

        System.out.println(banco.getContas().get(1).getSaldo());

        Thread teste = new Thread(new TesteCriacaoExclusao(banco2));
        Thread teste2 = new Thread(new TesteCriacaoExclusao(banco2));
        Thread teste3 = new Thread(new TesteCriacaoExclusao(banco2));
        Thread teste4 = new Thread(new TesteCriacaoExclusao(banco2));

        tetadorThread(teste, teste2, teste3, teste4);

        System.out.println(banco2.getContas().size());
*/

    }

    private static void tetadorThread(Thread testeThread, Thread testeThread2,
                                      Thread testeThread3, Thread testeThread4) {

        testeThread.start();
        testeThread2.start();
        testeThread3.start();
        testeThread4.start();

        try {
            testeThread.join();
            testeThread2.join();
            testeThread3.join();
            testeThread4.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class TesteCriacaoExclusao implements Runnable {
        private final BancoConcrete bancoConcrete;

        public TesteCriacaoExclusao(BancoConcrete bancoConcrete) {
            this.bancoConcrete = bancoConcrete;
        }

        @Override
        public void run() {
            for (int i = 0; i <= 1000; i++) {

                bancoConcrete.createConta(new Conta(1, false, 1000, Conta.Tipo.CONTA_CORRENTE) );
                bancoConcrete.excluirConta(1);

            }
        }
    }


    public static class Teste implements Runnable {
        private final BancoConcrete bancoConcrete;

        public Teste(BancoConcrete bancoConcrete) {
            this.bancoConcrete = bancoConcrete;
        }

        @Override
        public void run() {
            for (int i = 0; i <= 1000; i++) {

                this.bancoConcrete.getContas().get(1).depositar(1);
                this.bancoConcrete.getContas().get(1).sacar(1);
            }

        }
    }


}
