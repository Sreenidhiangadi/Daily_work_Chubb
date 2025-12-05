package test;

public class VrtualThread {

    public static void main(String[] args) throws InterruptedException {
        Thread virtual = new Thread(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        }, "virtual");

        virtual.start();   
        virtual.join(); 
        System.out.println("Virtual-thread-like logic done");
    }
}
