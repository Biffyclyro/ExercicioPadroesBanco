import java.util.HashMap;
import java.util.Random;

public class Ex5 {
    public static void main(String[] args) {



        final var banco = new Banco("Novo Banco Modernoso", new HashMap<>());
      /*  banco.createConta(1, false, 1000, Conta.Tipo.CONTA_CORRENTE);*/


        Thread testeThread = new Thread(new TesteDeposito(banco));
        Thread testeThread2 = new Thread(new TesteSaque(banco));
        Thread testeThread3 = new Thread(new TesteCriar(banco));
        Thread testeThread4 = new Thread(new TesteDeletar(banco));
        testeThread.start();
        testeThread2.start();
        testeThread3.start();
        testeThread4.start();
    }

    public static class TesteCriar implements Runnable{
        private Banco banco;
        private Random rnd = new Random();

        public TesteCriar(Banco banco) {
            this.banco = banco;
        }

        @Override
        public void run() {
            while (true){
                final var num = rnd.nextInt();
                if ( !this.banco.getContas().containsKey( num ) && this.banco.getContas().size() < 100 ){
                    this.banco.createConta(num, rnd.nextBoolean(),
                            rnd.nextDouble(),
                            Conta.Tipo.values()[rnd.nextInt(4)]);
                }
            }
        }

    }

    public static class TesteDeletar implements Runnable{
        private Banco banco;
        private final Random RND = new Random();
        private Integer[] valores;

        public TesteDeletar(Banco banco) {
            this.banco = banco;

            this.valores = (Integer[]) banco.getContas().keySet().toArray(new Integer[0] );
        }

        @Override
        public void run() {
            while (true){

                if(valores.length > 0){
                    final int conta = (int) valores[RND.nextInt(valores.length )];

                    this.banco.excluirConta(conta);

                }



            }
        }

    }








    public static class TesteDeposito implements Runnable{
        private Banco banco;
        private final Random RND = new Random();

        public TesteDeposito(Banco banco) {
            this.banco = banco;
        }

        @Override
        public void run() {
            while (true){

                final var valores = this.banco.getContas().keySet().toArray();
                if (valores.length > 0) {
                    final int conta = (int) valores[RND.nextInt(valores.length)];

                    if (this.banco.getContas().get(conta).getSaldo() <= 100000) {
                        synchronized (this.banco.getContas().get(conta)) {
                            /* System.out.println(this.banco.getContas().get(1).getSaldo());*/
                            testarDeposito(conta);
                        }
                    }
                }
            }
        }

        public void testarDeposito(int conta){
            try {
                this.banco.depositar(conta, 1000);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }



    }

    public static class TesteSaque implements Runnable{
        private Banco banco;
        private final Random RND = new Random();
        private Integer[] valores;

        public TesteSaque(Banco banco) {
            this.banco = banco;

            this.valores = (Integer[]) banco.getContas().keySet().toArray(new Integer[0] );
        }

        @Override
        public void run() {
            while (true){

                if(valores.length > 0) {
                    final int conta = (int) valores[RND.nextInt(valores.length)];

                    if (this.banco.getContas().get(conta).getSaldo() >= 100) {
                        synchronized (this.banco.getContas().get(conta)) {
                            /*System.out.println(this.banco.getContas().get(1).getLimite());*/
                            testarSaque(conta);
                        }
                    }
                }
            }
        }

        public void testarSaque(int conta){
            try {

                this.banco.sacar(conta, 100);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
