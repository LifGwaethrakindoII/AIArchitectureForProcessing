package VoidlessUtilities.AI.FSM;

public interface IFiniteStateAgent<T extends IFiniteStateAgent<T>>
{
	IState<T> state();
	
	void state(IState<T> _state);
	
	IState<T> previousState();
	
	void previousState(IState<T> _previousState);
}
