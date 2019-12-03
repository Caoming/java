package com.felix.byte.buddy

import net.bytebuddy.ByteBuddy
import net.bytebuddy.agent.ByteBuddyAgent
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy
import net.bytebuddy.implementation.FixedValue
import net.bytebuddy.matcher.ElementMatcher
import net.bytebuddy.matcher.ElementMatchers
import org.junit.Assert
import org.junit.Test

class BuddyTest{

    @Test
    fun testBuddy(){
        val loaded = ByteBuddy().subclass(Object::class.java).method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello World!"))
            .make()
            .load(javaClass.classLoader).loaded
        Assert.assertEquals(loaded.newInstance().toString(),"Hello World!")
    }

    class Foo{
        fun m():String{
            return "foo"
        }
    }

    class Bar{
        fun m():String{
            return "bar"
        }
    }

    @Test
    fun testClassLoad(){
        ByteBuddyAgent.install()
        val foo = Foo()
        ByteBuddy().
            redefine(Bar::class.java).name(Foo::class.java.name).make()
            .load(Foo::class.java.classLoader,
                ClassReloadingStrategy.fromInstalledAgent())

        Assert.assertEquals(foo.m(),"bar")

    }


}