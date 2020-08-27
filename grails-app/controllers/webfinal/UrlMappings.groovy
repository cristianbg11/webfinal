package webfinal

import ProyectoFinal.ServiciosController

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "servicios", action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
