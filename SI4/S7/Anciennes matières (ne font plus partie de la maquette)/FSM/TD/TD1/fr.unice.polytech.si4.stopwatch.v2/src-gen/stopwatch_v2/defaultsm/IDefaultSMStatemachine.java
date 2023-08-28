package stopwatch_v2.defaultsm;

import stopwatch_v2.IStatemachine;

import org.yakindu.sct.generator.polytech.java.types.Event;



public interface IDefaultSMStatemachine extends IStatemachine {

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
