package it.polito.latazza.data;

public class Consumption {
	private Integer id;
	private Employee e=null;
	private Beverage b;
	private Integer nofcap;
	private boolean fromA=false;
	private Transaction t;
	
	public Transaction getT() {
		return t;
	}
	public Employee getE() {
		return e;
	}
	/*public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNofcap() {
		return nofcap;
	}
	public void setNofcap(Integer nofcap) {
		this.nofcap = nofcap;
	}
	public boolean isFromA() {
		return fromA;
	}
	public void setFromA(boolean fromA) {
		this.fromA = fromA;
	}

	public void setT(Transaction t) {
		this.t = t;
	}

	public void setE(Employee e) {
		this.e = e;
	}
	public Beverage getB() {
		return b;
	}
	public void setB(Beverage b) {
		this.b = b;
	}*/
	public Consumption(Integer id, Employee e, Beverage b, Integer nofcap, boolean fromA, Transaction t) {
		super();
		this.id = id;
		this.e = e;
		this.b = b;
		this.nofcap = nofcap;
		this.fromA = fromA;
		this.t = t;
	}
	public Consumption(Integer id, Beverage b, Integer nofcap, Transaction t) {
		super();
		this.id = id;
		this.b = b;
		this.nofcap = nofcap;
		this.t = t;
		this.e=null;
		this.fromA=false;
	}
	@Override
	public String toString(){
		if(e!=null)
		{
			if(fromA)
				return t.getDateFormat()+" BALANCE "+e.getDenom()+" "+b.getName()+" "+nofcap;
			else
				return t.getDateFormat()+" CASH "+e.getDenom()+" "+b.getName()+" "+nofcap;
		}
		return t.getDateFormat()+" VISITOR "+b.getName()+" "+nofcap;
		
	}	
	
}
