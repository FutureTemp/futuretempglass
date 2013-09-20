package enums;

import framework.utils.StringUtils;

public enum ProductionStep{

	CUT("Cutting"), TEMPER("Tempering"), POLISH("Polishing"), SANDBLAST("Sandblasting");

	private String stepName;

	private ProductionStep(String stepName)
	{
		this.stepName = stepName;
	}

	public String getStepName()
	{
		return stepName;
	}

	public static ProductionStep getProductionStep(String name)
	{
		for(ProductionStep step: ProductionStep.values())
		{
			if(StringUtils.equalsIgnoreCase(step.getStepName(), name))
			{
				return step;
			}
		}
		return null;
	}
}
