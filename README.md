# Finances Analyser Tool - Receipts Service

### Purpose

In development is an analyser tool that will extract purchase information from images of receipts. 
That information can be used to reflect upon past purchases (how often has a certain item been 
purchased and how has the price of that item changed over time) and plan future purchases (are past 
purchase habits sustainable, or is some cutting back required).

### List of Components
The final solution will be a UI that calls API endpoints providing management of Receipts, Merchants 
(and their MerchantStores), as well as being able to query the purchase history of items.

An anticipated service breakdown would be:

* receipts: upload of receipts images; definition of merchants and their stores, relating them 
* purchases

### Work Outstanding

* Use of jobrunr-spring-boot-starter so that image conversion and receipt text parsing can happen asynchronously. Post request can then return immediately after image file is saved locally. 
* Connection to a persistence layer (Repository and DAO classes)
* Mocking of persistence layer
* Unit tests
* MVC tests
* OpenAPI contract definitions and documentation
* Improved documentation of overall architecture
* Improved documentation of data modelling choices
* Processes for deploying into a hosted environment
