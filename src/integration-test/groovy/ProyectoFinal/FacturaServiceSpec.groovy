package ProyectoFinal

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FacturaServiceSpec extends Specification {

    FacturaService facturaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Factura(...).save(flush: true, failOnError: true)
        //new Factura(...).save(flush: true, failOnError: true)
        //Factura factura = new Factura(...).save(flush: true, failOnError: true)
        //new Factura(...).save(flush: true, failOnError: true)
        //new Factura(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //factura.id
    }

    void "test get"() {
        setupData()

        expect:
        facturaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Factura> facturaList = facturaService.list(max: 2, offset: 2)

        then:
        facturaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        facturaService.count() == 5
    }

    void "test delete"() {
        Long facturaId = setupData()

        expect:
        facturaService.count() == 5

        when:
        facturaService.delete(facturaId)
        sessionFactory.currentSession.flush()

        then:
        facturaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Factura factura = new Factura()
        facturaService.save(factura)

        then:
        factura.id != null
    }
}
