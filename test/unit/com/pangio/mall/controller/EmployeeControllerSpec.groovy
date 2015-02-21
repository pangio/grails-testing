package com.pangio.mall.controller

import com.pangio.mall.Employee
import com.pangio.mall.EmployeeService
import com.pangio.mall.EmployeeController
import com.pangio.mall.Status
import grails.test.mixin.*
import spock.lang.*

/*
 * Unit Test for the controller {@link EmployeeController}
 * See also {@link Employee}
 *
 * @author pangio
 */
@TestFor(EmployeeController)
@Mock([Employee, EmployeeService])
class EmployeeControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'John'
        params["lastName"] = 'Doe'
        params["personalId"] = '348943fjf'
        params["status"] = Status.UNEMPLOYED
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.employeeInstanceList
            model.employeeInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.employeeInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def employee = new Employee()
            employee.validate()
            controller.save(employee)

        then:"The create view is rendered again with the correct model"
            model.employeeInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            employee = new Employee(params)

            controller.save(employee)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/employee/show/1'
            controller.flash.message != null
            Employee.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def employee = new Employee(params)
            controller.show(employee)

        then:"A model is populated containing the domain instance"
            model.employeeInstance == employee
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def employee = new Employee(params)
            controller.edit(employee)

        then:"A model is populated containing the domain instance"
            model.employeeInstance == employee
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 302 error is returned"
            response.status == 302
            response.redirectedUrl == '/employee/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def employee = new Employee()
            employee.validate()
            controller.update(employee)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.employeeInstance == employee

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            employee = new Employee(params).save(flush: true)
            controller.update(employee)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/employee/show/$employee.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 302 is returned"
            response.status == 302
            response.redirectedUrl == '/employee/index'
            flash.message != null

        when:"A domain instance is created"
            response.status == 200
            response.reset()
            populateValidParams(params)
            def employee = new Employee(params).save(flush: true)

        then:"It exists"
            Employee.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(employee)

        then:"The instance is deleted"
            Employee.count() == 0
            response.redirectedUrl == '/employee/index'
            flash.message != null
    }

    void "Test that the save action does not save a null instance"() {
        when:"The save action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.save(null)

        then:"A 302 is returned"
            response.status == 302
            response.redirectedUrl == '/employee/index'
            flash.message != null
    }

    void "Test that the hired action is called"() {

        given:
            new Employee(name: "Adam", lastName: "Malcom", personalId: "11N", status: Status.HIRED).save(flush: true)
            new Employee(name: "John", lastName: "Morris", personalId: "12C", status: Status.HIRED).save(flush: true)
            new Employee(name: "Craig", lastName: "Niall", personalId: "16", status: Status.UNEMPLOYED).save(flush: true)

        when:"The hired action is called"
            controller.hired()

        then:"2 result are found"
            view == '/employee/index'
            response.status == 200
            model.employeeInstanceList
            model.employeeInstanceCount == 2
    }

    void "Test that the unemployed action is called"() {

        given:
            new Employee(name: "Adam", lastName: "Malcom", personalId: "11N", status: Status.HIRED).save(flush: true)
            new Employee(name: "John", lastName: "Morris", personalId: "12C", status: Status.HIRED).save(flush: true)
            new Employee(name: "Craig", lastName: "Niall", personalId: "16", status: Status.UNEMPLOYED).save(flush: true)

        when:"The unemployed action is called"
            controller.unemployed()

        then:"1 result is found"
            view == '/employee/index'
            response.status == 200
            model.employeeInstanceList
            model.employeeInstanceCount == 1
    }
}
