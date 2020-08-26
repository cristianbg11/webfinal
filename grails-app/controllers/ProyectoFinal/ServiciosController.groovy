package ProyectoFinal

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ServiciosController {
    static layout = 'layout'
    def springSecurityService
    def index() {


    }
    def payment() {
        def evento=Evento.get(params.id);
        def monto=evento.costo;

        def pedido=new Pedido();
        pedido.monto=monto;
        pedido.fecha=new Date();
        pedido.evento=evento;
        pedido.estado="Pendiente";
        pedido.usuario=springSecurityService.currentUser as User
        pedido.save();
        ["pedido":pedido,"evento":evento]

    }
    def checkout() {
        def evento=Evento.get(params.id);
        def monto=evento.costo;
        ["evento":evento]

    }
}
