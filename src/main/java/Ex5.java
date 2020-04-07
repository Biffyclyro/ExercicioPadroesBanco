import java.util.HashMap;
import java.util.Random;

public class Ex5 {
    public static void main(String[] args) {



        final var banco = new Banco("Novo Banco Modernoso", new HashMap<>());
        final var banco2 = new Banco("Novo Banco Mao Meno", new HashMap<>());

        banco.createConta(1, false, 1000, Conta.Tipo.CONTA_CORRENTE);


        Thread testeThread = new Thread(new Teste(banco));
        Thread testeThread2 = new Thread(new Teste(banco));
        Thread testeThread3 = new Thread(new Teste(banco));
        Thread testeThread4 = new Thread(new Teste(banco));
        testeThread.start();
        testeThread2.start();
        testeThread3.start();
        testeThread4.start();
        try {
            testeThread.join();
            testeThread2.join();
            testeThread3.join();
            testeThread4.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(banco.getContas().get(1).getSaldo());

        Thread teste = new Thread(new TesteCriacaoExclusao(banco2));
        Thread teste2 = new Thread(new TesteCriacaoExclusao(banco2));
        Thread teste3 = new Thread(new TesteCriacaoExclusao(banco2));
        Thread teste4 = new Thread(new TesteCriacaoExclusao(banco2));
        teste.start();
        teste2.start();
        teste3.start();
        teste4.start();
        try {
            teste.join();
            teste2.join();
            teste3.join();
            teste4.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(banco2.getContas().size());


    }

    public static class TesteCriacaoExclusao implements Runnable{
        private Banco banco;

        public TesteCriacaoExclusao(Banco banco) {
            this.banco = banco;
        }

        @Override
        public void run() {
            for( int i = 0; i <= 1000; i++ ){

                banco.createConta(1, false, 1000, Conta.Tipo.CONTA_CORRENTE);
                banco.excluirConta(1);

            }
        }
    }


    public static class Teste implements Runnable{
       private Banco banco;

        public Teste(Banco banco) {
            this.banco = banco;
        }

        @Override
        public void run() {
            for( int i = 0; i <= 1000; i++ ){

               this.banco.getContas().get(1).depositar(1);
                this.banco.getContas().get(1).sacar(1);
            }

        }
    }

   /* public static class TesteCriar implements Runnable{
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
                            *//* System.out.println(this.banco.getContas().get(1).getSaldo());*//*
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
                            *//*System.out.println(this.banco.getContas().get(1).getLimite());*//*
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
    }*/
}
