package com.felix.chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 责任链模式
 * 作用：解耦，调用方不需要知道内部的有什么东西，只需要调用方法就OK，而被调用方针对不同的业务单独配置对应的方法和方式，拆分对应的责任，
 * 问题：每个节点都需要执行，可以带来不需要的消耗，可以和策略模式结合用来做每个责任都需要执行的场景。
 * @Author caoming
 * @Date: 2020/5/20 19:57
 */
public class ChainPatterns {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Trouble{
        private Integer number;
    }

    public static abstract class Support{
        private String name;
        private Support next;

        public Support(String name){
            this.name = name;
        }

        public Support setNext(Support next){
            this.next = next;
            return next;
        }

        @Override
        public String toString(){
            return "[" + this.name + "]";
        }

        public final void support(Trouble trouble){
            if(resolve(trouble)){
                done(trouble);
            }else if(next != null){
                next.support(trouble);
            }else{
                fail(trouble);
            }
        }

        public abstract boolean resolve(Trouble trouble);

        public void done(Trouble trouble){
            System.out.println(trouble + " is resolved by" + this + ".");
        }

        public void fail(Trouble trouble){
            System.out.println(trouble + "cannot be resolved.");
        }
    }

    /**
     * 啥都不做
     */
    public static class NoSupport extends Support{

        public NoSupport(String name) {
            super(name);
        }

        @Override
        public boolean resolve(Trouble trouble) {
            return false;
        }
    }

    public static class OddSupport extends Support{

        public OddSupport(String name) {
            super(name);
        }

        @Override
        public boolean resolve(Trouble trouble) {
            if(trouble.getNumber() % 2 == 1){
                return true;
            }
            return false;
        }
    }

    public static class LimitSupport extends Support{
        private  Integer limit;

        public LimitSupport(String name, Integer limit) {
            super(name);
            this.limit = limit;
        }

        @Override
        public boolean resolve(Trouble trouble) {
            if(trouble.getNumber() < limit){
                return true;
            }
            return false;
        }
    }

    public static class SpecialSupport extends Support{
        private int number;

        public SpecialSupport(String name, int number) {
            super(name);
            this.number = number;
        }

        @Override
        public boolean resolve(Trouble trouble) {
            if(trouble.getNumber() == number){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Support alice   = new NoSupport("Alice");
        Support bob     = new LimitSupport("Bob", 100);
        Support charlie = new SpecialSupport("Charlie", 429);
        Support diana   = new LimitSupport("Diana", 200);
        Support elmo    = new OddSupport("Elmo");
        Support fred    = new LimitSupport("Fred", 300);

        alice.setNext(bob).setNext(charlie).setNext(diana).setNext(elmo).setNext(fred);

        for(Integer i=0;i<500;i+=1) {
            alice.support(new Trouble(i));
        }
    }
}
