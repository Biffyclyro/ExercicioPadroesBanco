import java.util.Map;

public class FactoryBanco implements Creator {
    @Override
    public Banco factoryMethod() {
        return BancoConcrete.getInstanceOf();
    }
}
