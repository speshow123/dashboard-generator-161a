package models;
import java.util.*;

/**
 * 
 */
public class Action {
	
	/**
     * 
     */
    private String actionKind;
    
    public String getActionKind() {
		return actionKind;
	}


	public void setActionKind(String actionKind) {
		this.actionKind = actionKind;
	}


	public Operation getOperation() {
		return operation;
	}


	public void setOperation(Operation operation) {
		this.operation = operation;
	}


	public List<Operation> getSubOperationList() {
		return subOperationList;
	}


	public void setSubOperation(Operation subOperation) {
		this.subOperationList.add(subOperation);
	}


	private Operation operation;
    
    private List<Operation> subOperationList;

    
    /**
     * Default constructor
     */
    public Action() {
    	subOperationList = new ArrayList<>();
    }

}