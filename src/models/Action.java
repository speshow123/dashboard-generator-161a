package models;
import java.util.*;

/**
 * 
 */
public class Action {
	
	/**
     * 
     */
    private String actionID;

    /**
     * 
     */
    private String actionName;

    /**
     * 
     */
    private List<Parameter> paramsList;

    /**
     * 
     */
    private List<Action> subActionList;


    
    /**
     * Default constructor
     */
    public Action() {
    }



	public String getActionID() {
		return actionID;
	}



	public void setActionID(String actionID) {
		this.actionID = actionID;
	}



	public String getActionName() {
		return actionName;
	}



	public void setActionName(String actionName) {
		this.actionName = actionName;
	}



	public List<Parameter> getParamsList() {
		return paramsList;
	}



	public void setParamsList(List<Parameter> paramsList) {
		this.paramsList = paramsList;
	}



	public List<Action> getSubActionList() {
		return subActionList;
	}



	public void setSubActionList(List<Action> subActionList) {
		this.subActionList = subActionList;
	}

    



}