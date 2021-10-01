# Requirements Document 

Authors: Boscain Simone, Forte Valentina, Nadalin Marina, Onica Alexandru Valentin

Date: 20/05/2019

Version: 2.0


# Contents

- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)

# Stakeholders


| Stakeholder name  | Description | 
| ----------------- |:-----------:|
| Client            | Person interested in buying capsules interacting with the system LaTazza. He can be an employee or a visitor |
| Employee          | Person working in the company, who can buy capsules both by cash and through the bank system |
| Visitor           | Person visiting the company, who can buy capsules only by cash |
| Manager           | Employee in charge of handling the users requests and accounts, buy capsules and manage cash |
| Wholesaler        | Company or individual selling the capsules to the Manager |
| Bank System       | It manages account balance updates of Employee |
| IT Consultant     | Person in charge of resolving the hardware and software problems of the system |

# Context Diagram and interfaces

## Context Diagram
```plantuml
left to right direction
skinparam packageStyle rectangle

actor Manager as m
actor Client as c
actor "Bank System" as bs
m --|> Employee
Employee --|> c
Visitor --|> c

rectangle system {
(La Tazza) as t
m -- t
c -- t
t -- bs
}
```

## Interfaces
| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| :-----:|
| Manager       | GUI           | software application  |
| Employee       | GUI           | software application  |
| Visitor       | GUI           | touchscreen   |
| Bank System   | server requests | optical fiber       |


# Stories and personas

* As every Thursday evening, before leaving the office the Manager, Mr. Proietti, checks for capsule availability and usage  in order to guarantee the constant refillment of the system. He notices that his favourite capsule type, the Arabic coffee , is lacking  so he immediately places a 3 boxes order with a wholesaler. As soon as he is ready to leave, an order request pops-up on his monitor, so he checks if the balance of the requesting employee is not deeply negative (under -20€) or if negative but accectable for no longer than 2 weeks; everything is compliant with the requirements, so Mr. Proietti accepts the request and finally goes home.

* George C., a diligent worker, after 6 hours of straight work on his PC, to complete a imminent delivery, decides to take a break, what's better than a nice cup of coffee?
Unfortunately, he has no capsules left, so he checks his account in order to buy some. It is his unlucky day, his balance is zero, but he can pay using cash, moreover he decides to increase his balance with the 10€ he owns in his wallet, so he requests Mr. Proietti to update his account. He finally manages to buy a set of capsules and enjoy his coffee, 'What else?'.



# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| ------------- |:-------------:| 
|FR1.0        | Log-in employee |
|FR1.1      | Make capsule request |
|FR1.2      | Check account balance and payments history |
|FR1.3      | Update account balance |
|FR1.4      | Make account or cash payment |
|FR1.5      | Add Credit Card |
|FR1.6      | Log-out employee |
|FR2.0      | Log-in manager |
|FR2.01     | Handle employee orders|
|FR2.02     | Receive employee capsules request |
|FR2.03     | Search and check employee by ID |
|FR2.04     | Handle employee account | 
|FR2.05     | Show employee payment history|
|FR2.06     | Show employee balance |
|FR2.07     | Accept or refuse employee request |
|FR2.08     | Manage credit and debt of the employee |
|FR2.09     | Receive account payment from employee |
|FR2.10     | Check the company cash account |
|FR2.11     | Show the left amount for each kind of capsules |
|FR2.12     | Store order for each kind of capsule |
|FR2.13     | Log-out manager |
|FR3.0      | Manager sells capsules to visitors or employee by cash |
|FR4.0      | Manager orders capsules from wholesalers |
|FR4.1      | Manager pays wholesalers |
|FR5.0      | Manager delivers capsules to employees |
|FR6.0      | Visitors make capsule requests through a touchscreen |

## Non Functional Requirements

