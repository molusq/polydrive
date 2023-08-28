package stopwatch_v3.defaultsm;

import stopwatch_v3.IStatemachine;
import stopwatch_v3.ITimerCallback;

import org.yakindu.sct.generator.polytech.java.types.Event;



public interface IDefaultSMStatemachine extends ITimerCallback,IStatemachine {

	public interface SCInterface {
	
	
		public Event startPlaying = new Event();
		public Event startPause = new Event();
		public Event resumePlaying = new Event();
		public Event startStop = new Event();
		public Event reset = new Event();
		public Event updateCounter = new Event();
		public Event updateDisplay = new Event();
	
		public void raiseLeftButton();
		
		public void raiseRightButton();
		
		
		
		
		
		
		
		
		public long getI();
		
		public void setI(long value);
		
	}
	
	public SCInterface getSCInterface();
	
	
	
}
