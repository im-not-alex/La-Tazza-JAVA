# Inspection log

| Date | 09/04/2019 |
| :--- | :---: |
| Place | Polito |
| Object of inspection | La Tazza |
| **Participants and roles** <br>Moderator <br>Author <br>Reader <br>Reader <br>Reader |  <br>Luca Ardito, Maurizio Morisio<br>Torta Savoia<br>Gabriele G.<br>Matteo P.<br>Torta Savoia |
| Start time | 16:00 |
| End time | 19:00 |

| Problem id | Location  | Problem description (where in the document/code)| Status  (open, assigned, closed)| Type (see note) | Gravity  (minor, normal, major)|
| :--- | :---: | :---: | :---: | :---: | :---: |
| 1 | Context Diagram | Wholesaler : does he interact directly with the system? |Closed | Incorrect Fact | normal|
| 2 | Context Diagram | IT Consultant : does he interact directly with the system? | Closed | Incorrect Fact |minor |
| 3 | FR1.5, NF8  |Add Credit Card : where is the check for credit card validity? |  Closed | Omission |normal |
| 4 | NF6 | “Or go below 100€”. Why 100€? | Closed |Extraneous Information |minor |
| 5 | FR1.3, NF9, UC5 | What happens to the account balance of an ex-employee? | Closed | Omission | minor |
| 6 | UC Diagram | Retrive capsules from manager . Isn't it a functionality used in the context of “Make cash payment to manager”? | Closed |Incompleteness |minor |
| 7 | UC4 | “submits the payment”. What if the manager rejects the request? | Closed | Omission |normal |
| 8 | UC8 | “order not feasible”.  What does it mean? | Closed | Ambiguity |minor |
| 9 | UC8 | Manager not availble :why? | Closed | Contradiction |normal |
| 10 | SC3 | Step 5 : can the manager accept the request ? | Closed | Incorrect Fact | normal |
| 11 | System Design | Safe :is it an automatic machine interacting with the other components of the system or just with the manager? | Closed | Inconsistency | normal |
| 12 | Stakeholders, System Design | Inventory : is it an active element of the system? | Closed | Inconsistency | normal |


**Defect types:**

- Omission/ incompleteness
- Incorrect Fact
- Inconsistency/contradiction
- Ambiguity
- Extraneous Information
<br>&emsp;■ Overspecification (design)
- Un-reality
- Un-verifiability
- Un-traceability