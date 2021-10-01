package it.polito.latazza.data;

import java.util.Locale;

public class Recharge {
	private Integer id;
	private Employee e;
	private Transaction t;
	private Double tdvalue;
	
	public Transaction getT() {
		return t;
	}
	public Recharge(Integer id, Employee e, Transaction t) {
		super();
		this.id = id;
		this.e = e;
		this.t = t;
		this.tdvalue=(double) (t.getTotal()/100);
	}
	@Override
	public String toString(){
		return t.getDateFormat()+" RECHARGE "+e.getDenom()+" "+String.format(Locale.US, "%.2f", tdvalue)+" \u20ac";
	}
    /*public Integer getId() {
	return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee getE() {
		return e;
	}
	public void setE(Employee e) {
		this.e = e;
	}
	
	public void setT(Transaction t) {
		this.t = t;
	}
	*/
}
	
