package controller;

import domain.MessageService;
import domain.PersonService;

public class ControllerFactory {
	
    public RequestHandler getController(String key, PersonService model, MessageService service) {
        return createHandler(key, model, service);
    }
    
	private RequestHandler createHandler(String handlerName, PersonService model, MessageService service) {
		RequestHandler handler = null;
		try {
			Class<?> handlerClass = Class.forName("controller."+ handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (RequestHandler) handlerObject;
			handler.setModel(model, service);
		} catch (Exception e) {
			throw new RuntimeException("Deze pagina bestaat niet!!!!");
		}
		return handler;
	}


}
