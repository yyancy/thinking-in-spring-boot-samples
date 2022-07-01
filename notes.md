## Reading Notes

### Spring Boot Class
* org.springframework.boot.loader.JarLauncher
* org.springframework.boot.loader.WarLauncher

### Web Server Class
* TomcatWebServer
* JettyWebServer
* UndertowWebServer

### Class References
* ApplicationRunner  invoking after spring boot started.

### Web Event
* WebServerInitializedEvent  invoking after web server initialized.

### Auto configuration
#### Annotation
`@SpringBootApplication` do three things:
* `@EnableAutoConfiguration` enable auto-configuration mechanism
* `@ComponentScan` enable `@Component` scan mechanism
* `@Configuration` make current class as a configuration class


The difference of `@Bean` within `@Component` and `@Configuration`:
1. in `@Component`， `@Bean` class has the normal java semantic, without special CGLIB processing or other constraint applying
2. in `@Configuration`,`@Bean` class has enhanced with CGLIB. 


#### Spring annotation programing model
* meta-annotations  a meta-annotation that is declared on another annotation.
* stereotype annotations a stereotype annotation is an annotation that is used to declare the role that a component plays within the application.

#### parser class with spring 2.5 version(XML method) <context:component-scan/> element
* `ComponentScanBeanDefinitionParser#parse(...)` scanning base package and read spring beans.
* `ClassPathBeanDefinitionScanner#doScan(String...)`
* `AnnotationTypeFilter` filtering class which has annotated with some annotation like `@Component`

#### the mechanism of multiple level @Component
* `AnnotationMetadata` represents the metadata of all annotations of an underlying class
* `AnnotationAttributesReadingVisitor#visitEnd(...)` visit all annotation recursively
* `AnnotationMetadataReadingVisitor#getAnnotationTypes(...)` get all meta-annotation types


#### parser annotation class with spring 3.0 or above <context:annotation-config/> element.
* `AnnotationConfigBeanDefinitionParser#parse()`
* `AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry,Object)` Register all relevant annotation post processors in the given registry
* `ConfigurationClassPostProcessor` handle configuration class and @bean method
* `ConfigurationClassParser#processConfigurationClass` process @Configuration class with `@import`
or `@ImportResource` annotations and `@Bean` method

## NOTE

* If you want to use java standard reflection to get metadata from a class, you must be sure that the class is loaded by a `ClassLoader`
* Since AOP auto-proxying is implemented as a BeanPostProcessor itself, no BeanPostProcessors
  or directly referenced beans are eligible for auto-proxying; when a bean is created during BeanPostProcessor instantiation, i.e. when
  a bean is not eligible for getting processed by all BeanPostProcessors. further information read at `org.springframework.context.support.AbstractApplicationContext#registerBeanPostProcessors()`
  note: `beanfacoty#getBean()` method will invoke beanPostProcessor's method.
