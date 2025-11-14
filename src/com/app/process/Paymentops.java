package com.app.process;

 record fundtransfer(int x,int y) {}
public class Paymentops {

	public static void main(String[] args) {
		fundtransfer ft=new fundtransfer(30000,40000);
		System.out.println(ft);
		System.out.println("hello");
	}
   
}
