package pl.camp.it;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.camp.it.model.Response;
import pl.camp.it.model.User;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        /************** GET ****************/
        RestTemplate restTemplate = new RestTemplate();

        String ulr = "http://localhost:8080/cos/77";

        User user = restTemplate.getForObject(ulr, User.class);

        System.out.println(user.getLogin());
        System.out.println(user.getPassword());

        /******** POST *********/

        String url = "http://localhost:8080/user2";

        User user1 = new User();
        user1.setLogin("Zbyszek");
        user1.setPassword("zbychu11");

        User responseUser = restTemplate.postForObject(url, user1, User.class);

        System.out.println(responseUser.getLogin());
        System.out.println(responseUser.getPassword());

        /******** HTTP ENTITY **********/
        String url2 = "http://localhost:8080/user/{id}";

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("id", "55");

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url2)
                .queryParam("param1", "wartosc1")
                .queryParam("param2", "wartosc2")
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header1", "jakis naglowek 1");
        httpHeaders.add("header2", "jakis inny naglowek 2");

        User user2 = new User();
        user2.setLogin("TrudnyUser");
        user2.setPassword("trudnehaslo");

        HttpEntity<User> httpEntity = new HttpEntity<>(user2, httpHeaders);

        ResponseEntity<Response> responseEntity = restTemplate.exchange(uriComponents.toUriString(),
                HttpMethod.PUT,
                httpEntity,
                Response.class,
                uriParams);
        Response responseObject = responseEntity.getBody();

        System.out.println(responseObject.getInfo());
        System.out.println(responseObject.getCode());

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Jest fajnie jest kod 200");
        }
    }
}
