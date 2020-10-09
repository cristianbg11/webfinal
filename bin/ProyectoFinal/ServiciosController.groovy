package ProyectoFinal

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@Secured(['ROLE_CLIENTE', 'ROLE_ADMIN'])
@Transactional
class ServiciosController {
    static layout = 'layout'
    def springSecurityService
    def reportService

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def index() {
        /*
        //def getBatch(String id) {
            String url = "http://localhost:4567/eventos"

            def resp = new RestBuilder().get(url) {
                header 'Authorization', 'Basic base64EncodedUsername&Password'
            }
        List<Evento> eventos= JSON.parse(resp.body.toString());
        //}
        */
        List<Evento> eventos = Evento.findAll()
        ["eventos":eventos]
    }
    def mispedidos(){
        //List<Pedido> pedidos = Pedido.findAllByUsuario(springSecurityService.currentUser as User)
        User usuario = springSecurityService.currentUser as User
        def pedidos = Pedido.findAll()
        def evento = Evento.findAll()
        ["listpedidos":pedidos]
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
        System.out.println(pedido.estado)
        //crearfactura()
        def factura=new Factura();
        factura.monto=pedido.monto;
        factura.pedido=pedido;
        factura.estado=0;
        factura.save();
        //post a notificacion de servicio
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
        form.add("mensaje", "Pedido Nuevo Confirmado")
        form.add("id", pedido.id+"")
        form.add("correo", "cristianbg011@gmail.com")

        def resp = new RestBuilder().post("http://localhost:4566/notificacion"){
            //auth System.getProperty("artifactory.user"), System.getProperty("artifactory.pass")
            /*contentType "application/json"
            json {
                mensaje = "Pedido Nuevo Confirmado"
                id = 1//pedido.id
                correo="cristianbg011@gmail.com"
            }*/
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        System.out.println("hola")
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
