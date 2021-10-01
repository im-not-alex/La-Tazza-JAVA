# Unit Testing Documentation template

Authors: Boscain Simone, Forte Valentina, Nadalin Marina, Onica Alexandru Valentin

Date: 20/05/2019

Version: 2.0




# Contents

- [Black Box Unit Tests](#black-box-unit-tests)



- [White Box Unit Tests](#white-box-unit-tests)

  

# Black Box Unit Tests

```
<Define here criteria, predicates and the combination of predicates for each function of each class.
Define test cases to cover all equivalence classes and boundary conditions.
In the table, report the description of the black box test case and the correspondence with the JUnit black box test case name/number>
```


### Class DataImpl



**Criteria for method sellCapsule:**

- Sign of numberOfCapsules, employeeId, beverageId
- value of fromAccount


**Predicates for method sellCapsule:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Sign of employeeId        | >= 0         |
|                           | < 0          |
| Sign of beverageId        | >= 0         |
|                           | < 0          |
| Sign of numberOfCapsules  | > 0          |
|                           | <= 0         |
| Value of fromAccount      | Yes          |
|                           | No           |

**Boundaries for method sellCapsule:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Sign of employeeId  | 0, MAXINT |
| Sign of BeverageId  | 0, MAXINT  |
| Sign of numberOfCapsules   | 1, MAXINT  |





 **Combination of predicates for method sellCapsule:**

| Value of fromAccount |  Sign of employeeId    | Sign of beverageId | Sign of numberOfCapsules | Valid/Invalid | Description of the test case | JUnit test case |
| -------------------  | ---------------------- | ------------------ | ------------------------ | --------------| ---------------------------- | --------------- |
| YES               | POSITIVE | POSITIVE | POSITIVE | V |assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsules(2, 1, 200, true);}); <br > int res = tester.sellCapsules(2, 1, 19, true); <br /> assertEquals(res, 0);|it.polito.latazza.data.TestDataImpl.testSellCapsules()|
|                   |          |          | NEGATIVE | I |  assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsules(2, 1, -5, true);});                      |                              |
|                   |          | NEGATIVE | POSITIVE | I | assertThrows(BeverageException.class ,() -> { tester.sellCapsules(2, -2, 19, true);});||
|                   |  |  | NEGATIVE | I |||
|                   | NEGATIVE | POSITIVE | POSITIVE | I |assertThrows(EmployeeException.class ,() -> { tester.sellCapsules(-2, 1, 19, true);});||
|                   |  |  | NEGATIVE | I |||
|                   |  | NEGATIVE | POSITIVE | I |||
|                   |  |  | NEGATIVE | I |||
| NO               | POSITIVE | POSITIVE | POSITIVE | V |int res1 = tester.sellCapsules(3, 1, 19, false); <br /> assertEquals(res1, (int) e1.getBalance());|it.polito.latazza.data.TestDataImpl.testSellCapsules()|
|                   |  |  | NEGATIVE | I |||
|                   |  | NEGATIVE | POSITIVE | I |||
|                   |  |  | NEGATIVE | I |||
|                   | NEGATIVE | POSITIVE | POSITIVE | I |||
|                   |  |  | NEGATIVE | I |||
|                   |  | NEGATIVE | POSITIVE | I |||
|                   |  |  | NEGATIVE | I |||

**Criteria for method sellCapsulesToVisitor:**

- Sign of numberOfCapsules, beverageId


**Predicates for method sellCapsulesToVisitor:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Sign of beverageId        | >= 0         |
|                           | < 0          |
| Sign of numberOfCapsules  | > 0          |
|                           | <= 0         |

**Boundaries for method sellCapsulesToVisitor:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Sign of BeverageId  | 0, MAXINT  |
| Sign of numberOfCapsules   | 1, MAXINT  |





 **Combination of predicates for method sellCapsulesToVisitor:**

