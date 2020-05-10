interface PayProcess{
	double calculate();
}

class Hourly implements PayProcess{
	private double hourlyRate;
	private int id;

	Hourly(double hourlyRate, int id){
		this.hourlyRate = hourlyRate;
		this.id = id;
	}

	public double get_hourly_rate()
	{
		return hourlyRate;
	}
	public void update_hourly_rate(double hourlyRate)
	{
		this.hourlyRate = hourlyRate;
	}
	public double calculate()
	{
		int hours = 0;
		//Query to calculate hours from DB
		return hours*hourlyRate;
	}
}

class Monthly implements PayProcess{
	private double monthlyRate;
	private int id;

	Monthly(double monthlyRate, int id){
		this.monthlyRate = monthlyRate;
		this.id = id;
	}

	public double get_monthly_rate()
	{
		return monthlyRate;
	}
	public void update_monthly_rate(double monthlyRate)
	{
		this.monthlyRate = monthlyRate;
	}
	public double calculate()
	{
		return monthlyRate;
	}
}

class commission implements PayProcess{
	private double commissionRate;
	private int id;

	commission(double commissionRate, int id){
		this.commissionRate = commissionRate;
		this.id = id;
	}

	public double get_commission_rate()
	{
		return commissionRate;
	}
	public void update_commission_rate(double commissionRate)
	{
		this.commissionRate = commissionRate;
	}
	public double calculate()
	{
		double sales = 0;
		//Query to calculate sales from DB
		return (sales*commissionRate/100);
	}
}

class unionCharges implements PayProcess{
	private int id;

	unionCharges(int id){
		this.id = id;
	}

	public double calculate()
	{
		double dues = 0;
		//Query to calculate union dues
		return dues;
	}
}