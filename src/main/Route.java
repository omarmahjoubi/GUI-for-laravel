package main;

/**
 * Created by Moslah_Hamza on 27/03/2017.
 */
public class Route {

    private String uri;
    private String method;
    private String action;
    private String middleware;

    public Route(String uri, String method, String action, String middleware) {
        super();
        this.uri = uri;
        this.method = method;
        this.action = action;
        this.middleware = middleware;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMiddleware() {
        return middleware;
    }

    public void setMiddleware(String middleware) {
        this.middleware = middleware;
    }
}
