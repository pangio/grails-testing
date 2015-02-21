package com.pangio.mall.service

import com.pangio.mall.Employee
import com.pangio.mall.EmployeeService
import com.pangio.mall.Status
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/*
 * Unit Test for the the Service {@link EmployeeService}
 * See also {@link Employee}
 *
 * @author pangio
 */
@TestFor(EmployeeService)
@Mock([Employee])
class EmployeeServiceSpec extends Specification {

    def hiredPeople = null
    def unemployedPeople = null
    def anEmployee = null
    def qty = null

   /*
    * Data used within the scope of this Spec
    */
    def setup() {

        anEmployee = new Employee(name: "John", lastName: "Doe", personalId: "1FKODK45", status: Status.HIRED).save(flush: true)
        anEmployee = new Employee(name: "Clark", lastName: "Spencer", personalId: "2FKODK45", status: Status.HIRED).save(flush: true)
        anEmployee = new Employee(name: "Stephen", lastName: "Clayton", personalId: "3FKODK45", status: Status.HIRED).save(flush: true)
        anEmployee = new Employee(name: "Peter", lastName: "Malcom", personalId: "3FLODK45", status: Status.UNEMPLOYED).save(flush: true)
    }

    def "Should find hired people"() {

        when: "just look for hired people"
        qty = service.hiredPeople().size()

        then: "3 are found"
        assert qty == 3

        when: "another Employee is created and hired and look for hired people"
        anEmployee.save()
        qty = service.hiredPeople().size()

        then: "4 are found"
        service.hiredPeople().size() == 4

        where:
        anEmployee = new Employee(name: "Mark", lastName: "Brynn", personalId: "FYO4DK45", status: Status.HIRED)

    }

    def "Should find unemployed people"() {

        when: "just look for unemployed people"
        qty = service.unemployedPeople().size()

        then: "1 is found"
        qty == 1

        when: "another Employee is created but is unemployed and look for unemployed people"
        anEmployee.save()
        qty = service.unemployedPeople().size()

        then: "2 are found"
        qty == 2

        where:
        anEmployee = new Employee(name: "John", lastName: "Doe", personalId: "FKODK45", status: Status.UNEMPLOYED)
    }

    def "Should 'fire all' and 'hire all' and find the hired people"() {

        when: "everybody is fired and look for hired people"
        hiredPeople = Employee.findAllByStatus(Status.HIRED)
        hiredPeople.each {
            it.status = Status.UNEMPLOYED
            it.save(flush: true)
        }
        qty = service.hiredPeople().size()

        then: "0 is found"
        qty == 0

        when: "everybody is hired and look for hired people"
        unemployedPeople = Employee.findAllByStatus(Status.UNEMPLOYED)
        unemployedPeople.each {
            it.status = Status.HIRED
            it.save(flush: true)
        }
        qty = service.hiredPeople().size()

        then: "4 are found"
        qty == 4

    }

    def "Should 'fire all' and 'hire all' and find the unemployed people"() {

        when: "everybody is fired and look for unemployed people"
        hiredPeople = Employee.findAllByStatus(Status.HIRED)
        hiredPeople.each {
            it.status = Status.UNEMPLOYED
            it.save(flush: true)
        }
        qty = service.unemployedPeople().size()

        then: "4 are found"
        qty == 4

        when: "everybody is hired and look for unemployed people"
        unemployedPeople = Employee.findAllByStatus(Status.UNEMPLOYED)
        unemployedPeople.each {
            it.status = Status.HIRED
            it.save(flush: true)
        }
        qty = service.unemployedPeople().size()

        then: "0 is found"
        qty == 0
    }

   /*
    * Data clean up
    */
    def cleanup() {
        qty = null
        anEmployee = null
        hiredPeople = null
        unemployedPeople = null
    }
}