| Sign of beverageId | Sign of numberOfCapsules | Valid/Invalid | Description of the test case | JUnit test case |
| ------------------ | ------------------------ | --------------| ---------------------------- | --------------- |
| POSITIVE | POSITIVE | V | Beverage b = new Beverage (1, "coffee", 20, 500); <br> b.setQuantity(20); <br>tester.getBev().put(1, b);assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsulesToVisitor( 1, 40);});<br>tester.sellCapsulesToVisitor( 1, 19);<br>assertEquals(tester.getCons().containsKey(tester.getCid()-1), true);|  it.polito.latazza.data.TestDataImpl.testSellCapsulesToVisitors()|
|  | NEGATIVE | I | assertThrows(NotEnoughCapsules.class ,() -> { tester.sellCapsulesToVisitor( 1, -5);});||
| NEGATIVE  | POSITIVE | I |assertThrows(BeverageException.class ,() -> { tester.sellCapsulesToVisitor( -2, 19);});||
| | NEGATIVE | I |||


**Criteria for method rechargeAccount:**

- Sign of amountInCents, id


**Predicates for method rechargeAccount:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Sign of id                | >= 0         |
|                           | < 0          |
| Sign of amountInCents     | > 0          |
|                           | <= 0         |

**Boundaries for method rechargeAccount:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Sign of id  | 0, MAXINT  |
| Sign of amountInCents   | 0, MAXINT  |





 **Combination of predicates for method rechargeAccount:**

| Sign of id | Sign of amountInCents | Valid/Invalid | Description of the test case | JUnit test case |
| ------------------ | ------------------------ | --------------| ---------------------------- | --------------- |
| POSITIVE | POSITIVE | V | e.setBalance(800); <br /> int res = tester.rechargeAccount(2,800); <br /> assertEquals(res, 1600); <br /> assertEquals((int) tester.getBalance(), 800);| it.polito.latazza.data.TestDataImpl.testRechargeAccount()|
|  | NEGATIVE | I | assertThrows(EmployeeException.class ,() -> { tester.rechargeAccount(2, -500);});||
| NEGATIVE  | POSITIVE | I |assertThrows(EmployeeException.class ,() -> { tester.rechargeAccount(-2, 500);});||
| | NEGATIVE | I |||


**Criteria for method buyBoxes:**

- Sign of boxQuantity, balance, beverageId

**Predicates for method buyBoxes:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Value of beverageId        | >= 0         |
|                           | < 0          |
| Sign of boxQuantity  | > 0          |
|                           | <= 0         |
| Enough Balance | false |
|| true|

**Boundaries for method buyBoxes:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Sign of BeverageId  | 0, MAXINT  |
| Sign of boxQuantity   | 0, MAXINT  |






 **Combination of predicates for method buyBoxes:**

| Sign of beverageId | Sign of boxQuantity | Enough Balance |Valid/Invalid | Description of the test case | JUnit test case |
| ------------------ | ------------------------ | -- | --------------| ---------------------------- | --------------- |
| POSITIVE          | POSITIVE          |TRUE | V |Beverage b = new Beverage (1, "coffee", 20, 500);<br>tester.getBev().put(1, b);<br>tester.setBalance(2000);<br>tester.buyBoxes(1, 3);| it.polito.latazza.data.TestDataImpl.testBuyBoxes() |
|        |        |FALSE| I |assertThrows(NotEnoughBalance.class ,() -> { tester.buyBoxes( 1, 2);});||
|                           | NEGATIVE  | | I |||
| NEGATIVE          | POSITIVE          || I |assertThrows(BeverageException.class ,() -> { tester.buyBoxes( -2, 1);});||
|                           | NEGATIVE  | | I |||






**Criteria for method getEmployeeReport:**

- Value of startDate, endDate
- Sign of EmployeeId


**Predicates for method getEmployeeReport:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Sign of EmployeeId        | >= 0         |
|                           | < 0          |
| Value of startDate  | <= Now         |
|                           | > Now        |
| Value of endDate  | < startDate       |
|                           | >= startDate        |

**Boundaries for method getEmployeeReport:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Sign of EmployeeId  | 0, MAXINT  |
| Value of startDate | 01/01/1970 , Now |
| Value of endDate | >= startDate |





 **Combination of predicates for method getEmployeeReport:**

