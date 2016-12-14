package models;

import java.util.Map;
import java.util.HashMap;

public class Operation {
	private String idOperation;
	private String nameOperation;
	private Map<Parameter, Value> argsList;
	
	public Operation() {
		argsList = new HashMap<>();
	}

	public String getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(String idOperation) {
		this.idOperation = idOperation;
	}

	public String getNameOperation() {
		return nameOperation;
	}

	public void setNameOperation(String nameOperation) {
		this.nameOperation = nameOperation;
	}

	public Map<Parameter, Value> getArgsList() {
		return argsList;
	}

	public void setParameter(Parameter param) {
		argsList.put(param, null);
	}
	public Value setValue(String paramId, Value val) {

		for(Parameter param: argsList.keySet()) {
			if(param.getIdParam().equals(paramId))
				return argsList.put(param, val);
		}
		return null;
	}
	
	
}
