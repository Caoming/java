package com.felix.strategy;

/**
 * 策略模式
 * @Author caoming
 * @Date: 2020/4/14 14:22
 */
public class StrategyPatterns {

    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new CarStrategy());
        context.startStrategyMethod();

        context.setStrategy(new BikeStrategy());
        context.startStrategyMethod();
    }

    /**
     * 策略的接口
     */
    interface Strategy{
        public void strategyMethod();
    }

    static class CarStrategy implements Strategy{
        @Override
        public void strategyMethod() {
            System.out.println("car run ");
        }
    }

   static class BikeStrategy implements Strategy{
        @Override
        public void strategyMethod() {
            System.out.println("bike run ");
        }
    }

    static class Context{
        private Strategy strategy;

        public Strategy getStrategy() {
            return strategy;
        }

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        public void startStrategyMethod(){
            strategy.strategyMethod();
        }
    }
}