| Sign of EmployeeId | Value of startDate  | Value of endDate | Valid/Invalid | Description of the test case | JUnit test case |
| ------------------ | ------------------------ | -------------- | --------------| ---------------------------- | --------------- |
| POSITIVE | le Now | lt startDate | I |assertThrows(DateException.class ,() -> { tester.getEmployeeReport(2, new Date(),new Date(500));}); ||
|          |  | ge startDate | V | List ``<String>`` resultlist= tester.getEmployeeReport(0,new Timestamp(0), new Timestamp(System.currentTimeMillis())); <br /> for(String s: resultlist ) <br /> { <br /> assertTrue(li.contains(s)); <br /> count++; <br /> } <br /> assertEquals(count,li.size()); | it.polito.latazza.data.TestDataImpl.testGetEmployeeReport() |
|          | gt Now | lt startDate | I | Date d=new SimpleDateFormat("yyyy/MM/dd").parse("2030/01/01");<br>assertThrows(DateException.class ,() -> { tester.getEmployeeReport(2, d,new Date());});||
|          |        | ge startDate | I | ||
| NEGATIVE | le Now | lt startDate | I |||
| |  | ge startDate | I |  assertThrows(EmployeeException.class ,() -> { tester.getEmployeeReport(-2, new Date(0),new Date());}); ||
|  | gt Now | lt startDate | I |||
|  |  | ge startDate | I | ||


**Criteria for method getReport:**

- Value of startDate, endDate


**Predicates for method getReport:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Value of startDate  | <= Now         |
|                           | > Now        |
| Value of endDate  | < startDate       |
|                           | >= startDate        |

**Boundaries for method getReport:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Value of startDate | 01/01/1970 , Now |
| Value of endDate | >= startDate |




 **Combination of predicates for method getReport:**

| Value of startDate  | Value of endDate | Valid/Invalid | Description of the test case | JUnit test case |
| ------------------------ | -------------- | --------------| ---------------------------- | --------------- |
| le Now | lt startDate | I |assertThrows(DateException.class ,() -> { tester.getReport(new Date(),new Date(500));}); <br />  ||
|  | ge startDate | V | List ``<String>`` resultlist= tester.getReport(new Timestamp(0), new Timestamp(System.currentTimeMillis())); <br /> for(String s: resultlist ) <br />  { <br /> assertTrue(li.contains(s)); <br /> count++; <br /> } <br /> assertEquals(count,li.size());| it.polito.latazza.data.TestDataImpl.testGetReport() |
| gt Now | lt startDate | I |Date d=new SimpleDateFormat("yyyy/MM/dd").parse("2030/01/01");<br>assertThrows(DateException.class ,() -> { tester.getReport(d,new Date());}); ||
|  | ge startDate | I | ||

**Criteria for method createBeverage:**

- Value of name
- Sign capsulePerBox, boxPrice 


**Predicates for method createBeverage:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Value of name  | NOT NULL        |
|                           | NULL       |
| Sign of capsulePerBox  | > 0     |
|                           | <= 0       |
| Sign of boxPrice  | >= 0      |
|                           | < 0       |

**Boundaries for method createBeverage:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Value of name  |  not empty   |
| Sign of capsulePerBox  | 1, MAXINT    |
| Sign of boxPrice  |  0, MAXINT     |



 **Combination of predicates for method createBeverage:**

| Value of name  | Sign of capsulePerBox | Sign of boxPrice | Valid/Invalid | Description of the test case | JUnit test case |
| --------------- | ----------------------- | -------------- | --------------| ---------------------------- | --------------- |
| VALID | Positive | Positive | V | tester.createBeverage("tea", 5000, 1);|it.polito.latazza.data.TestDataImpl.testCreateBeverage()|
||| Negative | I |assertThrows(BeverageException.class ,() -> { tester.createBeverage("tea", 19,-800);});||
|  | Negative | Positive | I |assertThrows(BeverageException.class ,() -> { tester.createBeverage("tea", -19,800);});||
||| Negative | I |||
| NULL | Positive | Positive | I |assertThrows(BeverageException.class ,() -> { tester.createBeverage("", 19,800);});||
||| Negative | I |||
|  | Negative | Positive | I |||
||| Negative | I |||

**Criteria for method updateBeverage:**

- Value of name
- Sign of capsulePerBox, boxPrice,id


**Predicates for method updateBeverage:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Sign of id | >= 0 |
|| < 0 |
| Value of name  | NOT NULL        |
|                           | NULL       |
| Sign of capsulePerBox  | > 0     |
|                           | <= 0       |
| Sign of boxPrice  | >= 0      |
|                           | < 0       |

