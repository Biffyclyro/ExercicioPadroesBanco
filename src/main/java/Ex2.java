import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Ex2 {
    public static void main(String[] args) {

        System.out.println("HashMap:");
        final var banco = new Banco("Novo Banco Modernoso", new HashMap<>());
        System.out.println("TreeMap:");
        final var banco2 = new Banco("Novo Menos Modernoso", new TreeMap<>());
        System.out.println("LinkedHashMap:");
        final var banco3 = new Banco("Novo Nada Modernoso", new LinkedHashMap<>());





       /* banco.createConta(1, false, 1000, Conta.Tipo.CONTA_CORRENTE);
        banco.createConta(2, true, 1000, Conta.Tipo.RENDA_VARIAVEL);
        banco.createConta(3, true, 1000, Conta.Tipo.POUPANCA);
        banco.createConta(4, true, 1000, Conta.Tipo.RENDA_FIXA);
        banco.createConta(5, true, 1000, Conta.Tipo.RENDA_FIXA);



        banco.depositar(1, 200);
        banco.depositar(2, 1000);
        banco.depositar(3, 20);
        banco.depositar(4, 50);
        banco.depositar(5, 1);

        banco.transferir(2, 3, 100);

        banco.sacar(1, 100);
        banco.depositar(4, 50);

        banco.excluirConta(5);

        banco.tirarEstrato(1);
        banco.tirarEstrato(2);
        banco.tirarEstrato(3);
        banco.tirarEstrato(4)*/;


    }
}
