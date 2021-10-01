package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class TestDataImpl {

	private DataImpl tester;

	@Test
	public void testSellCapsules() throws EmployeeException, BeverageException, NotEnoughCapsules, NotEnoughBalance {


		Employee e = new Employee (2, "m", "n");
		assertThrows(EmployeeException.class ,() -> { tester.sellCapsules(2, 1, 19, true);});
		Employee e2 = new Employee (-2, "m", "n");
		assertThrows(EmployeeException.class ,() -> { tester.sellCapsules(-2, 1, 19, true);});
		tester.getEm().put(2,e);
		tester.setBalance(10000);
		Beverage b = new Beverage (1, "coffee", 20, 500);
		assertThrows(BeverageException.class ,() -> { tester.sellCapsules(2, 1, 19, true);});
		tester.getBev().put(1, b);
		tester.buyBoxes(1, 5);
		assertThrows(BeverageException.class ,() -> { tester.sellCapsules(2, -2, 19, true);});
		assertThrows(EmployeeException.class ,() -> { tester.sellCapsules(-2, 1, 19, true);});
		assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsules(2, 1, -5, true);});
		assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsules(2, 1, 200, true);});

		assertTrue(tester.getEm().containsKey(e.getEmployeeId()));
		assertTrue(tester.getBev().containsKey(b.getBeverageId()));



		assertTrue(e.getEmployeeId()>=0 && tester.getEm().containsKey(e.getEmployeeId()));
		assertTrue(b.getBeverageId()>=0 && tester.getBev().containsKey(b.getBeverageId()));
		assertTrue(b.getQuantity()>=0 && tester.getBeverageCapsulesPerBox(b.getBeverageId()) <= b.getQuantity());

		assertTrue(!tester.getCons().containsKey(tester.getCid()));
		int res = tester.sellCapsules(2, 1, 19, true);
		assertEquals(res, -475);
		assertEquals((int) tester.getCid(),1);



		Employee e1 = new Employee (3, "g", "h",2000);
		e1.setBalance(10000);
		tester.getEm().put(3,e1);
		res = tester.sellCapsules(3, 1, 19, true);
		assertEquals(res, (int) e1.getBalance());

		assertEquals((int) tester.getCid(),2);


		int res1 = tester.sellCapsules(3, 1, 19, false);
		assertEquals(res1, (int) e1.getBalance());
	}

	@Test
	public void testSellCapsulesToVisitors() throws BeverageException, NotEnoughCapsules {

		Beverage b = new Beverage (1, "coffee", 20, 500);
		b.setQuantity(20);
		tester.getBev().put(1, b);

		assertThrows(BeverageException.class ,() -> { tester.sellCapsulesToVisitor( -2, 19);});
		assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsulesToVisitor( 1, -5);});
		assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsulesToVisitor( 1, 40);});

		tester.sellCapsulesToVisitor( 1, 19);
		assertEquals(tester.getCons().containsKey(tester.getCid()-1), true);

	}

	@Test
	public void testRechargeAccount() throws EmployeeException {

		Employee e = new Employee (2, "m", "n");
		tester.getEm().put(2,e);

		assertThrows(EmployeeException.class ,() -> { tester.rechargeAccount(-2, 500);});
		assertThrows(EmployeeException.class ,() -> { tester.rechargeAccount(2, -500);});

		e.setBalance(800);
		int res = tester.rechargeAccount(2,800);

		assertEquals(res, 1600);
		assertEquals((int) tester.getBalance(), 800);

	}

	@Test
	public void testBuyBoxes() throws BeverageException, NotEnoughBalance {

		Beverage b = new Beverage (1, "coffee", 20, 500);
		tester.getBev().put(1, b);

		assertThrows(BeverageException.class ,() -> { tester.buyBoxes( -2, 1);});
		assertThrows(NotEnoughBalance.class ,() -> { tester.buyBoxes( 1, 2);});

		tester.setBalance(2000);
		tester.buyBoxes(1, 3);
		assertTrue(!tester.getBpl().isEmpty());

	}


	@Test
	public void testGetEmployeeReport() throws EmployeeException, DateException, BeverageException, ParseException {

		Employee e = new Employee (2, "m", "n");
		tester.getEm().put(2,e);
		Date d=new SimpleDateFormat("yyyy/MM/dd").parse("2030/01/01");
		assertThrows(EmployeeException.class ,() -> { tester.getEmployeeReport(-2, new Date(0),new Date());});
		assertThrows(DateException.class ,() -> { tester.getEmployeeReport(2, new Date(),new Date(500));});
		assertThrows(DateException.class ,() -> { tester.getEmployeeReport(2, d,new Date());});
		List<String> li=new ArrayList<String>();
		int count=0;
		tester.createBeverage("coffee", 5, 5);
		tester.createEmployee("mario", "rossi");
		Timestamp tim=new Timestamp((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-05-20 21:00:00")).getTime());
		Transaction t = new Transaction(1000,tim );
		e = tester.getEm().get(0);
		Beverage b = tester.getBev().get(0);
		e.getRecharge().add(new Recharge(0,tester.getEm().get(0),t));
		tester.getCons().put(0, new Consumption(0,e,b,1,true, t));
		tester.getCons().put(1, new Consumption(1,e,b,1,false, t));
		tester.getCons().put(2, new Consumption(2,b,1, t));
		tester.getBpl().add(new BoxPurchase(b,2,t));
		li.add("2018/05/20 21:00:00 BALANCE mario rossi coffee 1");
		li.add("2018/05/20 21:00:00 CASH mario rossi coffee 1");
		li.add("2018/05/20 21:00:00 RECHARGE mario rossi 10.00"+" \u20ac");

		List <String> resultlist= tester.getEmployeeReport(0,new Timestamp(0),new Timestamp(new Date().getTime()));

		for(String s: resultlist )
		{
			assertTrue(li.contains(s));
			count++;
		}
		assertEquals(count,li.size());
	}



	@Test
	public void testGetReport() throws DateException, EmployeeException, BeverageException, ParseException {

		Date d=new SimpleDateFormat("yyyy/MM/dd").parse("2030/01/01");
		assertThrows(DateException.class ,() -> { tester.getReport(new Date(),new Date(500));});
		assertThrows(DateException.class ,() -> { tester.getReport(d,new Date());});
		List<String> li=new ArrayList<String>();
		int count=0;
		tester.createBeverage("coffee", 5, 5);
		tester.createEmployee("mario", "rossi");
		Timestamp tim=new Timestamp((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-05-20 21:00:00")).getTime());
		Transaction t = new Transaction(1000,tim );
		Employee e = tester.getEm().get(0);
		Beverage b = tester.getBev().get(0);
		e.getRecharge().add(new Recharge(0,tester.getEm().get(0),t));
		tester.getCons().put(0, new Consumption(0,e,b,1,true, t));
		tester.getCons().put(1, new Consumption(1,e,b,1,false, t));
		tester.getCons().put(2, new Consumption(2,b,1, t));
		tester.getBpl().add(new BoxPurchase(b,2,t));
		li.add("2018/05/20 21:00:00 BALANCE mario rossi coffee 1");
		li.add("2018/05/20 21:00:00 CASH mario rossi coffee 1");
		li.add("2018/05/20 21:00:00 RECHARGE mario rossi 10.00"+" \u20ac");
		li.add("2018/05/20 21:00:00 VISITOR coffee 1");
		li.add("2018/05/20 21:00:00 BUY coffee 2");
		List <String> resultlist= tester.getReport(new Timestamp(0),new Timestamp(new Date().getTime()));

		for(String s: resultlist )
		{
			assertTrue(li.contains(s));
			count++;
		}

		assertEquals(count,li.size());


	}



	@Test
	public void testCreateBeverage() throws BeverageException {

		Beverage b = new Beverage (1, "coffee", 20, 500);
		b.setQuantity(20);
		tester.getBev().put(1, b);

		assertThrows(BeverageException.class ,() -> { tester.createBeverage("", 19,800);});
		assertThrows(BeverageException.class ,() -> { tester.createBeverage("tea", -19,800);});
		assertThrows(BeverageException.class ,() -> { tester.createBeverage("tea", 19,-800);});
		tester.createBeverage("tea", 5000, 1);
		tester.createBeverage("barley coffee", 5, 5);
		Beverage b2 = new Beverage(2, "coffee2", 5000, 1);
		tester.getBev().put(2, b2);
		assertEquals(tester.getBeverageName(0),"tea");
		assertEquals((int)tester.getBev().get(0).getPrice(),1);
		assertEquals(tester.getBeverageName(2),"coffee2");
		assertEquals((int)tester.getBev().get(0).getPrice(),1);		
	}

	@Test
	public void testUpdateBeverage() throws BeverageException {

		Beverage b = new Beverage (1, "coffee", 20, 500);
		tester.getBev().put(1, b);

		assertThrows(BeverageException.class ,() -> { tester.updateBeverage(1, "", 19,800);});
		assertThrows(BeverageException.class ,() -> { tester.updateBeverage(1,"coffee", -19,800);});
		assertThrows(BeverageException.class ,() -> { tester.updateBeverage(1,"coffee", 19,-800);});
		assertThrows(BeverageException.class ,() -> { tester.updateBeverage(-1,"coffee", 19,800);});
		tester.updateBeverage(1, "coffee", 20, 500);
		assertEquals(tester.getBev().containsKey(tester.getBid()+1), true);

		tester.updateBeverage(1, "coffee", 100, 2);
		tester.updateBeverage(1, "coffee", 100, 0);


	}

	@Test
	public void testCreateEmployee() throws EmployeeException {

		Employee e = new Employee (0, "mario", "rossi");
		tester.getEm().put(0,e);

		assertThrows(EmployeeException.class ,() -> { tester.createEmployee("mar8o", "rossi");});
		assertThrows(EmployeeException.class ,() -> { tester.createEmployee("mario", "r7ssi");});

		int res = tester.createEmployee("mario", "rossi");
		assertEquals(res, 1);

	}

	@Test
	public void testUpdateEmployee() throws EmployeeException {

		Employee e = new Employee (1, "mario", "rossi");
		tester.getEm().put(1,e);

		assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(-1, "mario", "rossi");});
		assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(0, "mario", "rossi");});
		assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(1, "m4rio", "rossi");});
		assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(1, "mario", "ros7i");});

		tester.updateEmployee(1, "mario", "rossi");
		assertEquals(tester.getEm().containsKey(tester.getEid()+1), true);

	}

	// WHITE BOXES
	@Test
	public void testpersistence() throws BeverageException, EmployeeException, NotEnoughBalance, NotEnoughCapsules, DateException {
		tester.createBeverage("coffee", 5, 5);
		tester.createEmployee("mario", "rossi");
		tester.rechargeAccount(0, 1000);
		tester.buyBoxes(0, 2);
		tester.sellCapsules(0, 0, 1, true);
		tester.sellCapsules(0, 0, 1, false);
		tester.sellCapsulesToVisitor(0, 1);
		DataImpl tester2 =new DataImpl();
		tester2.reset();
	}

	@Test
	public void testgetBeverageName() throws  BeverageException
	{

		tester.createBeverage("coffee", 5, 5);
		assertEquals(tester.getBeverageName(0),"coffee");
		assertThrows(BeverageException.class,()->{tester.getBeverageName(10);});
		assertThrows(BeverageException.class,()->{tester.getBeverageName(-5);});

	}
	@Test
	public void testgetBeverageCapsulesPerBox() throws  BeverageException
	{

		tester.createBeverage("coffee", 5, 5);
		assertEquals((int)tester.getBeverageCapsulesPerBox(0),5);
		assertThrows(BeverageException.class,()->{tester.getBeverageCapsulesPerBox(10);});
		assertThrows(BeverageException.class,()->{tester.getBeverageCapsulesPerBox(-5);});

	}
	@Test
	public void testgetBeverageCapsules() throws BeverageException
	{
		tester.createBeverage("coffee", 5, 5);
		assertThrows(BeverageException.class,()->{tester.getBeverageCapsules(10);});
		assertThrows(BeverageException.class,()->{tester.getBeverageCapsules(-5);});
		assertEquals((int)tester.getBeverageCapsules(0),0);
	}
	@Test
	public void testgetBeverageBoxPrice() throws BeverageException
	{

		tester.createBeverage("coffee", 5, 5);
		assertEquals((int)tester.getBeverageBoxPrice(0),5);
		assertThrows(BeverageException.class,()->{tester.getBeverageBoxPrice(10);});
		assertThrows(BeverageException.class,()->{tester.getBeverageBoxPrice(-5);});
	}
	@Test
	public void testgetBeveragesId() throws BeverageException
	{
		tester.createBeverage("coffee", 5, 5);
		Integer[] array= {0};
		assertArrayEquals(tester.getBeveragesId().toArray(),array);
	}

	@Test
	public void testgetBeverages() throws BeverageException
	{

		tester.createBeverage("coffee", 5, 5);
		assertTrue(tester.getBeverages().containsKey(0) && tester.getBeverages().size()==1 && tester.getBeverages().get(0).contentEquals("coffee"));
	}

	@Test
	public void testGetEmployeeName() throws EmployeeException {


		Employee e = new Employee(1,"mario", "rossi");
		tester.getEm().put(1, e);
		assertThrows(EmployeeException.class,()->{tester.getEmployeeName(-5);});

		String s =tester.getEmployeeName(1);
		assertEquals(s, e.getName());


	}

	@Test
	public void testGetEmployeeSurname() throws EmployeeException {


		Employee e = new Employee(1,"mario", "rossi");
		tester.getEm().put(1, e);
		assertThrows(EmployeeException.class,()->{tester.getEmployeeSurname(-5);});

		String s= tester.getEmployeeSurname(1);
		assertEquals(s, e.getSurname());


	}



	@Test
	public void testGetEmployeeBalance() throws EmployeeException {


		Employee e = new Employee(1,"mario", "rossi");
		tester.getEm().put(1, e);
		assertThrows(EmployeeException.class,()->{tester.getEmployeeBalance(-5);});

		int res=tester.getEmployeeBalance(1);
		assertEquals(res,0);

	}
	@Test
	public void testGetEmployeesId() throws EmployeeException
	{

		tester.createEmployee("mario", "rossi");
		Integer[] array= {0};
		assertArrayEquals(tester.getEmployeesId().toArray(),array);
	}
	@Test
	public void testGetEmployees() throws EmployeeException
	{

		tester.createEmployee("mario", "rossi");
		//assertTrue(tester.getEmployees().equals(me));

		Arrays.toString(tester.getEmployees().entrySet().toArray());
	}

	@Test
	public void testPerformance() throws EmployeeException, BeverageException, NotEnoughCapsules, NotEnoughBalance, DateException, ParseException
	{
		long begin = System.currentTimeMillis();
		tester.createEmployee("mario", "Rossi");
		long totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance createEmployee: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.createBeverage("coffee", 10, 10);
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance createBeverage: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.rechargeAccount(0, 100);
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance rechargeAccount: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.buyBoxes(0, 5);
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance buyBoxes: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.sellCapsulesToVisitor(0, 5);
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance sellCapsulesToVisitor: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.sellCapsules(0, 0, 5, false);
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance sellCapsules: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.sellCapsules(0, 0, 5, true);
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance sellCapsules: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.updateEmployee(0, "George", "Rossi");
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance updateEmployee: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.updateBeverage(0, "coffee", 10, 15);
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance updateBeverage: "+ totaltime);

		begin = System.currentTimeMillis();
		tester.getReport(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2021/01/01"));
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance getReport : "+ totaltime);

		begin = System.currentTimeMillis();
		tester.getEmployeeReport(0,new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2021/01/01"));
		totaltime=System.currentTimeMillis()-begin; 
		assertTrue(totaltime<500);
		System.out.println("Time performance getEmployeeReport : "+ totaltime);

	}

    @Test
    public void testUpdatePrice()throws EmployeeException, BeverageException, NotEnoughCapsules, NotEnoughBalance
    {
       Integer e= tester.createEmployee("Mario","Rossi");
       tester.rechargeAccount(e,2000);
       Integer b=tester.createBeverage("Coffee",3,600);
       tester.buyBoxes(b,1);
       tester.updateBeverage(b,"Coffee",10,1000);
       tester.buyBoxes(b,1);
       Integer eBalance=tester.sellCapsules(e,b,10,true);
       assertEquals(tester.getBalance().intValue(),400);
       assertEquals(700,eBalance.intValue());
       tester.updateBeverage(b,"Coffee",20,400);
       tester.buyBoxes(b,1);
       eBalance=tester.sellCapsules(e,b,10,false);
       assertEquals(440,tester.getBalance().intValue());
       assertEquals(700,eBalance.intValue());
    }

	@Test
	@AfterEach
	protected void tearDown() {
		tester.reset();

	}
	@Test
	@BeforeEach
	protected void setUp() {
		tester=new DataImpl();
	}

}

