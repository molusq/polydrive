	package stopwatch_v2.defaultsm;
	
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
			}
			public void clearOutEvents() {
			//nothings to do with the newly introduced Event Type
			}
			
		}
		
		protected SCInterfaceImpl sCInterface;
		
		private boolean initialized = false;
		
		public enum State {
			main_region_RESET,
			main_region_STOP,
			main_region_timerRunning,
			main_region_timerRunning_r1_RUNNING,
			main_region_timerRunning_r1_PAUSED,
			$NullState$
		};
		
		private final State[] stateVector = new State[1];
		
		private int nextStateIndex;
		
		public DefaultSMStatemachine() {
			sCInterface = new SCInterfaceImpl();
		}
		
		public void init() {
			this.initialized = true;
			for (int i = 0; i < 1; i++) {
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
			enterSequence_main_region_default();
		}
		
		public void exit() {
			exitSequence_main_region();
		}
		
		/**
		 * @see IStatemachine#isActive()
		 */
		public boolean isActive() {
			return stateVector[0] != State.$NullState$;
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
			case main_region_RESET:
				return stateVector[0] == State.main_region_RESET;
			case main_region_STOP:
				return stateVector[0] == State.main_region_STOP;
			case main_region_timerRunning:
				return stateVector[0].ordinal() >= State.
						main_region_timerRunning.ordinal()&& stateVector[0].ordinal() <= State.main_region_timerRunning_r1_PAUSED.ordinal();
			case main_region_timerRunning_r1_RUNNING:
				return stateVector[0] == State.main_region_timerRunning_r1_RUNNING;
			case main_region_timerRunning_r1_PAUSED:
				return stateVector[0] == State.main_region_timerRunning_r1_PAUSED;
			default:
				return false;
			}
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
		
		
		
		
		
		
		
		
		public long getI() {
			return sCInterface.getI();
		}
		
		public void setI(long value) {
			sCInterface.setI(value);
		}
		
		private boolean check_main_region_RESET_tr0_tr0() {
			return sCInterface.leftButton;
		}
		
		private boolean check_main_region_STOP_tr0_tr0() {
			return sCInterface.leftButton;
		}
		
		private boolean check_main_region_timerRunning_tr0_tr0() {
			return sCInterface.leftButton;
		}
		
		private boolean check_main_region_timerRunning_r1_RUNNING_tr0_tr0() {
			return sCInterface.rightButton;
		}
		
		private boolean check_main_region_timerRunning_r1_PAUSED_tr0_tr0() {
			return sCInterface.rightButton;
		}
		
		private void effect_main_region_RESET_tr0() {
			exitSequence_main_region_RESET();
			sCInterface.raiseStartPlaying();
			
			enterSequence_main_region_timerRunning_r1_RUNNING_default();
		}
		
		private void effect_main_region_STOP_tr0() {
			exitSequence_main_region_STOP();
			sCInterface.raiseReset();
			
			enterSequence_main_region_RESET_default();
		}
		
		private void effect_main_region_timerRunning_tr0() {
			exitSequence_main_region_timerRunning();
			sCInterface.raiseStartStop();
			
			enterSequence_main_region_STOP_default();
		}
		
		private void effect_main_region_timerRunning_r1_RUNNING_tr0() {
			exitSequence_main_region_timerRunning_r1_RUNNING();
			sCInterface.raiseStartPause();
			
			enterSequence_main_region_timerRunning_r1_PAUSED_default();
		}
		
		private void effect_main_region_timerRunning_r1_PAUSED_tr0() {
			exitSequence_main_region_timerRunning_r1_PAUSED();
			sCInterface.raiseResumePlaying();
			
			enterSequence_main_region_timerRunning_r1_RUNNING_default();
		}
		
		/* 'default' enter sequence for state RESET */
		private void enterSequence_main_region_RESET_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_RESET;
		}
		
		/* 'default' enter sequence for state STOP */
		private void enterSequence_main_region_STOP_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_STOP;
		}
		
		/* 'default' enter sequence for state RUNNING */
		private void enterSequence_main_region_timerRunning_r1_RUNNING_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_timerRunning_r1_RUNNING;
		}
		
		/* 'default' enter sequence for state PAUSED */
		private void enterSequence_main_region_timerRunning_r1_PAUSED_default() {
			nextStateIndex = 0;
			stateVector[0] = State.main_region_timerRunning_r1_PAUSED;
		}
		
		/* 'default' enter sequence for region main region */
		private void enterSequence_main_region_default() {
			react_main_region__entry_Default();
		}
		
		/* Default exit sequence for state RESET */
		private void exitSequence_main_region_RESET() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for state STOP */
		private void exitSequence_main_region_STOP() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for state timerRunning */
		private void exitSequence_main_region_timerRunning() {
			exitSequence_main_region_timerRunning_r1();
		}
		
		/* Default exit sequence for state RUNNING */
		private void exitSequence_main_region_timerRunning_r1_RUNNING() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for state PAUSED */
		private void exitSequence_main_region_timerRunning_r1_PAUSED() {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;
		}
		
		/* Default exit sequence for region main region */
		private void exitSequence_main_region() {
			switch (stateVector[0]) {
			case main_region_RESET:
				exitSequence_main_region_RESET();
				break;
			case main_region_STOP:
				exitSequence_main_region_STOP();
				break;
			case main_region_timerRunning_r1_RUNNING:
				exitSequence_main_region_timerRunning_r1_RUNNING();
				break;
			case main_region_timerRunning_r1_PAUSED:
				exitSequence_main_region_timerRunning_r1_PAUSED();
				break;
			default:
				break;
			}
		}
		
		/* Default exit sequence for region r1 */
		private void exitSequence_main_region_timerRunning_r1() {
			switch (stateVector[0]) {
			case main_region_timerRunning_r1_RUNNING:
				exitSequence_main_region_timerRunning_r1_RUNNING();
				break;
			case main_region_timerRunning_r1_PAUSED:
				exitSequence_main_region_timerRunning_r1_PAUSED();
				break;
			default:
				break;
			}
		}
		
		/* The reactions of state RESET. */
		private void react_main_region_RESET() {
			if (check_main_region_RESET_tr0_tr0()) {
				effect_main_region_RESET_tr0();
			}
		}
		
		/* The reactions of state STOP. */
		private void react_main_region_STOP() {
			if (check_main_region_STOP_tr0_tr0()) {
				effect_main_region_STOP_tr0();
			}
		}
		
		/* The reactions of state RUNNING. */
		private void react_main_region_timerRunning_r1_RUNNING() {
			if (check_main_region_timerRunning_r1_RUNNING_tr0_tr0()) {
				effect_main_region_timerRunning_r1_RUNNING_tr0();
			} else {
				if (check_main_region_timerRunning_tr0_tr0()) {
					effect_main_region_timerRunning_tr0();
				}
			}
		}
		
		/* The reactions of state PAUSED. */
		private void react_main_region_timerRunning_r1_PAUSED() {
			if (check_main_region_timerRunning_r1_PAUSED_tr0_tr0()) {
				effect_main_region_timerRunning_r1_PAUSED_tr0();
			} else {
				if (check_main_region_timerRunning_tr0_tr0()) {
					effect_main_region_timerRunning_tr0();
				}
			}
		}
		
		/* Default react sequence for initial entry  */
		private void react_main_region__entry_Default() {
			enterSequence_main_region_RESET_default();
		}
		
		public void runCycle() {
			if (!initialized)
				throw new IllegalStateException(
						"The state machine needs to be initialized first by calling the init() function.");
			clearOutEvents();
			for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
				switch (stateVector[nextStateIndex]) {
				case main_region_RESET:
					react_main_region_RESET();
					break;
				case main_region_STOP:
					react_main_region_STOP();
					break;
				case main_region_timerRunning_r1_RUNNING:
					react_main_region_timerRunning_r1_RUNNING();
					break;
				case main_region_timerRunning_r1_PAUSED:
					react_main_region_timerRunning_r1_PAUSED();
					break;
				default:
					// $NullState$
				}
			}
			clearEvents();
		}
		
}
