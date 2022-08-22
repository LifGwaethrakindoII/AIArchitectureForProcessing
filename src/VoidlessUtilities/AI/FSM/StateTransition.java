package VoidlessUtilities.AI.FSM;

import java.util.function.*;

public class StateTransition<T extends IFiniteStateAgent<T>>
{
	private Predicate<T> condition;
	private IState<T> state;
	
	public Predicate<T> condition() { return condition; }
	public void condition(Predicate<T> _condition) { condition = _condition; }
	
	public IState<T> state() { return state; }
	public void state(IState<T> _state) { state = _state; }
	
	public StateTransition(Predicate<T> _condition, IState<T> _state)
	{
		condition = _condition;
		state = _state;
	}
	
	public boolean TransitionEvaluation(T _agent)
	{
		return condition.test(_agent);
	}
}
