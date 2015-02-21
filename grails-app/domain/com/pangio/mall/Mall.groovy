package com.pangio.mall

/*
 * Grails Domain class {@link Mall}
 * Has many {@link Store}s
 *
 * @author pangio
 */
class Mall {

    String name
    String address
    Set<Store> stores = new ArrayList<Store>()

    static hasMany = [stores : Store]

    static constraints = {
        name matches: /[A-Z].*/ , nullable: false
        address nullable: false
        stores nullable: true
    }

    @Override
    String toString(){
        return name
    }
}
