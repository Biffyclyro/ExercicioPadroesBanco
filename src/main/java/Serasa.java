import java.util.ArrayList;
import java.util.List;

public class Serasa {
    private static final List<Conta> devedores = new ArrayList<>();

    public static void addConta(Conta c){
        devedores.add(c);
    }

    public static void removeConta(Conta c){
        devedores.remove(c);
    }

    public static int getTamnho(){
        return devedores.size();
    }

    public static boolean isEndividado(Conta c){
        return devedores.contains(c);
    }
}
