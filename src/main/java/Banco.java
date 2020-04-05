import java.util.HashMap;
import java.util.Map;

public class Banco {
    private String nome;
    private Map<Integer, Conta> contas;

    public Banco(String nome, Map<Integer, Conta> contas) {
        this.nome = nome;
        this.contas = contas;

        CriadorDeContas.criarConta(this.contas);
        CriadorDeContas.percorrerContas(this.contas);
        CriadorDeContas.getTodas(this.contas);
        System.out.println("");
    }

    public void createConta(int numero, boolean especial, double limite, Conta.Tipo tipo){
        final var conta = new Conta(numero, especial, limite, tipo);
        this.contas.put(conta.getNumero(), conta);
    }

    public void excluirConta(int numero){
        this.contas.remove(numero);
    }

   public void render(){
       for (Conta conta: this.contas.values()) conta.render();
   }

   public void depositar(int conta, double valor){

        this.contas.get(conta).depositar(valor);
   }

   public void sacar(int conta, double valor){
        this.contas.get(conta).sacar(valor);
   }

   public void transferir(int contaOrigem, int contaFim, double valor){
        final var de = this.contas.get(contaOrigem);
        final var para = this.contas.get(contaFim);
        de.sacar(valor);
        para.depositar(valor);
   }

   public void tirarEstrato(int numero){
        final var conta = this.contas.get(numero);

        if(conta != null){
            conta.tirarEstrato();
        }
   }
}
