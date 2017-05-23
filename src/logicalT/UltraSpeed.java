package logicalT;

public class UltraSpeed extends Help implements UnCapturable{

	public void use(Player a) {
		new Thread(new Runnable() {
			public void run() {
				a.logic.stopThread();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a.logic.startThread();
				
			}
		}).start();
		
	}

	public String toString() {
		return "UltraSpeed";
	}
	
}
