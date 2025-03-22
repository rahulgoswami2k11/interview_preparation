package designpatterns;

class Request {

}

class RequestHandler {
    private RequestHandler next;

    public RequestHandler(Request request) {
    }

    public void add(RequestHandler handler) {
        if(this.next != null) {
            next.add(handler);
        } else {
            this.next = handler;
        }
    }

    public void handle() {
        if(next != null) {
            this.next.handle();
        }
    }
}

class RequestValidationHandler extends RequestHandler {

    public RequestValidationHandler(Request request) {
        super(request);
    }

    public void handle() {
        System.out.println("Validating request");
        super.handle();
    }
}

class RequestIdGenerationHandler extends RequestHandler {
    public RequestIdGenerationHandler(Request request) {
        super(request);
    }

    public void handle() {
        System.out.println("Generating requestId");
        super.handle();
    }
}

class RequestAuthenticationHandler extends RequestHandler {
    public RequestAuthenticationHandler(Request request) {
        super(request);
    }

    public void handle() {
        System.out.println("Authenticating request");
        super.handle();
    }
}

class RequestAuthorizationHandler extends RequestHandler {
    public RequestAuthorizationHandler(Request request) {
        super(request);
    }

    public void handle() {
        System.out.println("Authorizing request");
        super.handle();
    }
}

public class RequestHandlerCOR {
    public static void main(String[] args) {
        Request request = new Request();
        RequestHandler requestHandler = new RequestHandler(request);
        requestHandler.add(new RequestValidationHandler(request));
        requestHandler.add(new RequestIdGenerationHandler(request));
        requestHandler.add(new RequestAuthenticationHandler(request));
        requestHandler.add(new RequestAuthorizationHandler(request));

        requestHandler.handle();
    }
}
