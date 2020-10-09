package ProyectoFinal

import grails.gorm.services.Service

@Service(Factura)
interface FacturaService {

    Factura get(Serializable id)

    List<Factura> list(Map args)

    Long count()

    void delete(Serializable id)

    Factura save(Factura factura)

}