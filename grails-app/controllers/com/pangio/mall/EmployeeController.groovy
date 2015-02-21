package com.pangio.mall

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/*
 * Grails Controller {@link Employee}
 *
 * @author pangio
 */
@Transactional(readOnly = true)
class EmployeeController {

   /*
    * Autowired service .
    * See {@link org.springframework.beans.factory.annotation.Autowire}
    */
    def employeeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Employee.list(params), model:[employeeInstanceCount: Employee.count()]
    }

    def show(Employee employeeInstance) {
        respond employeeInstance
    }

    def create() {
        respond new Employee(params)
    }

    @Transactional
    def save(Employee employeeInstance) {
        if (employeeInstance == null) {
            notFound()
            return
        }

        if (employeeInstance.hasErrors()) {
            respond employeeInstance.errors, view:'create'
            return
        }

        employeeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'employeeInstance.label', default: 'Employee'), employeeInstance.id])
                redirect employeeInstance
            }
            '*' { respond employeeInstance, [status: CREATED] }
        }
    }

    def edit(Employee employeeInstance) {
        respond employeeInstance
    }

    @Transactional
    def update(Employee employeeInstance) {
        if (employeeInstance == null) {
            notFound()
            return
        }

        if (employeeInstance.hasErrors()) {
            respond employeeInstance.errors, view:'edit'
            return
        }

        employeeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Employee.label', default: 'Employee'), employeeInstance.id])
                redirect employeeInstance
            }
            '*'{ respond employeeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Employee employeeInstance) {

        if (employeeInstance == null) {
            notFound()
            return
        }

        employeeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Employee.label', default: 'Employee'), employeeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'employeeInstance.label', default: 'Employee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

   /*
    * Returns a list of 'Hired' people
    *
    * @return list of hired people
    */
    def hired() {
        def hiredPeople = employeeService.hiredPeople()
        render(view: "index", model: [employeeInstanceList: hiredPeople, employeeInstanceCount: hiredPeople.size()])
    }

   /*
    * Returns a list of 'Unemployed' people
    *
    * @return list of unemployed people
    */
    def unemployed() {
        def unemployedPeople = employeeService.unemployedPeople()
        render(view: "index", model: [employeeInstanceList: unemployedPeople, employeeInstanceCount: unemployedPeople.size()])
    }
}
