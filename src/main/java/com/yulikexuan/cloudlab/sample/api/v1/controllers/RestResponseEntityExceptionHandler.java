//: com.yulikexuan.cloudlab.sample.api.v1.controllers.RestResponseEntityExceptionHandler.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
        ResponseEntityExceptionHandler {

    /*
     * MAY combine the ExceptionHandler annotation with @ResponseStatus for a
     * specific HTTP error status.
     *
     * Handler methods which are annotated with this annotation are allowed to
     * have very flexible signatures. They may have parameters of the following
     * types, in arbitrary order:
     *   - An exception argument: declared as a general Exception or as a more
     *     specific exception. This also serves as a mapping hint if the
     *     annotation itself does not narrow the exception types through its
     *     value().
     *   - WebRequest or NativeWebRequest.
     *     Allows for generic request parameter access as well as
     *     request/session attribute access, without ties to the native Servlet
     *     API.
     *   - Request and/or response objects (typically from the Servlet API).
     *     You may choose any specific request/response type, e.g.
     *     ServletRequest / HttpServletRequest.
     *   - Locale for the current request locale (determined by the most
     *     specific locale resolver available, i.e. the configured LocaleResolver
     *     in a Servlet environment).
     *   - InputStream / Reader for access to the request's content.
     *     This will be the raw InputStream/Reader as exposed by the Servlet API.
     *   - OutputStream / Writer for generating the response's content.
     *     This will be the raw OutputStream/Writer as exposed by the Servlet API.
     *   - Model as an alternative to returning a model map from the handler
     *     method. Note that the provided model is not pre-populated with
     *     regular model attributes and therefore always empty, as a convenience
     *     for preparing the model for an exception-specific view.
     *   - Session object: typically HttpSession.
     *     An argument of this type will enforce the presence of a corresponding
     *     session. As a consequence, such an argument will never be null.
     *     Note that session access may not be thread-safe, in particular in a
     *     Servlet environment: Consider switching the "synchronizeOnSession"
     *     flag to "true" if multiple requests are allowed to access a session
     *     concurrently.
     *
     * The following return types are supported for handler methods:
     *   - An HttpEntity<?> or ResponseEntity<?> object (Servlet-only) to set
     *     response headers and content.
     *     The ResponseEntity body will be converted and written to the response
     *     stream using message converters.
     *   - A ModelAndView object (from Servlet MVC).
     *   - A Model object, with the view name implicitly determined through a
     *     RequestToViewNameTranslator.
     *   - A Map object for exposing a model, with the view name implicitly
     *     determined through a RequestToViewNameTranslator.
     *   - A View object.
     *   - A String value which is interpreted as view name.
     *   - @ResponseBody annotated methods (Servlet-only) to set the response
     *     content. The return value will be converted to the response stream
     *     using message converters.
     *   - void if the method handles the response itself
     *     (by writing the response content directly, declaring an argument of
     *     type ServletResponse / HttpServletResponse for that purpose)
     *     or if the view name is supposed to be implicitly determined through
     *     a RequestToViewNameTranslator (not declaring a response argument in
     *     the handler method signature).
     *
     * ResponseEntity:
     *   - Extension of HttpEntity that adds a HttpStatus status code.
     *   - Used in RestTemplate as well @Controller methods.
     *   - In RestTemplate, this class is returned by getForEntity() and
     *     exchange():
     */
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(
            NotFoundException exception, WebRequest webRequest) {

        return new ResponseEntity<>(exception.toString(), new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

}///:~