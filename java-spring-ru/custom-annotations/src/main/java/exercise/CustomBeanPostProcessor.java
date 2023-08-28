package exercise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

// BEGIN
@Component
@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor {

    Map<Integer, String> objectLogLevel = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            objectLogLevel.put(bean.hashCode(), level);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (objectLogLevel.containsKey(bean.hashCode())) {
            Object proxyInstance = Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        if (objectLogLevel.get(bean.hashCode()).equals("debug")) {
                            log.debug("Was called method: " + method.getName()
                                    + "() with arguments: [" + Arrays.stream(args)
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(", ")) + "]");
                        } else if (objectLogLevel.get(bean.hashCode()).equals("info")) {
                            log.info("Was called method: " + method.getName()
                                    + "() with arguments: [" + Arrays.stream(args)
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(", ")) + "]");
                        }
                        Object result = method.invoke(bean, args);
                        return result;
                    }
            );
            return proxyInstance;
        }
        return bean;
    }
}
// END
