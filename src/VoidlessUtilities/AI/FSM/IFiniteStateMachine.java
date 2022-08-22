package VoidlessUtilities.AI.FSM;

public interface IFiniteStateMachine<T>
{
	T state();
	void state(T _state);
	
	T previousState();
	void previousState(T _state);
	
	void OnEnterState(T _state);
	
	void OnExitState(T _state);
	
	default void ChangeState(T _state)
	{
		previousState(state());
		if(previousState() != null) OnExitState(previousState());
		state(_state);
		if(state() != null)OnEnterState(state());
	}
	
	default void ReturnToPreviousState(T _state)
	{
		if(state() != null) OnExitState(state());
		state(previousState());
		previousState(state());
		if(state() != null) OnEnterState(state());
		
	}
}
