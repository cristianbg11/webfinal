package ProyectoFinal

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.springframework.http.HttpStatus

class RegisterController {
    static layout = 'layout'
    def index() {

    }
    def save(User user) {
            try {
                //def role = Role.get(params.role.id)
                user.save()


                def clienteRole = Role.findByAuthority('ROLE_CLIENTE')
                def usuarioRol = new UserRole()
                usuarioRol.user = user
                usuarioRol.role =clienteRole
                usuarioRol.save(flush: true)

                redirect(action: 'index', controller: 'servicios')



            } catch (ValidationException e) {
                flash.message = "Register Failed"
                redirect action: "index"
                return
            }
    }

    /*
    @Transactional
    def save(){
        def user = new User(params)
        if (user == null) {
            render status: HttpStatus.NOT_FOUND
            return
        }

        def clienteRole = Role.findByAuthority('ROLE_CLIENTE')
        def usuarioRol = new UserRole()
        usuarioRol.user = user
        usuarioRol.role = clienteRole

        user.save flush: true

        usuarioRol.save(flush: true)

    }
    */
}
