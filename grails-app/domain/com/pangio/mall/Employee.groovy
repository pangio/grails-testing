package com.pangio.mall

/*
 * Grails Domain class {@link Employee}
 *
 * @author pangio
 */
class Employee {

    String name
    String lastName
    String personalId
    Status status = Status.UNEMPLOYED

    static constraints = {
        name matches: /[A-Z].*/ , nullable: false
        lastName matches: /[A-Z].*/ , nullable: false
        personalId nullable: false
        status nullable: false
    }

    @Override
    String toString(){
        return lastName + ', '+ name
    }
}
