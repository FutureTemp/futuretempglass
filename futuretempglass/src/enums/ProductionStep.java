package enums;

public enum ProductionSteps{

	TEMPER("Tempering"), POLISH("Polishing"), SANDBLAST("Sandblasting");

	private String stepName;

	private ProductionSteps(String stepName)
	{
		this.stepName = stepName;
	}

	public String getStepName()
	{
		return stepName;
	}
}
