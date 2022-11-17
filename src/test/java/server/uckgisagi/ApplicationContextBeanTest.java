package server.uckgisagi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@SpringBootTest
public class ApplicationContextBeanTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("등록된 모든 Bean 조회")
    void retrieve_all_beans() {
        if (applicationContext != null) {
            String[] beans = applicationContext.getBeanDefinitionNames();

            for (String bean : beans) {
                System.out.println(bean);
            }
        }
    }

    /**
     * order: 0 requestMappingHandlerMapping = RequestMappingHandlerMapping
     *<br>
     * order: 1 viewControllerHandlerMapping = SimpleUrlHandlerMapping (WebConfig 수정 후 정상 등록)
     *<br>
     * order: 2 beanNameHandlerMapping = BeanNameUrlHandlerMapping
     *<br>
     * order: 3 routerFunctionMapping = RouterFunctionMapping
     *<br>
     * order: 2147483646 resourceHandlerMapping = SimpleUrlHandlerMapping
     */
    @Test
    @DisplayName("Spring 에서 자동으로 추가하는 HnadlerMapping의 우선순위 조회")
    void retrieve_HandlerMapping() {
        Map<String, HandlerMapping> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, HandlerMapping.class, true, false);
        matchingBeans.forEach((k, v) -> {
            System.out.printf(
                    "order: %s %s = %s %n%n",
                    ((Ordered) v).getOrder(), k, v.getClass().getSimpleName());
        });
    }
}
