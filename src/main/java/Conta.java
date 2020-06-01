import java.util.ArrayList;
import java.util.List;

public class Conta implements Prototype<Conta> {
    private int numero;
    private double saldo;
    private final boolean especial;
    private double limite;
    private final List<Movimentacao> movimentacoes = new ArrayList<>();
    private final Tipo tipo;
    private final List<Subscriber> subscribers = new ArrayList<>();

    public Conta(int numero, boolean especial, double limite, Tipo tipo) {
        this.numero = numero;
        this.especial = especial;
        this.limite = limite;
        this.tipo = tipo;
    }

    private Conta(Conta copia) {
        this.numero = copia.numero + 1;
        this.especial = copia.especial;
        this.limite = copia.limite;
        this.tipo = copia.tipo;
    }


    public enum Tipo {
        POUPANCA,
        CONTA_CORRENTE,
        RENDA_FIXA,
        RENDA_VARIAVEL
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isEspecial() {
        return especial;
    }

    public double getLimite() {
        return limite;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void tirarExtrato() {
        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println(movimentacao.getDescricao()
                    + "R$ " + movimentacao.getValor()
                    + " Tipo: " + movimentacao.getTipoMovimento());
        }
        System.out.println("");
    }


    public synchronized void sacar(double valor) {

        if (this.saldo + this.limite > 0) {
            this.saldo -= valor;

            this.movimentacoes.add(new Movimentacao("Saque ", valor, Movimentacao.TipoMovimento.DEBITO));
        } else {
            System.out.println("Saldo/Limite insuficiente");
        }


        this.notifySubscribes();
    }

    public synchronized void depositar(double valor) {

        this.saldo += valor;
        this.movimentacoes.add(new Movimentacao("Deposito ", valor, Movimentacao.TipoMovimento.DEBITO));

        this.notifySubscribes();

    }


    public void render() {

        switch (this.tipo) {

            case POUPANCA:
                this.saldo += this.saldo * 0.05;
                this.movimentacoes.add(new Movimentacao("Rendimento poupança ",
                        this.saldo * 0.05, Movimentacao.TipoMovimento.RENIMENTO));

            case RENDA_FIXA:
                final var rendimento = this.saldo * 0.01;
                final var taxa = rendimento * 0.27;
                this.saldo += rendimento - taxa;
                this.movimentacoes.add(new Movimentacao("Rendimento da renda fixa ",
                        rendimento - taxa, Movimentacao.TipoMovimento.RENIMENTO));

            case RENDA_VARIAVEL:
                final var rendimentoVariavel = this.saldo * Math.random() / 2;
                final var taxaVariavel = rendimentoVariavel * 0.27;
                this.saldo += rendimentoVariavel - taxaVariavel;
                this.movimentacoes.add(new Movimentacao("Rendimento da renda Variavel ",
                        rendimentoVariavel - taxaVariavel, Movimentacao.TipoMovimento.RENIMENTO));

            default:
                break;
        }

        this.notifySubscribes();

    }


    //parte de ser publisher
    private void notifySubscribes() {
        this.subscribers.forEach( s -> s.update(this));
    }

    public void subscribe(Subscriber s){
       this.subscribers.add(s);
    }

    public void unsubscribe(Subscriber s){
        this.subscribers.remove(s);
    }

    @Override
    public Conta clone() {
        return new Conta(this);
    }
}
