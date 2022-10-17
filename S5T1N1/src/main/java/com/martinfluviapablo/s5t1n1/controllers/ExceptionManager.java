package com.martinfluviapablo.s5t1n1.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionManager {

    /*
    TODO:
        Manage DataConflictException
        Manage SucursalNotFoundException
        Manage general exception / Critical exception (500)
        Errors due @Valid wrong should be managed here?
        Manage wrong url path
            Manage NoHandlerFoundException {when there's no method to handle that path)
            *DispatcherServlet must have property throwExceptionIfNoHandlerFound to true
     */
}
