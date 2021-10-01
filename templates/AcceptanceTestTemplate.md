# Acceptance Testing Documentation template

Authors: Boscain Simone, Forte Valentina, Nadalin Marina, Onica Alexandru Valentin

Date: 26/05/2019

Version: 1.0

# Contents

- [Scenarios](#scenarios)

- [Coverage of scenarios](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Scenarios


| Scenario ID: SC1 | Corresponds to UC1                             |
| ---------------- | ---------------------------------------------- |
| Description      | Colleague uses one capsule of type 1           |
| Precondition     | account of C has enough money to buy capsule T |
| Postcondition    | account of C updated, count of T updated       |
| Step#            | Step description                               |
| 1                | Administrator selects capsule type T           |
| 2                | Administrator selects colleague C              |
| 3                | Deduce one for quantity of capsule T           |
| 4                | Deduce price of T from account of C            |

| Scenario ID: SC2 | Corresponds to UC1                                     |
| ---------------- | ------------------------------------------------------ |
| Description      | Colleague uses one capsule of type T, account negative |
| Precondition     | account of C has not enough money to buy capsule T     |
| Postcondition    | account of C updated, count of T updated               |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator selects colleague C                      |
| 3                | Deduce one for quantity of capsule T                   |
| 4                | Deduce price of T from account of C                    |
| 5                | Account of C is negative, issue warning                |

| Scenario ID: SC3 | Corresponds to UC2 |
| ---------------- | ------------------ |
| Description      | Visitor uses one capsule of type T, using cash         |
| Precondition     | Capsule T exists, visitor has no account               |
| Postcondition    | Account of LaTazza updated, count of T updated         |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator selects Visistor V                       |
| 3                | Deduce one for quantity of capsule T                   |
| 4                | LaTazza account balance has increased by price of T    |

| Scenario ID: SC4 | Corresponds to UC3                                                                     |
| ---------------- | ---------------------------------------------------------------------------------------|
| Description      | Administrator recharge account of a colleague C of a certain quantity                  |
| Precondition     | Colleague C has account                                                                |                                                                             |
| Postcondition    | account of C updated, balance of LaTazza updated                                       |
| Step#            | Step descritpion                                                                       |
| 1                | Administrator selects colleague C                                                      |
| 2                | Administrator inputs the amount to be recharged                                        |
| 3                | Collegue's and LaTazza's balances are increased by recharged amount                    |

| Scenario ID: SC5 | Corresponds to UC4 |
| ---------------- | ------------------ |
| Description      | Administrator records the purchase of N capsules T     |
| Precondition     | Capsule T exists, LaTazza balance is enough            |
| Postcondition    | Account of LaTazza updated, count of T updated         |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator inputs quantity N                        |
| 3                | Deduce LaTazza account balance by price of T * N       |  


| Scenario ID: SC6 | Corresponds to UC5 |
| ---------------- | ------------------ |
| Description      | Administrator wants to review the report about consumption and recharges of a colleague  C over a certain period of time |
| Precondition     | Colleague C has account                                |
| Postcondition    | Report correctly shown                                 |
| Step#            | Step description                                       |
| 1                | Administrator selects colleague C                      |
| 2                | Administrator selects start and endDate                | 

| Scenario ID: SC7 | Corresponds to UC6|
| ---------------- | ------------------ |
| Description      | Administrator wants to review the report about consumption and recharges of all colleagues and box purchases over a certain period of time |
| Precondition     || 
| Postcondition    | Report correctly shown                                 |
| Step#            | Step description                                       |
| 1                | Administrator selects start and endDate                |

| Scenario ID: SC8 | Corresponds to FR7 |
| ---------------- | ------------------ |
| Description      | Capsule T price increased so it has to be updated      |
| Precondition     | Capsule T exists            |
| Postcondition    | Capsule T updated       |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator inputs new price and Updates              |

| Scenario ID: SC9 | Corresponds to FR8 |
| ---------------- | ------------------ |
| Description      | Colleague C's name was wrongly input and Administrator updates it     |
| Precondition     | Colleague C has account              |
| Postcondition    | Account of C updated       |
| Step#            | Step description                                       |
| 1                | Administrator selects colleague C                      |
| 2                | Administrator inputs new name and Updates             |

### Use case 7, FR7 Manage types of capsules and prices

| Actors Involved        | Administrator |
| ------------- |:-------------:| 
|  Precondition     | Capsule T exists |  
|  Post condition     | Capsule T price is successfully updated |
|  Nominal Scenario     | Capsule T price increased so Administrator selects the capsule and modifies the price |
|  Variants     | |

### Use case 8, FR8 Manage colleagues and accounts

| Actors Involved        | Administrator |
| ------------- |:-------------:| 
|  Precondition     | Colleague C has account  |  
|  Post condition     | Account of C updated |
|  Nominal Scenario     | Administrator had miss typed the name of C and corrects it |
|  Variants     | |

# Coverage of Scenarios

### 

| Scenario ID | Functional Requirements covered | API Test(s) | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | ----------- |
| 1           | FR1                             |  it.polito.latazza.data.TestDataImpl.testSellCapsules()           |   BuyAccountEmployee        |
| 2           | FR1                             | it.polito.latazza.data.TestDataImpl.testSellCapsules()            |   BuyAccountEmployee          |
| 3         |      FR2                           |   it.polito.latazza.data.TestDataImpl.testSellCapsulesToVisitors()      | BuyVisitor    |
| 4         |  FR3                               |it.polito.latazza.data.TestDataImpl.testRechargeAccount()             |    RechargeEmployeeAccount        |
| 5         | FR4                                |    it.polito.latazza.data.TestDataImpl.testBuyBoxes()         |        BuyBoxes     |
| 6       |     FR5                            |  it.polito.latazza.data.TestDataImpl.testGetEmployeeReport()     |       GetEmployeeReport      |
| 7         | FR6                                |   it.polito.latazza.data.TestDataImpl.testGetReport()         |      GetReport       |
| 8      |     FR7                            |  it.polito.latazza.data.TestDataImpl.testUpdateBeverage()    |        UpdateBeverage     |
| 9     |     FR8                            | it.polito.latazza.data.TestDataImpl.testUpdateEmployee()    |       UpdateEmployee     |


# Coverage of Non Functional Requirements

### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|              NF2              |          it.polito.latazza.data.TestDataImpl.testPerformance() |
