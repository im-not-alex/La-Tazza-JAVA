package it.polito.latazza.data;

public class BoxPurchase{
	private Beverage b;
	private Integer boxquantity;
	private Transaction t;
	private Integer remcaps;
	private Integer price;
public BoxPurchase(Beverage b, Integer boxquantity, Transaction t) {
		super();
		this.b = b;
		this.boxquantity = boxquantity;
		this.t = t;
	}
	public BoxPurchase(Beverage b, Integer boxquantity, Transaction t, Integer remcaps,Integer price) {
		super();
		this.b = b;
		this.boxquantity = boxquantity;
		this.t = t;
		this.remcaps = remcaps;
		this.price=price;
	}
	@Override
	public String toString(){
		return t.getDateFormat()+" BUY "+b.getName()+" "+boxquantity;
	}
	public Integer getRemcaps() {
		return remcaps;
	}
	public void setRemcaps(Integer remcaps) {
		if(remcaps==0)
			this.remcaps=0;
		else this.remcaps -= remcaps;
	}
	
	public Beverage getBid() {
	return b;
	}
	/*
	public void setBid(Beverage b) {
		this.b = b;
	}
	public Integer getBoxquantity() {
		return boxquantity;
	}
	public void setBoxquantity(Integer boxquantity) {
		this.boxquantity = boxquantity;
	}
	public Transaction getT() {
		return t;
	}
	public void setT(Transaction t) {
		this.t = t;
	}
	@Override
	public int compareTo(Object o) {
		return this.getT().getD().compareTo(((BoxPurchase)o).getT().getD());
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	*/
	public Integer getPrice() {
		return price;
	}

}
