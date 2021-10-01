package it.polito.latazza.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class DataImpl implements DataInterface {

	private Integer balance = 0;
	private Integer eid = 0;
	private Integer bid = 0;
	private Integer cid = 0;
	private List<BoxPurchase> bpl = new ArrayList<BoxPurchase>();
	private Map<Integer, Employee> em = new TreeMap<Integer, Employee>();
	private Map<Integer, Beverage> bev = new TreeMap<Integer, Beverage>();
	private Map<Integer, Consumption> cons = new TreeMap<Integer, Consumption>();
	private static enum USE{
		ADD_EMPLOYEE,
		UPDATE_EMPLOYEE,
		ADD_BEVERAGE,
		UPDATE_BEVERAGE,
		UPDATE_BALANCE,
		ADD_RECHARGE,
		ADD_BOXPURCH,
		UPD_BOXPURCH,
		ADD_CONS,
		RESET
	};
	private Connection con;

	private void serverconnect() throws ClassNotFoundException, SQLException {
		Class.forName (driver);
		con=DriverManager.getConnection(url,user,pass);
		
		retrivedata();

	}

	private void retrivedata() throws SQLException
	{
		Statement st=con.createStatement();
		String query;
		st.executeUpdate(initquery);
		query="SELECT * FROM EMPLOYEES;";
		ResultSet rs = st.executeQuery(query);
		Integer id=-1,balance,price,quantity,cpb,boxprice,total,idempl,idbev;
		String name,surname;
		Timestamp d;
		while(rs.next())
		{
			id =rs.getInt("id");
			balance =rs.getInt("balance");
			if(id==-1) {
				this.balance=balance;
				continue;
			}
			name = rs.getString("name");
			surname = rs.getString("surname");
			em.put(id, new Employee(id,name,surname,balance));
		}
		query="SELECT * FROM BEVERAGES;";
		rs = st.executeQuery(query);
		while(rs.next())
		{
			id =rs.getInt("id");
			name = rs.getString("name");
			price =rs.getInt("price");				
			quantity =rs.getInt("quantity");
			cpb =rs.getInt("cpb");
			boxprice =rs.getInt("boxprice");
			bev.put(id, new Beverage(id,name,price,quantity,cpb,boxprice));
		}
		query="SELECT * FROM BOXPURCH;";
		rs = st.executeQuery(query);
		Integer remcaps;
		while(rs.next())
		{
			id =rs.getInt("idbev");
			total =rs.getInt("total");				
			quantity =rs.getInt("quantity");
			d=rs.getTimestamp("timest");
			remcaps = rs.getInt("remcaps");
			price=rs.getInt("price");
			bpl.add(new BoxPurchase(bev.get(id),quantity,new Transaction(total,d), remcaps,price));
		}
		query="SELECT * FROM CONSUMPTIONS;";
		rs = st.executeQuery(query);
		while(rs.next())
		{
			id =rs.getInt("id");
			idempl =rs.getInt("idempl");
			idbev =rs.getInt("idbev");
			total =rs.getInt("total");				
			quantity =rs.getInt("quantity");
			d=rs.getTimestamp("timest");
			boolean fromA=rs.getBoolean("fromA");
			if(!idempl.equals(-1))
				cons.put(id, new Consumption(id,em.get(idempl),bev.get(idbev),quantity,fromA,new Transaction(total,d)));
			else cons.put(id, new Consumption(id,bev.get(idbev),quantity,new Transaction(total,d)));
		}
		query="SELECT * FROM RECHARGES;";
		rs = st.executeQuery(query);
		while(rs.next())
		{
			id=rs.getInt("idempl");
			Employee e=em.get(id);
			total =rs.getInt("total");				
			d=rs.getTimestamp("timest");
			Recharge r=new Recharge(e.getRid(),e,new Transaction(total,d));
			e.getRecharge().add(r);
		}
		rs.close();
		st.close();		
	}
	private void serverdata(USE c,Object... args) {
			try {
				Statement st=con.createStatement();
				String query = null;

				switch (c) {
				case ADD_EMPLOYEE:
					query= "INSERT INTO EMPLOYEES VALUES ("+args[0]+",'"+args[1]+"','"+args[2]+"',0);";
					break;
				case UPDATE_EMPLOYEE:
					query= "UPDATE EMPLOYEES SET name='"+args[1]+"',surname='"+args[2]+"' WHERE id="+args[0]+";";
					break;
				case ADD_BEVERAGE:
					query= "INSERT INTO BEVERAGES VALUES ("+args[0]+",'"+args[1]+"',"+args[2]+","+args[3]+","+args[4]+","+args[5]+");";
					break;
				case UPDATE_BEVERAGE:
					query= "UPDATE BEVERAGES SET name='"+args[1]+"',price="+args[2]+",cpb="+args[3]+",boxprice="+args[4]+" WHERE id="+args[0]+";";
					break;
				case ADD_RECHARGE:
					query= "INSERT INTO RECHARGES (idempl,total,timest) VALUES ("+args[0]+","+args[1]+",'"+args[2]+"');"
							+ "UPDATE EMPLOYEES SET balance=balance+"+args[1]+"WHERE id=-1 OR id="+args[0]+";";
					break;
				case ADD_CONS:
					int choice=1;
					query= "UPDATE BEVERAGES SET quantity=quantity-"+args[3]+" WHERE id="+args[2]+";";
					if(args[4].equals(true))
						query+="UPDATE EMPLOYEES SET balance=balance-"+args[5]+"WHERE id="+args[1]+";";
					else {
						query+="UPDATE EMPLOYEES SET balance=balance+"+args[5]+"WHERE id=-1;";
						choice=0; }
					query+="INSERT INTO CONSUMPTIONS VALUES ("+args[0]+","+args[1]+","+args[2]+","+args[3]+","+choice+","+args[5]+",'"+args[6]+"');";
					break;
				case ADD_BOXPURCH:
					query= "INSERT INTO BOXPURCH (idbev,quantity,total,timest,remcaps,price) VALUES ("+args[0]+","+args[1]+","+args[2]+",'"+args[3]+ "'," + args[4]+","+args[5]+");"
							+"UPDATE EMPLOYEES SET BALANCE=balance-"+args[2]+" WHERE id=-1;"
							+"UPDATE BEVERAGES SET quantity=quantity+cpb*"+args[1]+" WHERE id="+args[0]+";";
					break;
				case UPD_BOXPURCH:
					query="UPDATE BOXPURCH SET remcaps="+args[1]+" WHERE idbev="+args[0]+";";
					break;
				case RESET:
					query="DROP TABLE EMPLOYEES,BEVERAGES,CONSUMPTIONS,BOXPURCH,RECHARGES;"+initquery;
					break;
				default:
					break;
				}
				st.executeUpdate(query);
				st.close();
			} catch (SQLException e) {

			}
	};
	@Override
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		if (employeeId ==null || employeeId < 0 || !em.containsKey(employeeId))
			throw new EmployeeException("Employee not valid!");
		if (beverageId==null || beverageId < 0 || !bev.containsKey(beverageId))
			throw new BeverageException("Beverage not valid!");
		Beverage b = bev.get(beverageId);
		Employee e = em.get(employeeId);
		if (numberOfCapsules==null || numberOfCapsules <0 || fromAccount==null || numberOfCapsules > b.getQuantity())
			throw new NotEnoughCapsules("Not enough Capsules!");
		if(numberOfCapsules==0)
			return e.getBalance();
		Integer total=Consuming(beverageId,numberOfCapsules);
		
		if (fromAccount) e.addBalance(-total);
		else balance+=total;
		
		Timestamp tsp = new Timestamp(System.currentTimeMillis());
		while(cons.containsKey(this.cid)) this.cid++;
		cons.put(cid, new Consumption(cid, e, b, numberOfCapsules, fromAccount, new
				Transaction(total,tsp)));
		bev.get(beverageId).addQuantity(-numberOfCapsules);
		serverdata(USE.ADD_CONS,cid,employeeId,beverageId,numberOfCapsules,fromAccount,total,tsp);
		cid++;
		return e.getBalance();
	}

	@Override
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		if (beverageId==null || beverageId < 0 || !bev.containsKey(beverageId))
			throw new BeverageException("Beverage not valid!");	
		Beverage b = bev.get(beverageId);
		if (numberOfCapsules==null || numberOfCapsules <0 || numberOfCapsules > b.getQuantity())
			throw new NotEnoughCapsules("Not enough Capsules!");
		if(numberOfCapsules==0)
			return;
		Timestamp tsp = new Timestamp(System.currentTimeMillis());
		Integer total=Consuming(beverageId,numberOfCapsules);
		balance = balance + total;
		while(cons.containsKey(this.cid)) this.cid++;
		cons.put(cid, new Consumption(cid, b, numberOfCapsules, new
				Transaction(total,tsp)));
		bev.get(beverageId).addQuantity(-numberOfCapsules);
		serverdata(USE.ADD_CONS,cid,-1,beverageId,numberOfCapsules,false,total,tsp);
		cid++;
	}
	
	private Integer Consuming(Integer beverageId, Integer numberOfCapsules)
	{
		Integer nc = 0;
		Integer total = 0;
		for(BoxPurchase bp : bpl)
		{
				if(bp.getBid().getBeverageId().equals(beverageId) && bp.getRemcaps().intValue()>0)
				{
					if(numberOfCapsules > bp.getRemcaps()+nc)
					{
						nc += bp.getRemcaps();
						total += bp.getPrice()*bp.getRemcaps();
						bp.setRemcaps(0);
						serverdata(USE.UPD_BOXPURCH,bp.getBid(),0);
					}
					else 
					{
						total+= bp.getPrice()*(numberOfCapsules-nc);
						bp.setRemcaps(numberOfCapsules-nc);
						serverdata(USE.UPD_BOXPURCH,bp.getBid(),numberOfCapsules-nc);
						break;
					}
				}
		}
		return total;
	}
	
	@Override
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		if (id==null ||  id < 0 || !em.containsKey(id) || amountInCents==null)
			throw new EmployeeException("Employee not valid!");
		if (amountInCents <= 0)
			throw new EmployeeException("Amount invalid!");
		Employee e = em.get(id);
		Timestamp tsp = new Timestamp(System.currentTimeMillis());
		e.getRecharge().add(new Recharge(e.getRid(),e,new Transaction(amountInCents,tsp)));
		e.addBalance(amountInCents);
		this.balance = this.balance + amountInCents;
		serverdata(USE.ADD_RECHARGE,id,amountInCents,tsp);
		return e.getBalance();
	}

	@Override
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		if (beverageId==null || beverageId < 0 || !bev.containsKey(beverageId) || boxQuantity==null || boxQuantity<0)
			throw new BeverageException("Beverage not valid!");
		if(boxQuantity==0)
			return;
		Beverage b = bev.get(beverageId);
		Integer noc = boxQuantity * b.getCpb();
		int total=b.getBoxprice()*boxQuantity;
		if(balance < total)
			throw new NotEnoughBalance("LaTazza balance insufficient");
		Timestamp tsp = new Timestamp(System.currentTimeMillis());
		balance = balance - total;
		b.addQuantity(noc);
		bpl.add(new BoxPurchase(b, boxQuantity,new Transaction(total,tsp), noc,b.getPrice()));
		serverdata(USE.ADD_BOXPURCH,beverageId,boxQuantity,total,tsp, noc,b.getPrice());
	}

	@Override
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException {
		if (employeeId==null || employeeId < 0 || !em.containsKey(employeeId))
			throw new EmployeeException("Employee not valid!");
		//Timestamp tsp = new Timestamp(System.currentTimeMillis());
		if (startDate==null || endDate==null || startDate.compareTo(endDate)>0)
			throw new DateException("Dates not valid!");
		List<String> report = new ArrayList<String>();
		for (Recharge r : em.get(employeeId).getRecharge()) {
			if(!(r.getT().getD().before(startDate) || r.getT().getD().after(endDate)))
				report.add(r.toString());
		}
		report.addAll(cons.values().stream()
				.filter(a -> a.getE()!= null && a.getE().getEmployeeId().equals(employeeId) && !(a.getT().getD().before(startDate) || a.getT().getD().after(endDate)))
				.map(Consumption::toString).collect(Collectors.toList()));

		if(!report.isEmpty())
			report.sort((a,b)-> a.substring(0,18).compareTo(b.substring(0, 18)));
		return report;
	}

	@Override
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		//Timestamp tsp = new Timestamp(System.currentTimeMillis());
		if (startDate==null || endDate==null || startDate.compareTo(endDate)>0)
			throw new DateException("Dates not valid!");
		List<String> report = new ArrayList<String>();
		for(Integer e : em.keySet())
			for (Recharge r : em.get(e).getRecharge()) {
				if(!(r.getT().getD().before(startDate) || r.getT().getD().after(endDate)))
					report.add(r.toString());
			}
		for(BoxPurchase b : bpl)
			report.add(b.toString());
		report.addAll(cons.values().stream()
				.filter(a -> !(a.getT().getD().before(startDate) || a.getT().getD().after(endDate)))
				.map(Consumption::toString).collect(Collectors.toList()));
		
		if(!report.isEmpty())
			report.sort((a,b)-> a.substring(0,18).compareTo(b.substring(0, 18)));
		return report;

	}

	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		if (name==null || capsulesPerBox==null || boxPrice==null || name.isEmpty() || capsulesPerBox <= 0 || boxPrice < 0)
			throw new BeverageException("Beverage cannot be created!");
		while(bev.containsKey(this.bid)) this.bid++;
		Beverage b = new Beverage(bid, name,capsulesPerBox,boxPrice);
		bev.put(bid, b);
		serverdata(USE.ADD_BEVERAGE,bid,name,b.getPrice(),"0",capsulesPerBox,boxPrice);

		return bid++;
	}

	@Override
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		if (id==null || name==null || capsulesPerBox==null || boxPrice==null || id < 0  || !bev.containsKey(id) || name.isEmpty() || capsulesPerBox <= 0 || boxPrice < 0)
			throw new BeverageException("Beverage not valid!");
		Beverage b=bev.get(id);
		b.setBoxprice(boxPrice);
		b.setCpb(capsulesPerBox);
		b.setPrice();
		b.setName(name);
		serverdata(USE.UPDATE_BEVERAGE,id,name,b.getPrice(),capsulesPerBox,boxPrice);

	}

	@Override
	public String getBeverageName(Integer id) throws BeverageException {
		if (id==null || id < 0 || !bev.containsKey(id))
			throw new BeverageException("Beverage not valid!");
		return bev.get(id).getName();
	}

	@Override
	public Integer getBeverageCapsulesPerBox(Integer id) throws BeverageException {
		if (id==null || id < 0 || !bev.containsKey(id))
			throw new BeverageException("Beverage not valid!");
		return bev.get(id).getCpb();
	}

	@Override
	public Integer getBeverageBoxPrice(Integer id) throws BeverageException {
		if (id==null || id < 0 || !bev.containsKey(id))
			throw new BeverageException("Beverage not valid!");
		return bev.get(id).getBoxprice();
	}

	@Override
	public List<Integer> getBeveragesId() {
		return new ArrayList<Integer>(bev.keySet());
	}

	@Override
	public Map<Integer, String> getBeverages() {
		return new TreeMap<Integer, String>(
				bev.values().stream().collect(Collectors.toMap(Beverage::getBeverageId, Beverage::getName)));
	}

	@Override
	public Integer getBeverageCapsules(Integer id) throws BeverageException {
		if (id==null || id < 0 || !bev.containsKey(id))
			throw new BeverageException("Beverage not valid!");
		return bev.get(id).getQuantity();
	}

	@Override
	public Integer createEmployee(String name, String surname) throws EmployeeException {
		if (name==null || surname==null || !name.matches("[a-zA-Z]*") || !surname.matches("[a-zA-Z]*") || name.isEmpty() || surname.isEmpty())
			throw new EmployeeException("Invalid Arguments");
		while(em.containsKey(this.eid)) this.eid++;
		Employee e = new Employee(eid, name, surname);
		em.put(eid, e);
		serverdata(USE.ADD_EMPLOYEE,eid,name,surname);
		return eid++;
	}

	@Override
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		if (id==null || name==null || surname==null || id < 0 || !em.containsKey(id) || !name.matches("[a-zA-Z]*") || !surname.matches("[a-zA-Z]*") || name.isEmpty() || surname.isEmpty())
			throw new EmployeeException("Employee not valid!");
		em.get(id).setName(name);
		em.get(id).setSurname(surname);
		serverdata(USE.UPDATE_EMPLOYEE,id,name,surname);

	}

	@Override
	public String getEmployeeName(Integer id) throws EmployeeException {
		if (id== null || id < 0 || !em.containsKey(id))
			throw new EmployeeException("Employee not valid!");
		return em.get(id).getName();
	}

	@Override
	public String getEmployeeSurname(Integer id) throws EmployeeException {
		if (id== null || id < 0 || !em.containsKey(id))
			throw new EmployeeException("Employee not valid!");
		return em.get(id).getSurname();
	}

	@Override
	public Integer getEmployeeBalance(Integer id) throws EmployeeException {
		if (id== null || id < 0 || !em.containsKey(id))
			throw new EmployeeException("Employee not valid!");
		return em.get(id).getBalance();
	}

	@Override
	public List<Integer> getEmployeesId() {
		return new ArrayList<Integer>(em.keySet());
	}

	@Override
	public Map<Integer, String> getEmployees() {
		return new TreeMap<Integer, String>(
				em.values().stream().collect(Collectors.toMap(Employee::getEmployeeId, Employee::getDenom)));
	}

	@Override
	public Integer getBalance() {
		return this.balance;
	}

	@Override
	public void reset() {
		this.balance=0;
		this.eid=0;
		this.cid=0;
		this.bid=0;
		this.em.clear();
		this.bev.clear();
		this.cons.clear();
		this.bpl.clear();
		serverdata(USE.RESET);

	}

	public DataImpl() {
			try {
				serverconnect();
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Server offline or connection attributes invalid, please fill attributes url,driver,user,pass at the end of the file DataImpl.java\nServer supporting MySql required.");
			}
	}
		
	private static String initquery="CREATE TABLE IF NOT EXISTS EMPLOYEES (id INTEGER not NULL PRIMARY KEY, name VARCHAR(255) not NULL, surname VARCHAR(255) not NULL,balance INTEGER DEFAULT(0));"+
			"CREATE TABLE IF NOT EXISTS BEVERAGES (id INTEGER not NULL UNIQUE , name VARCHAR(255) not NULL, price INTEGER NOT NULL, quantity INTEGER DEFAULT(0),cpb INTEGER NOT NULL,boxprice INTEGER NOT NULL,PRIMARY KEY(id));"+
			"CREATE TABLE IF NOT EXISTS CONSUMPTIONS (id INTEGER not NULL UNIQUE , idempl INTEGER DEFAULT -1, idbev INTEGER not NULL, quantity INTEGER ,fromA BIT DEFAULT 0,total INTEGER, timest DATETIME, PRIMARY KEY(id));"+
			"CREATE TABLE IF NOT EXISTS BOXPURCH (id INTEGER not NULL AUTO_INCREMENT, idbev INTEGER not NULL, quantity INTEGER , total INTEGER, timest DATETIME , remcaps INTEGER, price INTEGER, PRIMARY KEY(id));"+
			"CREATE TABLE IF NOT EXISTS RECHARGES (id INTEGER not NULL AUTO_INCREMENT, idempl INTEGER not NULL, total INTEGER, timest DATETIME , PRIMARY KEY(id));"+
			"INSERT INTO EMPLOYEES (id,name,surname,balance) VALUES (-1, 'LaTazza','System',0) ON DUPLICATE KEY UPDATE balance=balance;";

	public Integer getEid() {
		return eid;
	}
	public Integer getBid() {
		return bid;
	}
	public Integer getCid() {
		return cid;
	}
	public List<BoxPurchase> getBpl() {
		return bpl;
	}
	public Map<Integer, Employee> getEm() {
		return em;
	}
	public Map<Integer, Beverage> getBev() {
		return bev;
	}
	public Map<Integer, Consumption> getCons() {
		return cons;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	private String url="jdbc:h2:~/test;mode=MySQL";
	private String driver="org.h2.Driver";
	private String user="";
	private String pass="";
}
