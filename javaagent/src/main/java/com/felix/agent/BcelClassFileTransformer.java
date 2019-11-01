package com.felix.agent;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class BcelClassFileTransformer implements ClassFileTransformer {

    private String agentArgs;

    public BcelClassFileTransformer(String agentArgs) {
        this.agentArgs = agentArgs;
    }

    /**
     * 测试
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer)
            throws IllegalClassFormatException {
        if(!className.equals("com./felix/agent")){
            return classfileBuffer;
        }

        try {
            JavaClass clazz = Repository.lookupClass(className);
            ClassGen cg = new ClassGen(clazz);
            ConstantPoolGen cp = cg.getConstantPool();
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals("getGreeting")) {
                    MethodGen mg = new MethodGen(method, cg.getClassName(), cp);
                    InstructionList il = new InstructionList();
                    il.append(new PUSH(cp, this.agentArgs+"caoming"));
                    il.append(InstructionFactory.createReturn(Type.STRING));
                    mg.setInstructionList(il);
                    mg.setMaxStack();
                    mg.setMaxLocals();
                    cg.replaceMethod(method, mg.getMethod());
                }
            }
            return cg.getJavaClass().getBytes();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
