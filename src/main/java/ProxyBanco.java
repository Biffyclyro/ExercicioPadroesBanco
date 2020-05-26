import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyBanco implements InvocationHandler {
    private final Banco banco;
    private FileWriter fileWriter;

    public ProxyBanco(Banco banco) {
        this.banco = banco;
        try {
            this.fileWriter = new FileWriter("log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        final var inicio = System.nanoTime();

        final var retornoMetodo = method.invoke(banco, args);

        final var fim = System.nanoTime();

        final var printWriter = new PrintWriter(fileWriter);

        this.fileWriter.flush();

        printWriter.println("Metodo: " + method.getName() + " levou: " + (fim - inicio) + " milisegudos");

        return retornoMetodo;
    }
}
