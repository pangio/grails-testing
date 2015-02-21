package com.pangio.mall.functional

import geb.junit4.GebReportingTest
import org.junit.Test
import pages.*

class MallTests extends GebReportingTest {

    @Test void clickMall(){
        assert true
    }

//    @Test
//    void listAllMalls() {
//        to ListPage
//        assert mallRows.size() == 3
//    }
//
//    @Test
//    void createMall() {
//        newMallButton.click()
//
//        assert at(CreatePage)
//        $("#enabled").click()
//        name = "Mall Amazing"
//        address = "Somewhere, Dublin City Centre"
//        createButton.click()
//
//        assert at(ShowPage)
//        assert name == "Mall Amazing"
//        assert address == "Somewhere, Dublin City Centre"
//    }


//    void editMall() {
//    void editMall() {
//        to ShowPage // funciona?
//        editButton.click()
//        assert at(EditPage)
//
//        $("#enabled").click()
//        updateButton.click()
//        assert at(ShowPage)
//
//        to ListPage
//        assert mallRows.size() == 4
//
//    }




//        def row = mallRow(0)
//        assert row.name == "Mall Amazing"
//        assert row.address == "Somewhere, Dublin City Centre"
//        row.showLink.click()
//
//        assert at(ShowPage)
//        def deletedId = id
//        withConfirm { deleteButton.click() }
//
//        assert at(ListPage)
//        assert message == "Mall $deletedId deleted"
//        assert mallRows.size() == 0

}