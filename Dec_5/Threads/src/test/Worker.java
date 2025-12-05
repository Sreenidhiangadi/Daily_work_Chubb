package test;

public class Worker implements Runnable {

int add(int a,int b)
{
	return a+b;
}
	@Override
	public void run() {
		add(1,2);
	}

}
