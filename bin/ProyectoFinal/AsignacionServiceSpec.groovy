package ProyectoFinal

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AsignacionServiceSpec extends Specification {

    AsignacionService asignacionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Asignacion(...).save(flush: true, failOnError: true)
        //new Asignacion(...).save(flush: true, failOnError: true)
        //Asignacion asignacion = new Asignacion(...).save(flush: true, failOnError: true)
        //new Asignacion(...).save(flush: true, failOnError: true)
        //new Asignacion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //asignacion.id
    }

    void "test get"() {
        setupData()

        expect:
        asignacionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Asignacion> asignacionList = asignacionService.list(max: 2, offset: 2)

        then:
        asignacionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        asignacionService.count() == 5
    }

    void "test delete"() {
        Long asignacionId = setupData()

        expect:
        asignacionService.count() == 5

        when:
        asignacionService.delete(asignacionId)
        sessionFactory.currentSession.flush()

        then:
        asignacionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Asignacion asignacion = new Asignacion()
        asignacionService.save(asignacion)

        then:
        asignacion.id != null
    }
}
