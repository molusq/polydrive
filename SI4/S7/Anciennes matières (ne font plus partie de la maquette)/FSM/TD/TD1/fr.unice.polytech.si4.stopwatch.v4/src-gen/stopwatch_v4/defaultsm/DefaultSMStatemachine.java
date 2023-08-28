	package stopwatch_v4.defaultsm;
	import stopwatch_v4.ITimer;
	
	public class DefaultSMStatemachine implements IDefaultSMStatemachine {

		protected class SCInterfaceImpl implements SCInterface {
		
			
			private boolean leftButton;
			public void raiseLeftButton() {
				leftButton = true;
				runCycle();
			}
			
			
			private boolean rightButton;
			public void raiseRightButton() {
				rightButton = true;
				runCycle();
			}
			
			
			private boolean sideButton;
			public void raiseSideButton() {
				sideButton = true;
				runCycle();
			}
			
			
			
			protected void raiseStartPlaying() {
				startPlaying.emits();
			}
			
			
			
			protected void raiseStartPause() {
				startPause.emits();
			}
			
			
			
			protected void raiseResumePlaying() {
				resumePlaying.emits();
			}
			
			
			
			protected void raiseStartStop() {
				startStop.emits();
			}
			
			
			
			protected void raiseReset() {
				reset.emits();
			}
			
			
			
			protected void raiseUpdateCounter() {
				updateCounter.emits();
			}
			
			
			
			protected void raiseUpdateDisplay() {
				updateDisplay.emits();
			}
			
			
			
			protected void raiseSidePressed() {
				sidePressed.emits();
			}
			
			
			
			protected void raiseSideIdle() {
				sideIdle.emits();
			}
			
			private long i;
			
			public long getI() {
				return i;
			}
			
			public void setI(long value) {
				this.i = value;
			}
			
			protected void clearEvents() {
				leftButton = false;
				rightButton = false;
				sideButton = false;
			}
			public void clearOutEvents() {
			//nothings to do with the newly introduced Event Type
			}
			
		}
		
		protected SCInterfaceImpl sCInterface;
		
		private boolean initialized = false;
		
		public enum State {
			main_region_stopWatch,
			main_region_stopWatch_r1_RESET,
			main_region_stopWatch_r1_STOP,
			main_region_stopWatch_r1_timerRunning,
			main_region_stopWatch_r1_timerRunning_r1_timerRunning,
			main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING,
			main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED,
			main_region_stopWatch_r1_timerRunning_r2_COUNTING,
			main_region_stopWatch_r2_IDLE,
			main_region_stopWatch_r2_PRESSED,
			$NullState$
		};
		
		private final State[] stateVector = new State[3];
		
		private int nextStateIndex;
		
		private ITimer timer;
		
		private final boolean[] timeEvents = new boolean[2];
		public DefaultSMStatemachine() {
			sCInterface = new SCInterfaceImpl();
		}
		
		public void init() {
			this.initialized = true;
			if (timer == null) {
				throw new IllegalStateException("timer not set.");
			}
			for (int i = 0; i < 3; i++) {
				stateVector[i] = State.$NullState$;
			}
			clearEvents();
			clearOutEvents();
			sCInterface.setI(0);
		}
		
		public void enter() {
			if (!initialized) {
				throw new IllegalStateException(
						"The state machine needs to be initialized first by calling the init() function.");
			}
			if (timer == null) {
				throw new IllegalStateException("timer not set.");
			}
			enterSequence_main_region_default();
		}
		
		public void exit() {
			exitSequence_main_region();
		}
		
		/**
		 * @see IStatemachine#isActive()
		 */
		public boolean isActive() {
			return stateVector[0] != State.$NullState$||stateVector[1] != State.$NullState$||stateVector[2] != State.$NullState$;
		}
		
		/** 
		* Always returns 'false' since this state machine can never become final.
		*
		* @see IStatemachine#isFinal()
		*/
		public boolean isFinal() {
			return false;
		}
		/**
		* This method resets the incoming events (time events included).
		*/
		protected void clearEvents() {
			sCInterface.clearEvents();
			for (int i=0; i<timeEvents.length; i++) {
				timeEvents[i] = false;
			}
		}
		
		/**
		* This method resets the outgoing events !
		*/
		public void clearOutEvents() {
		}
		
		/**
		* Returns true if the given state is currently active otherwise false.
		*/
		public boolean isStateActive(State state) {
		
			switch (state) {
			case main_region_stopWatch:
				return stateVector[0].ordinal() >= State.
						main_region_stopWatch.ordinal()&& stateVector[0].ordinal() <= State.main_region_stopWatch_r2_PRESSED.ordinal();
			case main_region_stopWatch_r1_RESET:
				return stateVector[0] == State.main_region_stopWatch_r1_RESET;
			case main_region_stopWatch_r1_STOP:
				return stateVector[0] == State.main_region_stopWatch_r1_STOP;
			case main_region_stopWatch_r1_timerRunning:
				return stateVector[0].ordinal() >= State.
						main_region_stopWatch_r1_timerRunning.ordinal()&& stateVector[0].ordinal() <= State.main_region_stopWatch_r1_timerRunning_r2_COUNTING.ordinal();
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning:
				return stateVector[0].ordinal() >= State.
						main_region_stopWatch_r1_timerRunning_r1_timerRunning.ordinal()&& stateVector[0].ordinal() <= State.main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED.ordinal();
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING:
				return stateVector[0] == State.main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING;
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED:
				return stateVector[0] == State.main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED;
			case main_region_stopWatch_r1_timerRunning_r2_COUNTING:
				return stateVector[1] == State.main_region_stopWatch_r1_timerRunning_r2_COUNTING;
			case main_region_stopWatch_r2_IDLE:
				return stateVector[2] == State.main_region_stopWatch_r2_IDLE;
			case main_region_stopWatch_r2_PRESSED:
				return stateVector[2] == State.main_region_stopWatch_r2_PRESSED;
			default:
				return false;
			}
		}
		
		/**
		* Set the {@link ITimer} for the state machine. It must be set
		* externally on a timed state machine before a run cycle can be correctly
		* executed.
		* 
		* @param timer
		*/
		public void setTimer(ITimer timer) {
			this.timer = timer;
		}
		
		/**
		* Returns the currently used timer.
		* 
		* @return {@link ITimer}
		*/
		public ITimer getTimer() {
			return timer;
		}
		
		public void timeElapsed(int eventID) {
			timeEvents[eventID] = true;
			runCycle();
		}
		
		public SCInterface getSCInterface() {
			return sCInterface;
		}
		
		public void raiseLeftButton() {
			sCInterface.raiseLeftButton();
		}
		
		public void raiseRightButton() {
			sCInterface.raiseRightButton();
		}
		
		public void raiseSideButton() {
			sCInterface.raiseSideButton();
		}
		
		
		
		
		
		
		
		
		
		
		public long getI() {
			return sCInterface.getI();
		}
		
		public void setI(long value) {
			sCInterface.setI(value);
		}
		
		private boolean check_main_region_stopWatch_r1_RESET_tr0_tr0() {
			return sCInterface.leftButton;
		}
		
		private boolean check_main_region_stopWatch_r1_STOP_tr0_tr0() {
			return sCInterface.leftButton;
		}
		
		private boolean check_main_region_stopWatch_r1_timerRunning_tr0_tr0() {
			return sCInterface.leftButton;
		}
		
		private boolean check_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr0_tr0() {
			return sCInterface.rightButton;
		}
		
		private boolean check_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr1_tr1() {
			return sCInterface.getI()>=5;
		}
		
		private boolean check_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED_tr0_tr0() {
			return sCInterface.rightButton;
		}
		
		private boolean check_main_region_stopWatch_r1_timerRunning_r2_COUNTING_tr0_tr0() {
			return timeEvents[0];
		}
		
		private boolean check_main_region_stopWatch_r2_IDLE_tr0_tr0() {
			return sCInterface.sideButton;
		}
		
		private boolean check_main_region_stopWatch_r2_PRESSED_tr0_tr0() {
			return timeEvents[1];
		}
		
		private boolean check_main_region_stopWatch_r2_PRESSED_tr1_tr1() {
			return sCInterface.sideButton;
		}
		
		private void effect_main_region_stopWatch_r1_RESET_tr0() {
			exitSequence_main_region_stopWatch_r1_RESET();
			sCInterface.raiseStartPlaying();
			
			enterSequence_main_region_stopWatch_r1_timerRunning_default();
		}
		
		private void effect_main_region_stopWatch_r1_STOP_tr0() {
			exitSequence_main_region_stopWatch_r1_STOP();
			sCInterface.raiseReset();
			
			enterSequence_main_region_stopWatch_r1_RESET_default();
		}
		
		private void effect_main_region_stopWatch_r1_timerRunning_tr0() {
			exitSequence_main_region_stopWatch_r1_timerRunning();
			sCInterface.raiseStartStop();
			
			enterSequence_main_region_stopWatch_r1_STOP_default();
		}
		
		private void effect_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr0() {
			exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING();
			sCInterface.raiseStartPause();
			
			enterSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED_default();
		}
		
		private void effect_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr1() {
			exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING();
			sCInterface.raiseUpdateDisplay();
			
			sCInterface.setI(0);
			
			enterSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_default();
		}
		
		private void effect_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED_tr0() {
			exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED();
			sCInterface.raiseResumePlaying();
			
			enterSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_default();
		}
		
		private void effect_main_region_stopWatch_r1_timerRunning_r2_COUNTING_tr0() {
			exitSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING();
			sCInterface.raiseUpdateCounter();
			
			sCInterface.setI(sCInterface.i + 1);
			
			enterSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING_default();
		}
		
		private void effect_main_region_stopWatch_r2_IDLE_tr0() {
			exitSequence_main_region_stopWatch_r2_IDLE();
			sCInterface.raiseSidePressed();
			
			enterSequence_main_region_stopWatch_r2_PRESSED_default();
		}
		
		private void effect_main_region_stopWatch_r2_PRESSED_tr0() {
			exitSequence_main_region_stopWatch_r2_PRESSED();
			enterSequence_main_region_stopWatch_r2_IDLE_default();
		}
		
		private void effect_main_region_stopWatch_r2_PRESSED_tr1() {
			exitSequence_main_region_stopWatch_r2_PRESSED();
			sCInterface.raiseSideIdle();
			
			enterSequence_main_region_stopWatch_r2_IDLE_default();
		}
		
		/* Entry action for state 'COUNTING'. */
		private void entryAction_main_region_stopWatch_r1_timerRunning_r2_COUNTING() {
			timer.setTimer(this, 0, 7, false);
		}
		
		/* Entry action for state 'PRESSED'. */
		private void entryAction_main_region_stopWatch_r2_PRESSED() {
			timer.setTimer(this, 1, 1000, false);
		}
		
		/* Exit action for state 'COUNTING'. */
		private void exitAction_main_region_stopWatch_r1_timerRunning_r2_COUNTING() {
			timer.unsetTimer(this, 0);
		}
		
		/* Exit action for state 'PRESSED'. */
		private void exitAction_main_region_stopWatch_r2_PRESSED() {
			timer.unsetTimer(this, 1);
		}
		
		/* 'default' enter sequence for state stopWatch */
		private void enterSequence_main_region_stopWatch_default() {
			enterSequence_main_region_stopWatch_r1_default();
			enterSequence_main_region_stopWatch_r2_default();
		}
		
		/* 'default' enter sequence for state RESET */
		private void enterSequence_main_region_stopWatch_r1_RESET_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_stopWatch_r1_RESET;
		}
		
		/* 'default' enter sequence for state STOP */
		private void enterSequence_main_region_stopWatch_r1_STOP_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_stopWatch_r1_STOP;
		}
		
		/* 'default' enter sequence for state timerRunning */
		private void enterSequence_main_region_stopWatch_r1_timerRunning_default() {
			enterSequence_main_region_stopWatch_r1_timerRunning_r1_default();
			enterSequence_main_region_stopWatch_r1_timerRunning_r2_default();
		}
		
		/* 'default' enter sequence for state RUNNING */
		private void enterSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING;
		}
		
		/* 'default' enter sequence for state PAUSED */
		private void enterSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED;
		}
		
		/* 'default' enter sequence for state COUNTING */
		private void enterSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING_default() {
			entryAction_main_region_stopWatch_r1_timerRunning_r2_COUNTING();
			nextStateIndex = 1;
			stateVector[1] = State.main_region_stopWatch_r1_timerRunning_r2_COUNTING;
		}
		
		/* 'default' enter sequence for state IDLE */
		private void enterSequence_main_region_stopWatch_r2_IDLE_default() {
			nextStateIndex = 2;
			stateVector[2] = State.main_region_stopWatch_r2_IDLE;
		}
		
		/* 'default' enter sequence for state PRESSED */
		private void enterSequence_main_region_stopWatch_r2_PRESSED_default() {
			entryAction_main_region_stopWatch_r2_PRESSED();
			nextStateIndex = 2;
			stateVector[2] = State.main_region_stopWatch_r2_PRESSED;
		}
		
		/* 'default' enter sequence for region main region */
		private void enterSequence_main_region_default() {
			react_main_region__entry_Default();
		}
		
		/* 'default' enter sequence for region r1 */
		private void enterSequence_main_region_stopWatch_r1_default() {
			react_main_region_stopWatch_r1__entry_Default();
		}
		
		/* 'default' enter sequence for region r1 */
		private void enterSequence_main_region_stopWatch_r1_timerRunning_r1_default() {
			react_main_region_stopWatch_r1_timerRunning_r1__entry_Default();
		}
		
		/* 'default' enter sequence for region r2 */
		private void enterSequence_main_region_stopWatch_r1_timerRunning_r2_default() {
			react_main_region_stopWatch_r1_timerRunning_r2__entry_Default();
		}
		
		/* 'default' enter sequence for region r2 */
		private void enterSequence_main_region_stopWatch_r2_default() {
			react_main_region_stopWatch_r2__entry_Default();
		}
		
		/* Default exit sequence for state RESET */
		private void exitSequence_main_region_stopWatch_r1_RESET() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for state STOP */
		private void exitSequence_main_region_stopWatch_r1_STOP() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for state timerRunning */
		private void exitSequence_main_region_stopWatch_r1_timerRunning() {
			exitSequence_main_region_stopWatch_r1_timerRunning_r1();
			exitSequence_main_region_stopWatch_r1_timerRunning_r2();
		}
		
		/* Default exit sequence for state RUNNING */
		private void exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for state PAUSED */
		private void exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for state COUNTING */
		private void exitSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING() {
			nextStateIndex = 1;
			stateVector[1] = State.$NullState$;
			
			exitAction_main_region_stopWatch_r1_timerRunning_r2_COUNTING();
		}
		
		/* Default exit sequence for state IDLE */
		private void exitSequence_main_region_stopWatch_r2_IDLE() {
			nextStateIndex = 2;
			stateVector[2] = State.$NullState$;
		}
		
		/* Default exit sequence for state PRESSED */
		private void exitSequence_main_region_stopWatch_r2_PRESSED() {
			nextStateIndex = 2;
			stateVector[2] = State.$NullState$;
			
			exitAction_main_region_stopWatch_r2_PRESSED();
		}
		
		/* Default exit sequence for region main region */
		private void exitSequence_main_region() {
			switch (stateVector[0]) {
			case main_region_stopWatch_r1_RESET:
				exitSequence_main_region_stopWatch_r1_RESET();
				break;
			case main_region_stopWatch_r1_STOP:
				exitSequence_main_region_stopWatch_r1_STOP();
				break;
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING();
				break;
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED();
				break;
			default:
				break;
			}
			
			switch (stateVector[1]) {
			case main_region_stopWatch_r1_timerRunning_r2_COUNTING:
				exitSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING();
				break;
			default:
				break;
			}
			
			switch (stateVector[2]) {
			case main_region_stopWatch_r2_IDLE:
				exitSequence_main_region_stopWatch_r2_IDLE();
				break;
			case main_region_stopWatch_r2_PRESSED:
				exitSequence_main_region_stopWatch_r2_PRESSED();
				break;
			default:
				break;
			}
		}
		
		/* Default exit sequence for region r1 */
		private void exitSequence_main_region_stopWatch_r1() {
			switch (stateVector[0]) {
			case main_region_stopWatch_r1_RESET:
				exitSequence_main_region_stopWatch_r1_RESET();
				break;
			case main_region_stopWatch_r1_STOP:
				exitSequence_main_region_stopWatch_r1_STOP();
				break;
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING();
				break;
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED();
				break;
			default:
				break;
			}
			
			switch (stateVector[1]) {
			case main_region_stopWatch_r1_timerRunning_r2_COUNTING:
				exitSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING();
				break;
			default:
				break;
			}
		}
		
		/* Default exit sequence for region r1 */
		private void exitSequence_main_region_stopWatch_r1_timerRunning_r1() {
			switch (stateVector[0]) {
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING();
				break;
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED();
				break;
			default:
				break;
			}
		}
		
		/* Default exit sequence for region r1 */
		private void exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1() {
			switch (stateVector[0]) {
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING();
				break;
			case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED:
				exitSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED();
				break;
			default:
				break;
			}
		}
		
		/* Default exit sequence for region r2 */
		private void exitSequence_main_region_stopWatch_r1_timerRunning_r2() {
			switch (stateVector[1]) {
			case main_region_stopWatch_r1_timerRunning_r2_COUNTING:
				exitSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING();
				break;
			default:
				break;
			}
		}
		
		/* Default exit sequence for region r2 */
		private void exitSequence_main_region_stopWatch_r2() {
			switch (stateVector[2]) {
			case main_region_stopWatch_r2_IDLE:
				exitSequence_main_region_stopWatch_r2_IDLE();
				break;
			case main_region_stopWatch_r2_PRESSED:
				exitSequence_main_region_stopWatch_r2_PRESSED();
				break;
			default:
				break;
			}
		}
		
		/* The reactions of state RESET. */
		private void react_main_region_stopWatch_r1_RESET() {
			if (check_main_region_stopWatch_r1_RESET_tr0_tr0()) {
				effect_main_region_stopWatch_r1_RESET_tr0();
			}
		}
		
		/* The reactions of state STOP. */
		private void react_main_region_stopWatch_r1_STOP() {
			if (check_main_region_stopWatch_r1_STOP_tr0_tr0()) {
				effect_main_region_stopWatch_r1_STOP_tr0();
			}
		}
		
		/* The reactions of state RUNNING. */
		private void react_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING() {
			if (check_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr0_tr0()) {
				effect_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr0();
			} else {
				if (check_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr1_tr1()) {
					effect_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_tr1();
				}
			}
		}
		
		/* The reactions of state PAUSED. */
		private void react_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED() {
			if (check_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED_tr0_tr0()) {
				effect_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED_tr0();
			}
		}
		
		/* The reactions of state COUNTING. */
		private void react_main_region_stopWatch_r1_timerRunning_r2_COUNTING() {
			if (check_main_region_stopWatch_r1_timerRunning_r2_COUNTING_tr0_tr0()) {
				effect_main_region_stopWatch_r1_timerRunning_r2_COUNTING_tr0();
			} else {
				if (check_main_region_stopWatch_r1_timerRunning_tr0_tr0()) {
					effect_main_region_stopWatch_r1_timerRunning_tr0();
				}
			}
		}
		
		/* The reactions of state IDLE. */
		private void react_main_region_stopWatch_r2_IDLE() {
			if (check_main_region_stopWatch_r2_IDLE_tr0_tr0()) {
				effect_main_region_stopWatch_r2_IDLE_tr0();
			}
		}
		
		/* The reactions of state PRESSED. */
		private void react_main_region_stopWatch_r2_PRESSED() {
			if (check_main_region_stopWatch_r2_PRESSED_tr0_tr0()) {
				effect_main_region_stopWatch_r2_PRESSED_tr0();
			} else {
				if (check_main_region_stopWatch_r2_PRESSED_tr1_tr1()) {
					effect_main_region_stopWatch_r2_PRESSED_tr1();
				}
			}
		}
		
		/* Default react sequence for initial entry  */
		private void react_main_region__entry_Default() {
			enterSequence_main_region_stopWatch_default();
		}
		
		/* Default react sequence for initial entry  */
		private void react_main_region_stopWatch_r1__entry_Default() {
			enterSequence_main_region_stopWatch_r1_RESET_default();
		}
		
		/* Default react sequence for initial entry  */
		private void react_main_region_stopWatch_r1_timerRunning_r1__entry_Default() {
			enterSequence_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING_default();
		}
		
		/* Default react sequence for initial entry  */
		private void react_main_region_stopWatch_r1_timerRunning_r2__entry_Default() {
			enterSequence_main_region_stopWatch_r1_timerRunning_r2_COUNTING_default();
		}
		
		/* Default react sequence for initial entry  */
		private void react_main_region_stopWatch_r2__entry_Default() {
			enterSequence_main_region_stopWatch_r2_IDLE_default();
		}
		
		public void runCycle() {
			if (!initialized)
				throw new IllegalStateException(
						"The state machine needs to be initialized first by calling the init() function.");
			clearOutEvents();
			for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
				switch (stateVector[nextStateIndex]) {
				case main_region_stopWatch_r1_RESET:
					react_main_region_stopWatch_r1_RESET();
					break;
				case main_region_stopWatch_r1_STOP:
					react_main_region_stopWatch_r1_STOP();
					break;
				case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING:
					react_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_RUNNING();
					break;
				case main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED:
					react_main_region_stopWatch_r1_timerRunning_r1_timerRunning_r1_PAUSED();
					break;
				case main_region_stopWatch_r1_timerRunning_r2_COUNTING:
					react_main_region_stopWatch_r1_timerRunning_r2_COUNTING();
					break;
				case main_region_stopWatch_r2_IDLE:
					react_main_region_stopWatch_r2_IDLE();
					break;
				case main_region_stopWatch_r2_PRESSED:
					react_main_region_stopWatch_r2_PRESSED();
					break;
				default:
					// $NullState$
				}
			}
			clearEvents();
		}
		
}
