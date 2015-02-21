package com.pangio.mall

/*
 * Domain class {@link Store}
 * Has many {@link Employee}s
 *
 * @author pangio
 */
class Store {

    String name
    Type type
    Set<Employee> employees = new ArrayList<Employee>()

    static belongsTo = [mall: Mall]
    static hasMany = [employees: Employee]

    static constraints = {
        name matches: /[A-Z].*/ , nullable: false
        type nullable: false
        mall nullable: false
        employees nullable: true
    }

    @Override
    String toString(){
        return name
    }
}
