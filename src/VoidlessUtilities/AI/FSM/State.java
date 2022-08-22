package VoidlessUtilities.AI.FSM;

import java.util.*;

public abstract class State<T extends IFiniteStateAgent<T>> implements IState<T>
{
	private IState<T> state;
	private IState<T> previousState;
	private ArrayList<StateTransition<T>> transitions;
	
	@Override
	public IState<T> state() { return state; }

	@Override
	public void state(IState<T> _state) { state = _state; }

	@Override
	public IState<T> previousState() { return previousState; }

	@Override
	public void previousState(IState<T> _previousState) { previousState = _previousState; }
	
	@Override
	public ArrayList<StateTransition<T>> transitions() { return transitions; }

	@Override
	public void transitions(ArrayList<StateTransition<T>> _transitions) { transitions = _transitions; }

	public State(StateTransition<T> ... _transitions)
	{
		transitions = new ArrayList<StateTransition<T>>(Arrays.asList(_transitions));
	}
	
	@Override
	public abstract void OnEnterState(T _agent);

	@Override
	public abstract void OnExecuteState(T _agent);

	@Override
	public abstract void OnExitState(T _agent);
}
