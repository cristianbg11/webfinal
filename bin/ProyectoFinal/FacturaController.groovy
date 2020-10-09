package ProyectoFinal

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
class FacturaController {

    FacturaService facturaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond facturaService.list(params), model:[facturaCount: facturaService.count()]
    }

    def show(Long id) {
        respond facturaService.get(id)
    }

    def create() {
        respond new Factura(params)
    }

    def save(Factura factura) {
        if (factura == null) {
            notFound()
            return
        }

        try {
            facturaService.save(factura)
        } catch (ValidationException e) {
            respond factura.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'factura.label', default: 'Factura'), factura.id])
                redirect factura
            }
            '*' { respond factura, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond facturaService.get(id)
    }

    def update(Factura factura) {
        if (factura == null) {
            notFound()
            return
        }

        try {
            facturaService.save(factura)
        } catch (ValidationException e) {
            respond factura.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'factura.label', default: 'Factura'), factura.id])
                redirect factura
            }
            '*'{ respond factura, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        facturaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'factura.label', default: 'Factura'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'factura.label', default: 'Factura'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
