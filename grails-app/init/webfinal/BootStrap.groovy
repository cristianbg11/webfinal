package webfinal

import ProyectoFinal.*

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def clienteRole = new Role(authority: 'ROLE_CLIENTE').save(flush: true)
        def empleladoRole = new Role(authority: 'ROLE_EMPLEADO').save(flush: true)

        def admin = new User(username: 'admin', enabled: true, password: 'admin')
        admin.email = "cristianbg011@gmail.com"
        admin.nombre = "Cristian"
        admin.save(flush: true)

        def usuarioRol = new UserRole()
        usuarioRol.user = admin
        usuarioRol.role =adminRole
        usuarioRol.save(flush: true)

        def empleado1 = new User(username: 'empleado1', enabled: true, password: 'empleado1')
        admin.email = "afomillow@gmail.com"
        admin.nombre = "empleado1"
        admin.save(flush: true)

        def empleadoRol = new UserRole()
        empleadoRol.user = empleado1
        empleadoRol.role =empleadoRol
        empleadoRol.save(flush: true)

        def evento = new Evento()
        evento.nombre = "Pre-Boda"
        evento.costo = 1000
        evento.save(flush: true)

        evento = new Evento()
        evento.nombre = "Boda"
        evento.costo = 5000
        evento.save(flush: true)

        evento = new Evento()
        evento.nombre = "Cumpleanos"
        evento.costo = 3000
        evento.save(flush: true)

        evento = new Evento()
        evento.nombre = "Video Evento"
        evento.costo = 4000
        evento.save(flush: true)
        //print(UserRole.findAll)
    }
    def destroy = {
    }
}
