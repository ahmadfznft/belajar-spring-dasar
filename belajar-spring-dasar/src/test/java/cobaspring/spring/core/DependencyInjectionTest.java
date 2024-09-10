package cobaspring.spring.core;

import cobaspring.spring.core.data.Bar;
import cobaspring.spring.core.data.DependencyInjectionConfiguration;
import cobaspring.spring.core.data.Foo;
import cobaspring.spring.core.data.FooBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyInjectionTest {

    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(DependencyInjectionConfiguration.class);
    }

    @Test
    void TestDI(){
        Foo foo = applicationContext.getBean("fooSecond", Foo.class);
        applicationContext.getBean(Bar.class);
        FooBar fooBar = applicationContext.getBean(FooBar.class);

        Assertions.assertSame(foo, fooBar.getFoo());
        Assertions.assertSame(foo, fooBar.getBar());

    }

    @Test
    void testNoDI(){

        var foo = new Foo();
        var bar = new Bar();

        var fooBar = new FooBar(foo, bar);

        Assertions.assertSame(foo, fooBar.getFoo());
        Assertions.assertSame(foo, fooBar.getBar());
    }

}
