package ProyectoFinal

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
@Secured(['ROLE_ADMIN'])
class AsignacionController {

    AsignacionService asignacionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond asignacionService.list(params), model:[asignacionCount: asignacionService.count()]
    }

    def show(Long id) {
        respond asignacionService.get(id)
    }

    def create() {
        respond new Asignacion(params)
    }

    def save(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            asignacionService.save(asignacion)
        } catch (ValidationException e) {
            respond asignacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect asignacion
            }
            '*' { respond asignacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond asignacionService.get(id)
    }

    def update(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            asignacionService.save(asignacion)
        } catch (ValidationException e) {
            respond asignacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect asignacion
            }
            '*'{ respond asignacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        asignacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
