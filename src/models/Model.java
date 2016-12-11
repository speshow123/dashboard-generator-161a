package models;

import controllers.ModelGeneration;

public interface Model {

	void toHtml(ModelGeneration generation, String directoryPath);
}