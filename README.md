# grails-testing
Grails MALL webapp is a sample application to show how to test a Grails app.

# Stack
*  Grails 2.3.7
*  coverage plugin
*  Spock / Geb / Selenium Firefox driver

# Run the example

From Terminal :

* clone the repo
```
https://github.com/pangio/grails-testing.git
```
* Run
```
grails run-app
```
* Test 
```
grails test-app unit: integration: functional: -coverage

The above command executes Unit, Integration and Functional tests and creates all the coverage reports. 
Note the Functional tests would attempt to execute the application and the Bootstrap.groovy required for the test. 
So avoid to use the same port for these 2 processes simultaneously
```
* Reports
```
grails reports
/target/test-reports/cobertura/index.html

covarage plugin reports
/target/test-reports/html/index.html
```

