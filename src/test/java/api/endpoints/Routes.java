package api.endpoints;

/*https://petstore.swagger.io/v2/user --POST
https://petstore.swagger.io/v2/user/{username}---GET
https://petstore.swagger.io/v2/user/{username}---PUT
https://petstore.swagger.io/v2/user/{username}--DELETE

 */
public class Routes {

    public static String base_url="https://petstore.swagger.io/v2";

    public static String create_user=base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String update_user=base_url+"/user/{username}";
    public static String delete_user=base_url+"/user/{username}";

}
