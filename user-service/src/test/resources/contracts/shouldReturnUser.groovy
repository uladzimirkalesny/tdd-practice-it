package contracts
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return an user")
    request {
        method GET()
        url "/users/1"
    }
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(id: 1, name: "Uladzimir", isActive: true, age: 29)
    }
}