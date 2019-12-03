package com.felix.agent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.Assert;
import org.junit.Test;

public class BuddyAgent {

    public static void premain() {

    }

    @Test
    public void test(){
        new ByteBuddy().with(new NamingStrategy.AbstractBase() {

            @Override
            protected String name(TypeDescription typeDescription) {
                return "i love ByteBuddy" + typeDescription.getSimpleName();
            }
        }).subclass(Object.class).make().load(BuddyAgent.class.getClassLoader());
    }

    class Foo {
        String m() { return "foo"; }
    }

    class Bar {
        String m() { return "bar"; }
    }

    @Test
    public void testReloading(){
        ByteBuddyAgent.install();
        Foo foo = new Foo();
        new ByteBuddy().redefine(Bar.class).name(Foo.class.getName()).
                make().load(Foo.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(foo.m());
        System.out.println(new Bar().m());
        Assert.assertEquals(foo.m(),"bar");
    }

}
