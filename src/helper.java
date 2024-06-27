public class helper {
	public static void ThreadMsg(String msg) {
		System.out.println(Thread.currentThread().getName()+" : "+msg);
	}
}