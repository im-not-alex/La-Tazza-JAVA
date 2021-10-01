# Design Document Template

Authors: Boscain Simone, Forte Valentina, Nadalin Marina, Onica Alexandru Valentin

Date: 09/06/2019

Version: 3.0

# Contents

- [Package diagram](#package-diagram)
- [Class diagram](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design document has to comply with:
1. [Official Requirement Document](../Official\ Requirements\ Document.md)
2. [DataInterface.java](../src/main/java/it/polito/latazza/data/DataInterface.java)

UML diagrams **MUST** be written using plantuml notation.

# Package diagram

```plantuml
title Packages - Package Diagram
skinparam packageStyle Folder

package "View" #FFFACD {
    
} 
package "Controller" #FFFACD{
    package Data {}
    package Exceptions{}
}
Package "Model" #FFFACD{
}
    View --|> Model : +GetData
    View <|-- Model : +Update
    Controller <|-- Model : +Update/Modify
     View <|-- Controller : +Manupulate
note "Containts View classes of the MVC pattern" as N1
note "Facade of the methods available and Controller of the MVC pattern and interfaces with DB" as N2
N1 .. "View"
N2 .. "Data"
```


# Class diagram


## Package View

```plantuml

class JButton
class JFrame

note "Administrator input GUI " as N2
N2 .. JButton
note "Administrator input/output GUI" as N3
N3 .. JFrame

```

## Package Controller and Model
### All necessary constructors, getter and setters not explicitly showed are implied.

```plantuml

class LaTazza << (S,#FF7700) Singleton >>{
    +main()
}



class Recharge{
    -RechargeId
    -EmployeeId
    -transaction
}
class BoxPurchase {
    -beverageId
    -boxQuantity
    -transaction
    -remcaps
    -price
}
note "Corresponds to Colleague of the official requirements document" as N2
N2 .left. Employee
class Employee {
    -EmployeeId
    -name
    -surname
    -balance
    -ConsumptionList
    -RechargeList
    ..Constructor..
    +createEmployee(name, surname)
    ..Setter..
    +updateEmployee(name, surname)
    ..Getter..
    +getEmployeeName()
    +getEmployeeSurname()
    +getEmployeeBalance()
}
class Consumption {
    -ConsumptionId
    -employeeId*
    -beverageId
    -numberOfCapsules
    -fromAccount*
    -transaction
}
class Transaction {
    -date
    -amount
}
note "Corresponds to CapsuleType of the official requirements document" as N1
N1 .up. Beverage

class Beverage {
    -beverageId 
    -name
    -price
    -quantity
    -capsuleperBox
    -boxPrice
    ..Constructor..
    +createBeverage(name, capsulesPerBox, boxPrice)
    ..Setter..
    +updateBeverage(name, capsulesPerBox, boxPrice)
    ..Getter..
    +getBeverageName()
    +getBeverageCapsulesPerBox()
    +getBeverageBoxPrice()
    +getBeverageCapsules()
}
package Data{
interface DataInterface{
}
class DataImplementation << (F,#228B22) Facade >>{
    -balance
    -BoxPurchaseList
    -EmployeeIndex
    -EmployeeMap
    -BeverageIndex
    -BeverageMap
    -ConsumptionIndex
    -ConsumptionMap
    +sellCapsules(employeeId, beverageId, numberOfCapsules, fromAccount)
    +sellCapsulesToVisitor(beverageId, numberOfCapsules)
    +rechargeAccount(id, amountInCents)
    +buyBoxes(beverageId, boxQuantity)
    +getEmployeeReport(employeeId, startDate, endDate)
    +getReport(startDate, endDate)
    +getBeveragesId()
    +getBeverages()
    +getEmployeesId()
    +getEmployees()
    +getBalance()
    +reset()
    -serverconnect()
    -retrivedata()
    -serverdata()
    -Consuming(beverageId, numberOfCapsules)
}

}
package Exceptions {
    
    class BeverageException << (E,#FF0000) Exception >>{ 
    +BeverageException(string)
    }
    class DateException << (E,#FF0000) Exception >>{
    +DateException(string)
    }
    class EmployeeException << (E,#FF0000) Exception >>{ 
    +EmployeeException(string)
    }
    class NotEnoughBalance << (E,#FF0000) Exception >>{ 
    +NotEnoughBalance(string)
    }
    class NotEnoughCapsules << (E,#FF0000) Exception >>{ 
    +NotEnoughCapsules(string)
    }
}

DataInterface <|-- DataImplementation : "Extends"
LaTazza -- DataImplementation
DataImplementation -left- "*" Employee
DataImplementation -down- "*" Beverage
DataImplementation -left- "*" Transaction
Recharge -down-|> Transaction
Consumption -down-|> Transaction
Transaction <|-up- BoxPurchase
Employee -- "*" Consumption
Employee -- "*" Recharge
Consumption "*"-- "*" Beverage
BoxPurchase "*"-- "*" Beverage
DataImplementation -- Exceptions
Employee --> EmployeeException
Beverage --> BeverageException

BeverageException -[hidden]right- DateException
NotEnoughCapsules -[hidden]r- EmployeeException
DateException -[hidden]r- NotEnoughBalance
NotEnoughBalance -[hidden]r- NotEnoughCapsules


```


# Verification traceability matrix


|  | Recharge | Beverage  | Transaction | Employee | Consumption | BoxPurchase | LaTazza | DataImplementation | JButton | JFrame |
| ------------- |:-------------:| :-----:| :-----:| :------: | :-----:| :-----: | :-----: | :-----: | :-----: | :-----: |
| FR1  |  | x | x | x | x | | x | x | x | x |
| FR2  |  | x | x | | x | | x | x | x | x |
| FR3  | x  |  | x | x | | | x | x | x | x |
| FR4  |  | x  |x | | | x | x | x | x| x | 
| FR5  | x |  |x |x | x | | x | x | x | x | 
| FR6  | x |  | x| x| x | | x | x | x | x | 
| FR7  |  | x | | |  | | x | x | x | x | 
| FR8  |  |  | | x|  | | x | x | x | x | 




# Verification sequence diagrams 

## Scenario 1 - Colleague uses one capsule of type T

```plantuml
": JButton/JFrame" -> ": DataImplementation": 1: selectBeverageDropDownMenu()
": DataImplementation" -> ": Beverage": 2: getBeverageId()
": JButton/JFrame" -> ": DataImplementation": 3: selectEmployeeDropDownMenu()
": DataImplementation" -> ": Employee": 4: getEmployeeId()
": JButton/JFrame" -> ": DataImplementation": 5: selectNumberCapsule()
": JButton/JFrame" -> ": DataImplementation" : 6: clickSellButton()
activate ": DataImplementation"
": DataImplementation" -> ": DataImplementation" : 7: sellCapsules()
return 8: ok!
```
<br><br>

 ## Scenario 2 - Colleague uses one capsule of type T, account negative 
```plantuml
": JButton/JFrame" -> ": DataImplementation": 1: selectBeverageDropDownMenu()
": DataImplementation" -> ": Beverage": 2: getBeverageId()
": JButton/JFrame" -> ": DataImplementation": 3: selectEmployeeDropDownMenu()
": DataImplementation" -> ": Employee": 4: getEmployeeId()
": JButton/JFrame" -> ": DataImplementation": 5: selectNumberCapsule()
": JButton/JFrame" -> ": DataImplementation" : 6: clickSellButton()
": DataImplementation" -> ": DataImplementation" : 7: sellCapsules()
": DataImplementation" -> ": NotEnoughBalance" : 8: warningBalance()
": NotEnoughBalance" -> ": JButton/JFrame" : 9: displayerror()

```