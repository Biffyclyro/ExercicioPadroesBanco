import lombok.Data;

@Data
public class Movimentacao {
    private String descricao;
    private double valor;
    private TipoMovimento tipoMovimento;

    public Movimentacao(String descricao, double valor, TipoMovimento tipoMovimento) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipoMovimento = tipoMovimento;
    }

    public enum TipoMovimento {
        DEBITO,
        CREDITO,
        RENIMENTO
    }

}
