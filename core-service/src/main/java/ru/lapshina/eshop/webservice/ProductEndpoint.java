package ru.lapshina.eshop.webservice;

import lombok.AllArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.lapshina.eshop.service.ProductService;
import ru.lapshina.eshop.soap.GetAllProductsRequest;
import ru.lapshina.eshop.soap.GetAllProductsResponse;

@Endpoint
@AllArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.lapshina.com/spring/ws/product";
    private final ProductService productService;


    /*
        Пример запроса: POST http://localhost:8082/market/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.lapshina.com/spring/ws/product">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAllXml().forEach(response.getProductxml()::add);
        return response;
    }
}
