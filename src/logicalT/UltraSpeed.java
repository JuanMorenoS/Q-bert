package logicalT;

public class UltraSpeed extends Help implements UnCapturable {
	private Thread useThread;
	private boolean live = true;

	public void use(Player a) {
		useThread = new Thread(new Runnable() {
			public void run() {
				a.logic.stopThread();
				try {
					killThread();
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a.logic.resumeThread();
				live=false;
			}
		});
		useThread.start();
	}

	private void killThread() {
		if (!live)
			useThread.stop();
	}

	public String toString() {
		return "UltraSpeed";
	}

}
