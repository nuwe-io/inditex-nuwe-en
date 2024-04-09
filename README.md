# Introduction

INDITEX is expanding its team, and for this reason a challenge has been created to find the talent they need to incorporate into their team.

In this case, the challenge will be **Backend** with Java 21. In order to develop everything proposed, which will be detailed later, a project will be provided as a starting point, where you can find:

- **Skeleton**: basic structure that serves as a basis for not starting from scratch. 
- **Unit Test**: a series of unit tests are provided so that participants can check their progress.
- `data.sql` and `schema.sql`: SQL files that serve as the basis for developing the challenge. It includes the schema and test data. It is imported automatically. They are located under `src/main/resources`.

## **Challenge**

Five phases are proposed for this event:

1. The first phase corresponds to complete the class `OrderRepository.java` in **/repositories** with SQL queries.
2. The second phase corresponds to the first part of the algorithmic challenge. The classes `Order.java` and `OrderController.java` will have to be completed.
3. The third phase corresponds to the enhancement of the application using **Red Hat Data Grid**.
4. The fourth phase corresponds to the second part of the algorithmic challenge, where an extra degree of difficulty is added to what was proposed in the second phase.
5. The fifth phase corresponds to documentation where certain aspects of the project will be detailed and the theoretical questions proposed in **Phase 4** will be answered.

### **First phase - SQL**

An almost complete API is provided as a starting point.
In this phase, participants will have to:

1. Complete the classes within **/repositories** to be able to make **SQL queries** to the DB. It is necessary to implement the SQL queries of `OrderRepository.java`. Specifically the endpoints: `findByNameProduct`, `findByNameCustomer` and `findByNameCustomerAndNameProduct`, as well as `findAll` and `findById`.

### **Second phase - API and first algorithm**

In this part you are asked to create an efficient algorithm that solves the following problem:

Inditex has created a system of Lockers spread over several locations. In order to optimise the delivery routes, they have restricted them to a single trip per driver, so the driver must drop off all the orders placed in a single Locker that is the most optimal for **all** customers. The idea is to minimise the total distance customers have to travel to pick up their orders.

Example:

We have three customers: A, B, C and five Lockers: 1, 2, 3, 4 and 5. Suppose A places an order and his nearest Locker is 1, this is assigned. However, customer B places a new order, so the Locker should be changed to the one that is more optimal for A and B.

To do this you will need to:

1. Complete the `Order.java` class within **/entities**.
2. Complete the development of the class `OrderController.java` inside **/controllers**. This endpoint must have the following behaviour:

	- Receives data: customer id and product id as query parameters. It updates the stock of the product.
	- It takes the address of the ordering customer and automatically updates the Locker resulting from the algorithm created.

**INFORMATION TO NOTE: All points (addresses) will be based on a Cartesian coordinate plane.**

**The fourth phase will require functional code to be developed, in particular that developed in this second phase to be taken as a starting point.**

### Third Phase - Red Hat Data Grid

This phase proposes to integrate Red Hat Data Grid to improve the performance and scalability of the application. In order to carry out this phase, it is necessary to have completed the first two phases and have a clean and functional code base.

As a starting point, an initial configuration of the embedded version of DataGrid is provided. It can be found in `DataGridConfiguration.java`.

It is proposed to include this extra functionality to the data access layer (repositories), and the following actions should be carried out:

1. Cache annotations in repositories
2. Cache configuration
3. Identification of queries to be cached
4. Delete entries that have not been queried within 2 minutes[OPTIONAL]
5. Limit maximum memory to X (value to be decided) [OPTIONAL]
6. Tests and adjustments

In this phase, participants are also tested on their ability to work with a service that they will have to document as they go along. Initial documentation is provided, but it will be up to the participant to find everything they need for the development of this phase.

[Data Grid Doc - General](https://access.redhat.com/documentation/es-es/red_hat_data_grid/8.4/html-single/data_grid_spring_boot_starter/index)

**For this phase, you can add whatever is necessary to the base code, without altering its behaviour.**

**IMPORTANT NOTICE: if you get stuck in this exercise, it is recommended that you skip to the next phase and return to it once it is completed.**

### Fourth Phase - Second Algorithm

As an added difficulty, the same scenario as **Phase 2** is proposed but with the addition of obstacles in the plane. Points will be provided through which the path cannot pass, so that direct paths (straight lines) can no longer be made when encountering one of these points, it will be necessary to take another route and these must be planned around these obstacles. The most optimal Locker could vary from the results obtained in Phase 2. The final objective will be:

To develop an algorithm capable of finding the most optimal Locker for all customers in the same way as in **Phase 2**, but taking into account the obstacles. This gives a more realistic approach to creating a solution to this problem.

**INFORMATION TO NOTE: All points (addresses) will be based on a Cartesian coordinate plane.**

**Additional information**

The algorithm must take into account that it must be scalable, i.e. it must be efficient for a few points as well as for millions of points.

Whatever algorithm is implemented, an answer to the following questions must be added to the documentation:

- **How would you assemble a more computationally optimal algorithm that supports millions of customers and orders?**
- **Can you explain the cyclomatic complexity of the algorithm you have developed?**

### Fifth phase - Documentation

The following should be included in the documentation:

- **Usage** of the project
- **Installation steps** of the project for its correct use.
- **Logic of the algorithm** used:
	- Name of the algorithm
	- Reason for using the algorithm
	- How it helps in scaling the application
- **Answers to the questions proposed in Phase 4**

## **Project tree**
```bash
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── inditex
│   │   │           ├── controllers
│   │   │           │   ├── CustomerController.java
│   │   │           │   ├── LockerController.java
│   │   │           │   ├── ObstacleController.java (2)
│   │   │           │   ├── OrderController.java    (2)
│   │   │           │   └── ProductController.java
│   │   │           ├── DataGridConfiguration.java
│   │   │           ├── entities
│   │   │           │   ├── Customer.java
│   │   │           │   ├── Locker.java
│   │   │           │   ├── Obstacle.java
│   │   │           │   ├── Order.java              (2)
│   │   │           │   └── Product.java
│   │   │           ├── InditexApplication.java
│   │   │           ├── JacksonConfiguration.java
│   │   │           ├── repositories
│   │   │           │   ├── CustomerRepository.java
│   │   │           │   ├── LockerRepository.java
│   │   │           │   ├── ObstacleRepository.java
│   │   │           │   ├── OrderRepository.java    (1)
│   │   │           │   └── ProductRepository.java
│   │   │           └── ServletInitializer.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── data.sql [Contains example data]
│   │       └── schema.sql [Contains the DB schema]
│   └── test
│       ├── java
│       │   └── com
│       │       └── inditex
│       │           ├── CustomerControllerUnitTest.java
│       │           ├── CustomerJdbcUnitTest.java
│       │           ├── LockerControllerUnitTest.java
│       │           ├── LockerJdbcUnitTest.java
│       │           ├── ObstacleControllerUnitTest.java
│       │           ├── ObstacleJdbcUnitTest.java
│       │           ├── OrderControllerUnitTest.java
│       │           ├── OrderJdbcUnitTest.java
│       │           ├── ProductControllerUnitTest.java
│       │           └── ProductJdbcUnitTest.java
│       └── resources
│           ├── application.properties
│           └── testdata.sql
```

(1): classes to be completed in phase 1.

(2): classes to be completed in phase 2.

As many utility classes as necessary can be created.

**The code provided is fully functional and nothing needs to be modified during the first two phases. Just add whatever is missing to complete the proposed objectives.**