| ID| Type (efficiency, reliability, ..)| Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|NF1 | Privacy     | Sensitive data shall be preserved | FR1.0,FR2.0|
|NF2 | Domain      | Payment must be in €/euro | FR1.4, FR2.09, FR4.1 |
|NF3 | Efficiency  | Furnish of capsules must not exceed 1 hours | FR5.0 |
|NF4 | Reliability | The balance in the account employee must exceed -20€  | FR2.07, FR2.08 |
|NF5 | Reliability | Negative balances must not perpetuate over 2 weeks  | FR2.08 |
|NF6 | Reliability | Cash account must not exceed 100.000 € or go below 0 € | FR2.10 |
|NF7 | Reliability | System downtime must be less than 1 hour for week | FR1.0, FR2.0  |  
|NF8 | Domain      | Credit Card must be valid and in SEPA area | FR1.5 |
|NF9 | Efficiency  | the company adds the remaining money in ex-employees' accounts to their severance pay | FR1.3 |
|NF10| Legality    | Capsule delivery includes also the receipt | FR5.0 |
# Use case diagram and use cases

```plantuml
left to right direction

actor Visitor as v
actor Employee as e

(Handle personal account) as hpa
(Log in/out into personal account) as liopea
(Check payments history) as ph
(Make capsule request) as mccra
(Add credit card) as cc
(Authorize account payment) as mp
(Update account balance) as uab
(Retrive capsules from manager) as rcfm
(Make cash payment to manager) as mpm

e -up- liopea
e -right- hpa
hpa .> uab : include
liopea .> ph : include
hpa .> cc : include
mp .> rcfm : include
e -- mccra 
e -- mp
mccra -> mpm : include
mccra -- v
mpm .> rcfm : include
```

```plantuml
left to right direction

actor Manager as m

(Log in/out from manager account) as lioma

(Handle employee's order) as ho
(Receive capsules request) as rcr
(Accept/Refuse capsule request) as arcr
(Deliver capsules) as gc
(Receive cash or account payment) as rcoap
(Accept Payment) as ap
(Handle employee's account) as ha
(Visualize employee's balance) as veb
(Show employee's payment history) as seeph
(Show left amount for each kind of capsule) as slaekc
(Handle wholesaler order) as hwo
(Pay Wholesaler) as mpw
(Control company cash account) as ccca
(Store orders for each kind of capsule) as mofkc

(Handle cash payment from visitors) as hcpfv
(Receive cash payments) as rpsms
(Deliver capsules to visitors) as gcv
(Search and check employee by ID) as sace
m -up- lioma
m -- ho
m -left- ha
m -right- hwo
m -up- hcpfv
m -up- slaekc

rcr <. ho : include
ho .> arcr : include
ho .> rcoap : include
arcr .> ap : include
ap .> gc : include

ha .> sace : include
sace .> veb : include
sace .> seeph : include

hwo .> mpw : include
hwo .> ccca : include
hwo .> mofkc : include

hcpfv .> rpsms : include
hcpfv .> gcv : include
```


## Use Cases

### Use case 1, UC1
| Actors Involved        | Visitor, Manager |
| ------------- |:-------------:| 
|  Precondition     | Visitor wants to buy some capsules and has enough money (€/euro) to do so. |  
|  Post condition     | Manager provides the capsules to the visitor. |
|  Nominal Scenario     | Visitor places an order on a touchscreen at the entrance of the office, then he reaches the manager in order to pay and retrive the order. |
|  Variants     | The manager is not available, the system is down or the wanted capsules are not available |

### Use case 2, UC2

| Actors Involved        | Employee, Manager |
| ------------- |:-------------:| 
|  Precondition     | The system is up and running and the Employee or the Manager want to log-in |  
|  Post condition     | Personal data is available to the actor |
|  Nominal Scenario     | The actor inputs username and password from host and has different display data based on his role on the company (Manager/Employee) |
|  Variants     | No account registered yet, password forgotten, wrong input parameters, faults on system database |

### Use case 3, UC3

| Actors Involved        | Employee|
| ------------- |:-------------:| 
|  Precondition     | The Employee has successfully logged in his account and wants to make a capsule request|  
|  Post condition     | He retrives the capsules from the manager |
|  Nominal Scenario     | The Employee chooses to pay by cash and after selecting the type and amount of capsules he submits the order and waits for manager approval |
|  Variants     | The manager does not accept the order, no cash available |

### Use case 4, UC4

