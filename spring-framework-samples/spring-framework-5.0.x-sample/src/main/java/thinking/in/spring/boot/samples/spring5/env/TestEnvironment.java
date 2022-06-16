package thinking.in.spring.boot.samples.spring5.env;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;

public class TestEnvironment extends AbstractEnvironment {

    /**
     * further read at {@link AbstractEnvironment#customizePropertySources(MutablePropertySources)}
     */
    private String notInitialValue = "notInitialValue";
    private final String initialValue = "initialValue";
    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {
        super.customizePropertySources(propertySources);
        System.out.printf("The default value is %s%n", notInitialValue);
        System.out.printf("The default value2 is %s%n", initialValue);
    }
}
