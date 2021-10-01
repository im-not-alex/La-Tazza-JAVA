package it.polito.latazza.data;

public class Beverage {
	private Integer beverageId;
	private String name;
	private Integer price;
	private Integer quantity=0;
	private Integer cpb;
	private Integer boxprice;
	
	public Integer getBeverageId() {
		return beverageId;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void addQuantity(Integer quantity) {
		this.quantity += quantity;
	}
	public Integer getBoxprice() {
		return boxprice;
	}

	public Integer getCpb() {
		return cpb;
	}
	public void setCpb(Integer cpb) {
		this.cpb = cpb;
	}
	public void setBoxprice(Integer boxprice) {
		this.boxprice = boxprice;
	}
	public void setPrice() {
		this.price = (int) Math.ceil(boxprice/cpb);
		if(this.price==0 && boxprice != 0)
			this.price=1;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*public void setBeverageId(Integer beverageId) {
		this.beverageId = beverageId;
	}*/
	public Beverage(Integer beverageId, String name,Integer price, Integer quantity, Integer cpb, Integer boxprice) {
		super();
		this.beverageId = beverageId;
		this.name = name;
		this.price = price;
		this.quantity=quantity;
		this.cpb = cpb;
		this.boxprice = boxprice;
	}
	public Beverage(Integer beverageId, String name,Integer cpb, Integer boxprice) {
		super();
		this.beverageId = beverageId;
		this.name = name;
		this.cpb = cpb;
		this.boxprice = boxprice;
		this.setPrice();

	}
	

}
