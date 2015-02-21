package com.pangio.mall

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/*
 * Grails Controller {@link Store}
 *
 * @author pangio
 */
@Transactional(readOnly = true)
class StoreController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Store.list(params), model:[storeInstanceCount: Store.count()]
    }

    def show(Store storeInstance) {
        respond storeInstance
    }

    def create() {
        respond new Store(params)
    }

    @Transactional
    def save(Store storeInstance) {
        if (storeInstance == null) {
            notFound()
            return
        }

        if (storeInstance.hasErrors()) {
            respond storeInstance.errors, view:'create'
            return
        }

        storeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'storeInstance.label', default: 'Store'), storeInstance.id])
                redirect storeInstance
            }
            '*' { respond storeInstance, [status: CREATED] }
        }
    }

    def edit(Store storeInstance) {
        respond storeInstance
    }

    @Transactional
    def update(Store storeInstance) {
        if (storeInstance == null) {
            notFound()
            return
        }

        if (storeInstance.hasErrors()) {
            respond storeInstance.errors, view:'edit'
            return
        }

        storeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Store.label', default: 'Store'), storeInstance.id])
                redirect storeInstance
            }
            '*'{ respond storeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Store storeInstance) {

        if (storeInstance == null) {
            notFound()
            return
        }

        storeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Store.label', default: 'Store'), storeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'storeInstance.label', default: 'Store'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
