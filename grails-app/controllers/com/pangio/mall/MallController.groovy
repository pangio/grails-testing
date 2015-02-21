package com.pangio.mall

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/*
 * Grails Controller {@link Mall}
 *
 * @author pangio
 */
@Transactional(readOnly = true)
class MallController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Mall.list(params), model:[mallInstanceCount: Mall.count()]
    }

    def show(Mall mallInstance) {
        respond mallInstance
    }

    def create() {
        respond new Mall(params)
    }

    @Transactional
    def save(Mall mallInstance) {
        if (mallInstance == null) {
            notFound()
            return
        }

        if (mallInstance.hasErrors()) {
            respond mallInstance.errors, view:'create'
            return
        }

        mallInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mallInstance.label', default: 'Mall'), mallInstance.id])
                redirect mallInstance
            }
            '*' { respond mallInstance, [status: CREATED] }
        }
    }

    def edit(Mall mallInstance) {
        respond mallInstance
    }

    @Transactional
    def update(Mall mallInstance) {
        if (mallInstance == null) {
            notFound()
            return
        }

        if (mallInstance.hasErrors()) {
            respond mallInstance.errors, view:'edit'
            return
        }

        mallInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Mall.label', default: 'Mall'), mallInstance.id])
                redirect mallInstance
            }
            '*'{ respond mallInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Mall mallInstance) {

        if (mallInstance == null) {
            notFound()
            return
        }

        mallInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Mall.label', default: 'Mall'), mallInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mallInstance.label', default: 'Mall'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
