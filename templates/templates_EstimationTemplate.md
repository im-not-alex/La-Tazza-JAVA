# Project Estimation  template

Authors: Boscain Simone, Forte Valentina, Nadalin Marina, Onica Alexandru Valentin

Date: 02/06/2019

Version: 1.0

# Contents

- [[Data from your LaTazza project]

- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Data from your LaTazza project

###
|||
| ----------- | ------------------------------- | 
|         Total person hours  worked by your  team, considering period March 5 to May 26, considering ALL activities (req, des, code, test,..)    |  282 |             
|Total Java LoC delivered on May 26 (only code, without Exceptions, no Junit code) |763 |
| Total number of Java classes delivered on May 26 (only code, no Junit code, no Exception classes)| 8|
| Productivity P =| 11 |
|Average size of Java class A = | 96 |

# Estimate by product decomposition



### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| Estimated n classes NC (no Exception classes)  |       9                      |             
| Estimated LOC per class  (Here use Average A computed above )      |     96                   | 
| Estimated LOC (= NC * A) | 864 |
| Estimated effort  (person days) (Here use productivity P)  |        3                              |      
| Estimated calendar time (calendar weeks) (Assume team of 4 people, 8 hours per day, 5 days per week ) |      4              |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort    |             
| ----------- | ------------------------------- | 
| Requirements | 30|
| Design |20|
| Coding | 60|
| Testing |  40|


###
![gantt](templates/Gantt_chart.png)

