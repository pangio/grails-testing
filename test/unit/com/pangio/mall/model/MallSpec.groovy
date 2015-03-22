package com.pangio.mall.model

import com.pangio.mall.Mall
import com.pangio.mall.Store
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/*
 * Unit Test for the the Domain class {@link Mall}
 *
 * @author pangio
 */
@TestFor(Mall)
class MallSpec extends Specification {

    def mall

    def setup() {
        mall = new Mall()
        mall.name = 'Great mall'
        mall.address = '44 Parnell St, Dublin 1'
    }

    def cleanup() {
        mall = null
    }

    void 'Test Mall constraints'() {
        when: 'Mall is created with name and address'

        then: 'validation succeeds'
        mall.validate()
        !mall.hasErrors()

        when: 'an empty Mall is created'
        mall = new Mall()
        mall.name = 'Some Mall'

        then: 'validation fails'
        !mall.validate()
        mall.hasErrors()
    }

    void 'Test the Stores quantity'() {

        when: 'Mall is created'

        then: "Store's size is 0"
        assertEquals 0, mall.stores.size()
        mall.validate()
        !mall.hasErrors()

        when: 'a Store is opened'
        mall.stores.add(new Store())

        then: "Store's size is 1"
        assertEquals 1, mall.stores.size()
        mall.validate()
        !mall.hasErrors()

        when: 'another Store is opened'
        mall.stores.add(new Store())

        then: "Stores's size is 2"
        assertEquals 2, mall.stores.size()
        mall.validate()
        !mall.hasErrors()
    }
    void 'Test Mall attributes'() {

        when: 'attributes are set'

        then: "the attributes have expected values"
        assertEquals mall.name, 'Great mall'
        assertEquals mall.address, '44 Parnell St, Dublin 1'

        mall.validate()
        !mall.hasErrors()

        when: 'the Name is set'
        mall.name = 'SOMETHING'

        then: "the model is updated"
        assertEquals mall.name, 'SOMETHING'

        mall.validate()
        !mall.hasErrors()
    }
}
