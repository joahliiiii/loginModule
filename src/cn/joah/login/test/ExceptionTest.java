package cn.joah.login.test;

public class ExceptionTest {
    public static boolean catchMethod() {
       System.out.print("call catchMethod and return  --->>  ");
       return false;
    }
    public static void finallyMethod() {
        System.out.println();
        System.out.print("call finallyMethod and do something  --->>  ");
    }
    /*public static boolean catchFinallyTest1() {
       try {
            int i = 10 / 0; // 抛出 Exception，后续处理被拒绝
            System.out.println("i vaule is : " + i);
            return true;   // Exception 已经抛出，没有获得被执行的机会
       } catch (Exception e) {
           System.out.println(" -- Exception --");
           return catchMethod();  // Exception 抛出，获得了调用方法的机会，但方法值在 finally 执行完后才返回
       }finally{
            finallyMethod();  // Exception 抛出，finally 代码块将在 catch 执行 return 之前被执行
       }
    }*/

    public static void main(String[] args) {
        try {
            int i = 10 / 0; // 抛出 Exception，后续处理被拒绝
            System.out.println("i vaule is : " + i);
//            return true;   // Exception 已经抛出，没有获得被执行的机会
        } catch (Exception e) {
            System.out.println(" -- Exception --");
//            return catchMethod();  // Exception 抛出，获得了调用方法的机会，但方法值在 finally 执行完后才返回
        }finally{
            finallyMethod();  // Exception 抛出，finally 代码块将在 catch 执行 return 之前被执行
        }
        System.out.println("helloooooo");
    }
}
