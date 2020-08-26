package ProyectoFinal

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
@Secured(['ROLE_ADMIN'])
@Transactional
class ServiciosController {
    static layout = 'layout'
    def springSecurityService

    def index() {
        //def getBatch(String id) {
            String url = "http://localhost:4567/eventos"

            def resp = new RestBuilder().get(url) {
                header 'Authorization', 'Basic base64EncodedUsername&Password'
            }
        List<Evento> eventos= JSON.parse(resp.body.toString());
        //}
        ["eventos":eventos]
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

        //crearfactura()
        def factura=new Factura();
        factura.monto=pedido.monto;
        factura.pedido=pedido;
        factura.estado=0;
        factura.save();
        //post a notificacion de servicio
        def resp = new RestBuilder().post("http://localhost:4566/notificacion"){
            //auth System.getProperty("artifactory.user"), System.getProperty("artifactory.pass")
            contentType "application/json"
            json {
                mensaje = "Pedido Nuevo Confirmado"
                id = pedido.id
                correo="cristianbg011@gmail.com"
            }
        }

        /*def resp = new RestBuilder().post('http://localhost:4566/notificacion?mensaje={menaje}&id={id}&correo={correo}') {
            urlVariables ["someDude", 1, "enmanuel.bueno@gmail.com"]
        }*/


        ["pedido":pedido,"evento":evento]

    }
    def checkout() {
        def evento=Evento.get(params.id);
        def monto=evento.costo;

        ["evento":evento]

    }
    def crearfactura(){
        def pedido=Pedido.get(params.id);
        //crea factura
        def factura=new Factura();
        factura.monto=pedido.monto;
        factura.pedido=pedido;
        factura.estado=0;
        factura.save();



    }

    def report(){
        reportService.testReport()
    }
    //mostrar factura en jasper
    def verfactura() {

    }
}
