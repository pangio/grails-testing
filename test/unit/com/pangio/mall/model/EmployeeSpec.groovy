package com.pangio.mall.model

import com.pangio.mall.Employee
import grails.test.mixin.TestFor
import spock.lang.Specification

/*
 * Unit Test for the the Domain class {@link Employee}
 *
 * @author pangio
 */
@TestFor(Employee)
class EmployeeSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void 'Test Employee constraints'() {
        when: 'Employee is created with name and address'
        def employee = new Employee()
        employee.name = 'John'
        employee.lastName = 'Doe'
        employee.personalId = "IOSO4FKDO"

        then: 'validation succeeds'
        employee.validate()
        !employee.hasErrors()

        when: 'an empty Employee is created'
        employee = new Employee()
        employee.name = 'John'

        then: 'validation fails'
        !employee.validate()
        employee.hasErrors()
    }
}
