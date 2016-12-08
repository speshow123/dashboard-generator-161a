package models;

import controllers.ModelGeneration;

public interface Model {

	public void toHtml(ModelGeneration generation, String directoryPath);
}