package com.pangio.mall.functional

import geb.spock.GebReportingSpec
import pages.*
import spock.lang.Stepwise

@Stepwise
class MallSpec extends GebReportingSpec {
	
	def "should list 3 existing Malls"() {
		when:
		to ListPage
		then:
		mallRows.size() == 3
	}
	
	def "should create a new Mall"() {
		when:
		newMallButton.click()
		then:
		at CreatePage
	}

	def "should fill in the form of the Mall"() {
		when:
		name = "Another Big Mall"
		address = "Somewhere, Dublin 2"
		createButton.click()
		then:"check the created Mall"
		at ShowPage
	}

	def "should check the Mall was created"() {
		expect:
		name == "Another Big Mall"
		address == "Somewhere, Dublin 2"
	}

	def "should edit a Mall"() {
		when:
		editButton.click()
		then:
		at EditPage
		when:
		updateButton.click()
		then:
		at ShowPage
	}

    def "should select a Mall from list"() {
        given:
        def aMall = 1
        when:
        to ListPage
        mallRow(aMall).showLink.click()
        then:
        at ShowPage
    }

	def "should validate the first Mall from the list"() {
		when:
		to ListPage
		then:
		mallRows.size() == 4
		def row = mallRow(0)
		row.name == "Big Mall"
		row.address== "44 Parnell St, Dublin 1"
	}

	def "should select and delete an existing Mall"() {
        given:
        def deletedId
		mallRow(1).showLink.click()
        when:
		withConfirm { deleteButton.click() }
		then:
		at ListPage
        // TODO get the id of the entity
//		message == "Mall $deletedId deleted"
		mallRows.size() == 3
	}
}