**Boundaries for method updateBeverage:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Value of id | 0, MAXINT |
| Value of name  |  not empty   |
| Value of capsulePerBox  | 1, MAXINT    |
| Value of boxPrice  |  0, MAXINT     |



 **Combination of predicates for method updateBeverage:**

| Sign of id | Value of name  | Sign of capsulePerBox | Sign of boxPrice | Valid/Invalid | Description of the test case | JUnit test case |
| ------ | --------------- | ----------------------- | -------------- | --------------| ---------------------------- | --------------- |
| POSITIVE | VALID | POSITIVE | POSITIVE | V |    tester.updateBeverage(1, "coffee", 20, 500);<br>assertEquals(tester.getBev().containsKey(tester.getBid()+1), true);| it.polito.latazza.data.TestDataImpl.testUpdateBeverage() |
| ||| NEGATIVE | I |assertThrows(BeverageException.class ,() -> { tester.updateBeverage(1,"coffee", 19,-800);});||
| | | NEGATIVE | POSITIVE | I |assertThrows(BeverageException.class ,() -> { tester.updateBeverage(1,"coffee", -19,800);});||
|||| NEGATIVE | I |||
|| NULL | POSITIVE | POSITIVE | I |assertThrows(BeverageException.class ,() -> { tester.updateBeverage(1, "", 19,800);});||
|||| NEGATIVE | I |||
||  | NEGATIVE | POSITIVE | I |||
|||| NEGATIVE | I |||
| NEGATIVE | VALID | POSITIVE | POSITIVE | I |assertThrows(BeverageException.class ,() -> { tester.updateBeverage(-1,"coffee", 19,800);});||
| ||| NEGATIVE | I |||
| | | NEGATIVE | POSITIVE | I |||
|||| NEGATIVE | I |||
|| NULL | POSITIVE | POSITIVE | I |||
|||| NEGATIVE | I |||
||  | NEGATIVE | POSITIVE | I |||
|||| NEGATIVE | I |||


**Criteria for method createEmployee:**

- Value of name, surname


**Predicates for method createEmployee:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Value of name  | NOT NULL        |
|                           | NULL       |
| Value of surname  | NOT NULL      |
|                           | NULL |

**Boundaries for method createEmployee:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Value of name  |  "[a-zA-Z]*"   |
| Value of surname  |  "[a-zA-Z]*"   |




 **Combination of predicates for method createEmployee:**

| Value of name  | Value of surname | Valid/Invalid | Description of the test case | JUnit test case |
| ------------------------ | -------------- | --------------| ---------------------------- | --------------- |
| ONLY CHARS | ONLY CHARS | V | int res = tester.createEmployee("mario", "rossi"); <br /> assertEquals(res, 1);|it.polito.latazza.data.TestDataImpl.testCreateEmployee() |
|  | INVALID WORD | I |  assertThrows(EmployeeException.class ,() -> { tester.createEmployee("mario", "r7ssi");}); ||
| INVALID WORD | ONLY CHARS | I |assertThrows(EmployeeException.class ,() -> { tester.createEmployee("mar8o", "rossi");});||
|  | INVALID WORD | I | ||

**Criteria for method updateEmployee:**

- Sign of id
- Value of name, surname


**Predicates for method updateEmployee:**

| Criteria                  | Predicate    |
| ------------------------- | ------------ |
| Value of id | >= 0 |
|| < 0 |
| Value of name  | NOT NULL        |
|                           | NULL       |
| Value of surname  | < NOT NULL      |
|                           | NULL |

**Boundaries for method updateEmployee:**

| Criteria            | Boundary values             |
| ------------------- | --------------------------- |
| Value of id | 0, MAXINT |
| Value of name  |  "[a-zA-Z]*"   |
| Value of surname  |  "[a-zA-Z]*"   |




 **Combination of predicates for method updateEmployee:**

