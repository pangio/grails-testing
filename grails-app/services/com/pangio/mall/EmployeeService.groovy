package com.pangio.mall

import grails.transaction.Transactional

/*
 * Grails Service {@link EmployeeService}
 * See {@link Employee}
 *
 * @author pangio
 */
@Transactional
class EmployeeService {

    /*
     * Returns a list of 'Hired' people
     *
     * @return list of hired people
     */
    def List<Employee> hiredPeople() {
        Employee.findAllByStatus(Status.HIRED)
    }

    /*
     * Returns a list of 'Unemployed' people
     *
     * @return list of unemployed people
     */
    def List<Employee> unemployedPeople () {
        Employee.findAllByStatus(Status.UNEMPLOYED)
    }
}
