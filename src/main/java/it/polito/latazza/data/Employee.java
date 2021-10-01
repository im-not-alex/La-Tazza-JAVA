package it.polito.latazza.data;

import java.util.ArrayList;
import java.util.List;

public class Employee{
	private Integer employeeId;
	private String name;
	private String surname;
	private Integer balance=0;
	private List<Recharge> recharge;
	private Integer rid=0;

	public Integer getEmployeeId() {
		return employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public void addBalance(Integer balance) {
		this.balance += balance;
	}
	public List<Recharge> getRecharge() {
		return recharge;
	}

	public String getDenom() {
		return this.name+ " " +this.surname;
	}
	public Employee(Integer employeeId, String name, String surname) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.surname = surname;
		this.balance=0;
		this.recharge=new ArrayList<Recharge>();
	}
	public Integer getRid() {
		return rid++;
	}
	public Employee(Integer employeeId, String name, String surname,Integer balance) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.surname = surname;
		this.rid=0;
		this.balance=balance;
		this.recharge=new ArrayList<Recharge>();
	}
	/*public void setRecharge(List<Recharge> recharge) {
	this.recharge = recharge;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}*/
}