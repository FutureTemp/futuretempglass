package framework;

public abstract class View implements Screen{

	private Data data;

	public View(Data data)
	{
		this.data = data;
	}

	public View()
	{
		this(new Data());
	}

	protected Object getData(String name)
	{
		return data.getData(name);
	}

	protected void addData(String name, Object value)
	{
		data.addData(name, value);
	}
}
