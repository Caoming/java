package com.felix.byte.buddy

import net.bytebuddy.ByteBuddy
import net.bytebuddy.dynamic.ClassFileLocator
import net.bytebuddy.pool.TypePool

class MyApplication {

    class Bar{}

    fun main(arr:Array<String>){
//        val typePool = TypePool.Default.of(MyApplication::class.java.classLoader)
//        ByteBuddy().redefine(typePool.describe("com.felix.byte.buddy.Bar")!!.resolve(),
//            ClassFileLocator.ForClassLoader.of(MyApplication::class.java.classLoader))
    }
}