| Actors Involved        | Employee|
| ------------- |:-------------:| 
|  Precondition     | The Employee has successfully logged in his account and after making a capsule request he wants to pay using his account credit|  
|  Post condition     | He retrives the capsules from the manager |
|  Nominal Scenario     | He checks the total of the order and authorizes the account payment |
|  Variants     | Account credit unavailable for balance negativity, the manager does not accept the order|

### Use case 5, UC5

| Actors Involved        | Employee|
| ------------- |:-------------:| 
|  Precondition     | The Employee has successfully logged in his account and wants to update his balance|  
|  Post condition     | He succeeds in updating his balance|
|  Nominal Scenario     | The employee adds the credit card data to his account and through the bank checkout he updates the account balance|
|  Variants     | Bank system down, Credit Card empty or invalid |

### Use case 6, UC6

| Actors Involved        |Manager|
| ------------- |:-------------:| 
|  Precondition     | The Manager has successfully logged in his account and wants to check an employee behaviour |  
|  Post condition     | The search is successful and positive|
|  Nominal Scenario     | The Manager checks if the employee balance respects the system constraints knowing the ID |
|  Variants     | The employee balance has been negative for more than 2 weeks, can't check employee's payment history for database faults, ex-employee, wrong ID |

### Use case 7, UC7

| Actors Involved        |Manager|
| ------------- |:-------------:| 
|  Precondition     | The Manager has successfully logged in his account and wants to check capsules' amounts|  
|  Post condition     | The lacking capsules will be ordered as soon as possible|
|  Nominal Scenario     | The Manager after receiving an order request checks for capsules availability|
|  Variants     | System crash, transactions not properly saved into the database |

### Use case 8, UC8

| Actors Involved        |Manager|
| ------------- |:-------------:| 
|  Precondition     | The Manager has successfully logged in his account and recives a visitor capsule request |  
|  Post condition     | The transaction is successfully completed|
|  Nominal Scenario     | The Manager checks if the order is feasible, prepares and delivers it to the visitor after being paid in cash (€/euro) |
|  Variants     | The Visitor has not enough money,desired capsules not available|

### Use case 9, UC9

| Actors Involved        |Manager|
| ------------- |:-------------:| 
|  Precondition     | The Manager needs to buy capsules |  
|  Post condition     | He places an order with a wholesaler |
|  Nominal Scenario     | The Manager controls the company cash account for the capsules, he stores an order into the system and delivers it to the wholesaler paying the due to amount |
|  Variants     | The Cash account is lower than the parcel, Wholesaler not available, System down |

### Use case 10, UC10

| Actors Involved        |Manager|
| ------------- |:-------------:| 
|  Precondition     | The Manager has successfully logged in his account and receives an employee's capsule request |  
|  Post condition     | He delivers the capsules to the employee |
|  Nominal Scenario     | The Manager checks if the order is feasible and the payment by cash must be in €/euro |
|  Variants     | The employee balance has been negative for more than 2 weeks, System crash or Database failures, desired capsules not available |

# Relevant scenarios
### Successful account payment 

Precondition: client is an employee, the manager has approved the request, the balance of the employee is positive and balance account is higher than -20€

Postcondition: the employee can recall capsules from the manager  

| Scenario ID: SC1        | Corresponds to  UC: Make account payment   |
| ------------- |:-------------:|
| Step#         | Description 	|
|1		        | Log in employee |
|2				| Make capsule request|
|3				| Wait for manager approval|
|4      		| If the account is in acceptable balance authorize the payment |
|5		        | Update the balance for the employee account |
|6        		| Post to the server the update of the balance for the employee |
|7              | Store the transaction |
|8              | Log out employee |

### Successful Update account balance

Precondition: client is an employee, he wants to update his account balance using his credit card

Postcondition: The employee's account balance is increased

| Scenario ID: SC2        | Corresponds to  UC: Update account balance |
| ------------- |:-------------:|
| Step#         | Description 	|
|1		        | Log in employee |
|2				| Add credit card|
|3				| Check for credit card validity |
|4      		| Input the desired amount |
|5              | Checkout through the bank system |
|6		        | Update the balance for the employee account |
|6        		| Post to the server the update of the balance for the employee |
|7              | Log out employee |

### Unsuccessful capsule request

