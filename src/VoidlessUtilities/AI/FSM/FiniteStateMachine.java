package VoidlessUtilities.AI.FSM;

public class FiniteStateMachine<T extends IFiniteStateAgent<T>>
{
	private IState<T> globalState;
	private IState<T> previousGlobalState;
	
	public IState<T> globalState() { return globalState; }
	
	public IState<T> previousGlobalState() { return previousGlobalState; }
	
	public FiniteStateMachine(IState<T> _globalState)
	{
		globalState = _globalState;
	}
	
	public void ChangeGlobalState(IState<T> _state)
	{		
		previousGlobalState = globalState;
		globalState = _state;
	}
	
	public void ChangeState(T _agent, IState<T> _state)
	{
		IState<T> state = null;
		_agent.previousState(_agent.state());
		_agent.state(_state);
		
		/// Exit recursively all states from the previous state
		if(_agent.previousState() != null)
		{
			state = _agent.previousState();
			while(state != null)
			{
				state.OnExitState(_agent);
				state = state.state();
			}
		}
			
		_agent.state().OnEnterState(_agent);
		
		state = _agent.state();
		
		/// Enter recursively states of the new state
		while(state != null)
		{
			state.OnEnterState(_agent);
			state = state.state();
		}
	}
	
	public void ResetState(T _agent)
	{
		ChangeState(_agent, _agent.state());
	}
	
	public void RevertState(T _agent, IState<T> _state)
	{
		if(_agent.previousState() != null)
		{
			IState<T> newState = _agent.previousState();
			IState<T> state = _agent.state();
			
			while(state != null)
			{
				state.OnExitState(_agent);
				state = state.state();
			}
			
			_agent.previousState(_agent.state());
			_agent.state(newState);
			
			state = _agent.state();
			
			while(state != null)
			{
				state.OnEnterState(_agent);
				state = state.state();
			}
		}
	}
	
	public void UpdateFSM(T _agent)
	{	
		IState<T> state = _agent.state();
		
		while(state != null)
		{
			state.OnExecuteState(_agent);
			
			for(StateTransition<T> transition : state.transitions())
			{
				if(transition.TransitionEvaluation(_agent))
				{
					ChangeState(_agent, transition.state());
					state = null;
					break;
				}
			}
			
			if(state != null) state = state.state();
		}
	}
}
