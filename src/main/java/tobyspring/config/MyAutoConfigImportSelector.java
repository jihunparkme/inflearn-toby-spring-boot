package tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    /**
     * 동적 자동 구성 정보 등록
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "tobyspring.config.autoconfig.DispatcherServletConfig",
                "tobyspring.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
