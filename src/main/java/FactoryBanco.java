import java.lang.reflect.Proxy;

public class FactoryBanco implements Creator {
    @Override
    public Banco factoryMethod() {

        return (Banco) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{Banco.class},
                new ProxyBanco(BancoConcrete.getInstanceOf())
        );
    }
}
