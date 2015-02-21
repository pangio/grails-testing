import com.pangio.mall.Employee
import com.pangio.mall.Mall
import com.pangio.mall.Status
import com.pangio.mall.Store

class BootStrap {

    def init = { servletContext ->

        createMalls()
        createEmployees()
        createStores()
        employSomePeople()

    }

    def employSomePeople() {

        def emp = Employee.findByPersonalId("33884911N")
        emp.status = Status.HIRED
        emp.save(flush: true)

        def kfc = Store.findByName("KFC")
        kfc.addToEmployees(emp)
        kfc.save(flush: true)

        emp = Employee.findByPersonalId("33884915")
        emp.status = Status.HIRED
        emp.save(flush: true)

        def nike = Store.findByName("Nike")
        nike.addToEmployees(emp)
        nike.save(flush: true)
    }

    def createMalls() {
        System.out.println('Creating Malls')
        [[ name: "Big Mall", address: "44 Parnell St, Dublin 1"],
        [ name: "H&M Mall", address: "18 O'Connell's St, Dublin 1"],
        [ name: "The Gallery", address: "65 Ballyboden St, Dublin 16"]]
                .each { it ->
            def mall = new Mall(name: it.name, address: it.address).save()
        }
        assert Mall.list().size() == 3
    }

    def createStores() {
        System.out.println('Creating Stores')
        [[ name: "KFC", type: com.pangio.mall.Type.FAST_FOOD],
        [ name: "Burger King", type: com.pangio.mall.Type.FAST_FOOD],
        [ name: "Mc Donalds", type: com.pangio.mall.Type.FAST_FOOD],
        [ name: "Pennys", type: com.pangio.mall.Type.CLOTHING],
        [ name: "Nike", type: com.pangio.mall.Type.SPORT]]
                .each { it ->
            def store = new Store(name: it.name, type: it.type, mall: Mall.findByName("Big Mall")).save()
        }

        [[ name: "Adidas", type: com.pangio.mall.Type.RUNNING],
        [ name: "Aldo", type: com.pangio.mall.Type.SHOES],
        [ name: "Adidas", type: com.pangio.mall.Type.CLOTHING],
        [ name: "Joe's", type: com.pangio.mall.Type.RESTAURANT],
        [ name: "H&M ", type: com.pangio.mall.Type.CLOTHING],
        [ name: "KFC D1", type: com.pangio.mall.Type.FAST_FOOD]]
                .each { def s ->
            def store = new Store(name: s.name, type: s.type, mall: Mall.findByName("The Gallery")).save()
        }
        assert Store.list().size() == 11

    }

    def createEmployees() {
        System.out.println('Creating Employees')
        [[ name: "Adam", lastName: "Malcom", personalId: "11N"],
        [ name: "John", lastName: "Morris", personalId: "12C" ],
        [ name: "Anthony", lastName: "Morgan", personalId: "13K" ],
        [ name: "Angus", lastName: "Oswald", personalId: "14A" ],
        [ name: "Colin", lastName: "Oliver", personalId: "15" ],
        [ name: "Craig", lastName: "Niall", personalId: "16" ],
        [ name: "Curt", lastName: "Murray", personalId: "17B" ],
        [ name: "Chris", lastName: "Owen", personalId: "18P" ]]
                .each { it ->
            def employee = new Employee(name: it.name, lastName: it.lastName, personalId: "338849"+ it.personalId).save()
        }
        assert Employee.list().size() == 8
    }

    def destroy = {
    }
}
