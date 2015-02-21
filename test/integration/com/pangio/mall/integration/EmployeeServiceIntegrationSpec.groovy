package com.pangio.mall.integration

import com.pangio.mall.Employee
import com.pangio.mall.Status
import grails.test.spock.IntegrationSpec
/*
 * Grails Integration Test
 * See {@link EmployeeService} and {@link IntegrationSpec}
 *
 * @author pangio
 */
class EmployeeServiceIntegrationSpec extends IntegrationSpec {

    def employeeService

    def setup() {

            new Employee(name: "Adam", lastName: "Malcom", personalId: "11N", status: Status.HIRED).save(flush: true)
            new Employee(name: "John", lastName: "Morris", personalId: "12C", status: Status.HIRED).save(flush: true)
            new Employee(name: "Craig", lastName: "Niall", personalId: "16", status: Status.UNEMPLOYED).save(flush: true)

    }

    def "Look for Hired people"() {

        when:"The look for hired people"
            def people = employeeService.hiredPeople()

        then:"2 are found"
            assert people.size(), 2
    }

    def "Look for Unemployed people"() {

        when:"The look for unemployed people"
            def people = employeeService.unemployedPeople()

        then:"1 is found"
            assert people.size(), 1
    }
}