Precondition: employee sends a capsule request

Postcondition: no order can be sent

| Scenario ID: SC3        | Corresponds to  UC: Handle employee order   |
| ------------- |:-------------:| 
| Step#         | Description 	|
|1          | Log in manager |
|2      | Receive employee capsules request |
|3      | Search and check employee ID |
|4      | Check the cash account |
|5      | Show the left amount for each type of capsules |
|6      | Amount of requested capsules not available | 
|7       | Reject employee request and payment due to capsule lacking |
|8     | Send a "capsule lacking " notification to the employee |
|9     | Log out manager |

### Unsuccessful account payment 

Precondition: client is an employee, the balance account is less than -20€

Postcondition: payment cannot be done

| Scenario ID: SC4       | Corresponds to  UC: Handle employee order   |
| ------------- |:-------------:|
| Step#         | Description 	|
|1          | Log in manager |
|2      | Receive employee capsules request |
|3      | Search and check employee ID |
|4      | Check the cash account |
|5      | Reject employee request because the balance account is less than -20€ |
|6     | Send a " too negative balance " notification to the employee |
|9     | Log out manager |


### Unsuccessful capsule order

Precondition: company cash account is under 100€, manager accepted employee capsule request

Postcondition: The manager can't execute th order

| Scenario ID: SC5      | Corresponds to  UC: Handle wholesaler order |
| ------------- |:-------------:|
| Step#         | Description 	|
|1       	    | Log in manager |
|2      		| make order for some type of capsule |
|3      		| Error sent by the system "total order price is below cash account!" |
|4      		| Request rejected |
|5      		| Request the company to supply the money needed |
|6      		| Log out manager |


# Glossary

```plantuml
class Employee {
    ID_E
    name
    surname
    role
}

class Visitor {
    ID_rand
}

class Account {
    ID_E 
    ID_A
    balance
    credit card
    time negative*
}

class Transaction {
    ID_E
    ID_rand*
    ID_T
    timestamp
    amount
    type
}

class Capsule {
    ID_C
    type
    quantity
}

class Order {
    ID_O
    ID_C
    quantity
    total price
    timestamp
    ID_W
}

class "Bank System" as bs {
    Name
}


class Wholesaler {
    ID_W
    name
}

class Inventory {
    ID_I
    ID_C
    cash account amount

}

class Manager {
    start date
    end date*
}

Employee "1" <|-- Manager

Employee "1" -- "*" Transaction : capsule request
Employee "1" -- "1" Account : owns 
Manager "1" -right- "1" Inventory : handles
Manager "1" -- "*" Order : handles
Manager "1" -- "*" Account : handles
Manager "1" -- "*" Transaction : accept/refuse
Capsule "*" -- "1" Inventory : contains
Capsule "1" -- "*" Order : contains
Account "1" -- "*" Transaction : contains
Wholesaler "1" -- "*" Order : takes

Transaction "*" -right- "1" Visitor : capsule request
bs "*" -- "*" Account : credit card validity/balance update
bs "*" -- "*" Transaction : Checkout
note top of Capsule : type must be in { Coffee, Arabic coffee, Tea, Lemon-tea, Camomile-tea }
note top of Visitor : ID_rand generated randomly to handle the transactions
```


# System Design
```plantuml
class Server{
+balance
+login_request()
+receive_Capsule_Request()
+redirect_CR_manager()
+payment_check()
+store_data()
+place_order_wholesaler()
+update_balance()
+show_info()
+update_nb_timers()
}
class Host{
+login()
+Capsule_Request()
+download_userdata()
+store_local_user()
+send_pwlost()
+upload_cc()
+contact_paymentserver()
}
class Database{
+push_credentials()
+pull_credentials()
+update_credentials()
+reset_password()
+store_transaction()
+manage_cc()
}
class TouchInterface{
+assign_IDrand()
+Capsule_Request()
+print_receipt()
}
LaTazza o-- "1" Server
LaTazza o-- Host
Server o-- "1" Database
Host o-- "1" TouchInterface
Printer -right-o TouchInterface
note top of Server 
    update_nb_timers() updates the timers of the users having negative balance
    show_info() answers to download_userdata() from Host
end note

```
