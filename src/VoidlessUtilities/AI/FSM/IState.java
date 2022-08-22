package VoidlessUtilities.AI.FSM;

import java.util.*;

public interface IState<T extends IFiniteStateAgent<T>> extends IFiniteStateAgent<T>
{
	ArrayList<StateTransition<T>> transitions();
	
	void transitions(ArrayList<StateTransition<T>> _transitions);
	
	void OnEnterState(T _agent);
	
	void OnExecuteState(T _agent);
	
	void OnExitState(T _agent);
}