| Sign of id | Value of name  | Value of surname | Valid/Invalid | Description of the test case | JUnit test case |
| ----------- | ------------------------ | -------------- | --------------| ---------------------------- | --------------- |
| POSITIVE |ONLY CHARS | ONLY CHARS | V | assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(0, "mario", "rossi");});<br>tester.updateEmployee(1, "mario", "rossi");<br>assertEquals(tester.getEm().containsKey(tester.getEid()+1), true); |it.polito.latazza.data.TestDataImpl.testUpdateEmployee()|
| | | INVALID WORD | I |  assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(1, "mario", "ros7i");}); ||
|| INVALID WORD | ONLY CHARS | I | assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(1, "m4rio", "rossi");});||
|| | INVALID WORD | I | ||
| NEGATIVE |ONLY CHARS | ONLY CHARS | I |assertThrows(EmployeeException.class ,() -> { tester.updateEmployee(-1, "mario", "rossi");});||
| | | INVALID WORD | I |  ||
|| INVALID WORD | ONLY CHARS | I |||
|| | INVALID WORD | I | ||



# White Box Unit Tests

### Test cases definition



| Unit name | JUnit test case                              |
| --------- | -------------------------------------------- |
| sellCapsules | it.polito.latazza.data.TestDataImpl.testSellCapsules()  |
| sellCapsulesToVisitor | it.polito.latazza.data.TestDataImpl.testSellCapsulesToVisitors()  |
|  rechargeAccount      |  it.polito.latazza.data.TestDataImpl.testRechargeAccount()       |
|  buyBoxes      | it.polito.latazza.data.TestDataImpl.testBuyBoxes()       |
| getEmployeeReport | it.polito.latazza.data.TestDataImpl.testGetEmployeeReport() | 
| getReport | it.polito.latazza.data.TestDataImpl.testGetReport() |
|createBeverage| it.polito.latazza.data.TestDataImpl.testCreateBeverage()|
|updateBeverage |it.polito.latazza.data.TestDataImpl.testUpdateBeverage() |
|getBeverageName| it.polito.latazza.data.TestDataImpl.testgetBeverageName() |
|getBeverageCapsulesPerBox| it.polito.latazza.data.TestDataImpl.testgetBeverageCapsulesPerBox()|
|getBeverageBoxPrice | it.polito.latazza.data.TestDataImpl.testgetBeverageBoxPrice() |
|getBeveragesId|  it.polito.latazza.data.TestDataImpl.testgetBeveragesId() |
|getBeverages| it.polito.latazza.data.TestDataImpl.testgetBeverages() |
|getBeverageCapsules|it.polito.latazza.data.TestDataImpl.testgetBeverageCapsules() |
|createEmployee| it.polito.latazza.data.TestDataImpl.testCreateEmployee() |
|updateEmployee| it.polito.latazza.data.TestDataImpl.testUpdateEmployee() |
|getEmployeeName| it.polito.latazza.data.TestDataImpl.testGetEmployeeName() |
|getEmployeeSurname| it.polito.latazza.data.TestDataImpl.testGetEmployeeSurname() |
|getEmployeeBalance| it.polito.latazza.data.TestDataImpl.testGetEmployeeBalance() |
|getEmployeesId| it.polito.latazza.data.TestDataImpl.testGetEmployeesId()|
|getEmployees| it.polito.latazza.data.TestDataImpl.testGetEmployees() |



### Code coverage report
#### it.polito.latazza.data

![it.polito.latazza.data](templates/jacoco_out.PNG)

#### DataImpl.java

![DataImpl.java](templates/jacoco_dataimpl.PNG)

### Loop coverage analysis


| Unit name | Loop rows | Number of iterations | JUnit test case                              |
| --------- | --------- | -------------------- | -------------------------------------------- |
| sellCapsules | 197    | 0,1,2+                   | it.polito.latazza.data.TestDataImpl.testSellCapsules()   |
| sellCapsulesToVisitor          | 217         | 0,1,2+                    | it.polito.latazza.data.TestDataImpl.testSellCapsulesToVisitors() |
| getEmployeeReport          | 270-273          |    0,1,2+                 | it.polito.latazza.data.TestDataImpl.testGetEmployeeReport() |
| getReport       | 287-291, 292-293      |  0,1,2+                 | it.polito.latazza.data.TestDataImpl.testGetReport()     |
| createBeverage       | 305     |  0,1,2+                 |it.polito.latazza.data.TestDataImpl.testCreateBeverage()    |
| createEmployee       | 369     |  0,1,2+                 |it.polito.latazza.data.TestDataImpl.testCreateEmployee()    |



