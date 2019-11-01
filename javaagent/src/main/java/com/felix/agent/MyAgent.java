package com.felix.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    /**
     *
     * @param agentOps
     * @param instrumentation
     */
    public static void premain(String agentOps, Instrumentation instrumentation) {
        System.out.println("premain执行了");
        System.out.println(agentOps);
//        instrumentation.addTransformer(new ClassFileTransformer() {
//            @Override
//            public byte[] transform(ClassLoader loader, String className,
//                                    Class<?> classBeingRedefined,
//                                    ProtectionDomain protectionDomain, byte[] classfileBuffer)
//                    throws IllegalClassFormatException {
//                    String cname = "com.felix.agent.HelloService";
//                    ClassPool classPool = new ClassPool();
//                    classPool.insertClassPath(new LoaderClassPath(loader));
//                    if(cname.replaceAll("/",".").equals(cname)){
//                        try {
//                            CtClass ctClass = classPool.get(cname);
//                            CtMethod ctMethod = ctClass.getDeclaredMethod("getName");
//                            ctMethod.insertBefore("System.out.println(System.currentTimeMillis())");
//                            return  ctClass.toBytecode();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                }
//
//                return new byte[0];
//            }
//        });

//        new AgentBuilder.Default()
//                .type(ElementMatchers.named("com.felix.agent"))
//                .transform(new AgentBuilder.Transformer() {
//                    @Override
//                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
//                                                            TypeDescription typeDescription,
//                                                            ClassLoader classLoader,
//                                                            JavaModule javaModule) {
//                        return builder.method(ElementMatchers.named("getGreeting"))
//                                .intercept(FixedValue.value(agentOps));
//                    }
//                }).installOn(instrumentation);

        new AgentBuilder.Default()
                .type(ElementMatchers.named("com.felix.agent"))
                .transform((builder, type, classLoader, module) ->
                        builder.method(ElementMatchers.nameMatches("getGreeting"))
                                .intercept(MethodDelegation.to(TimingInterceptor.class)))
                .installOn(instrumentation);
//        instrumentation.addTransformer(new BcelClassFileTransformer(agentOps));
    }

    /**
     *
     * @param agentOps
     */
    public static void premain(String agentOps) {
        System.out.println("premain执行了2");
        System.out.println(agentOps);
    }


}
