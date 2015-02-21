package com.pangio.mall.model

import com.pangio.mall.Employee
import com.pangio.mall.Mall
import com.pangio.mall.Store
import com.pangio.mall.Type
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/*
 * Unit Test for the the Domain class {@link Store}
 *
 * @author pangio
 */
@TestFor(Store)
@Mock([Employee])
class StoreSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void 'Test the Store constraints'() {

        when: 'a Store is created with name and type'
        def store = new Store()
        store.name = 'Some Store'
        store.type = Type.SHOES
        store.mall = new Mall()

        then: 'validation succeeds'
        store.validate()
        !store.hasErrors()

        when: 'an empty Store is created'
        store = new Store()

        then: 'validation fails'
        !store.validate()
        store.hasErrors()

        when: 'Store is created without Mall'
        store = new Store()
        store.name = 'New Store'
        store.type = Type.FAST_FOOD

        then: 'validation fails'
        !store.validate()
        store.hasErrors()
    }

    void 'Test the Employees quantity'() {

        when: 'a Store is created'
        def store = new Store()
        store.name = 'Some Store'
        store.type = Type.SHOES
        store.mall = new Mall()

        then: "Employee's size is 0"
        assertEquals 0, store.employees.size()
        store.validate()
        !store.hasErrors()

        when: 'an Employee is hired'
        store.addToEmployees(new Employee()).save(flush: true)

        then: "Employee's quantity is 1"
        assertEquals 1, store.employees.size()
        store.validate()
        !store.hasErrors()

        when: 'another Employee is hired'
        store.addToEmployees(new Employee()).save(flush: true)

        then: "Employee's quantity is 2"
        assertEquals 2, store.employees.size()
        store.validate()
        !store.hasErrors()
    }

    void 'Test Store attributes'() {

        when: 'attributes are set'
        def store = new Store()
        store.name = 'Some Store'
        store.type = Type.SHOES
        store.mall = new Mall()

        then: "the attributes have expected values"
        assertEquals 0, store.employees.size()
        assert store.name, 'Some Store'
        assert store.toString(), 'Some Store'
        assert store.type, Type.SHOES

        store.validate()
        !store.hasErrors()

        when: 'the Name is set'
        store.name = 'SOMETHING'

        then: "toString() should return the same"
        assert store.toString(), 'SOMETHING'
        assert store.toString(), store.name

        store.validate()
        !store.hasErrors()
    }
}
