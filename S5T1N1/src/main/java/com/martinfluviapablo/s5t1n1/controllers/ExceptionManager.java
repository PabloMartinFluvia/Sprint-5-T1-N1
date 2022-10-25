package com.martinfluviapablo.s5t1n1.controllers;

import com.martinfluviapablo.s5t1n1.model.services.exceptions.CustomException;
import com.martinfluviapablo.s5t1n1.model.services.exceptions.DataConflictException;
import com.martinfluviapablo.s5t1n1.model.services.exceptions.IdException;
import com.martinfluviapablo.s5t1n1.model.services.exceptions.SucursalNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

    @ExceptionHandler(SucursalNotFoundException.class)
    public ModelAndView handleSucursalNotFound(CustomException ex){
        ModelAndView mav = new ModelAndView("llista");
        mav.addObject("inexistent",ex.getCAUSE());
        return mav;
    }

    @ExceptionHandler({DataConflictException.class, IdException.class})
    public ModelAndView handleDataConflictive(CustomException ex){
        ModelAndView mav = new ModelAndView("customerror");
        mav.addObject("error",ex.getCAUSE());
        return mav;
    }

    /*
    This exceptions must be handled when are "discovered"
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleCriticalException(HttpServletRequest request, Exception ex){
        LoggerFactory.getLogger(ExceptionManager.class).error(
                "Request: "+ request.getRequestURL()+" raised "+ex);
        ex.printStackTrace();
        ModelAndView mav = new ModelAndView("customerror");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("error",ex.getMessage());
        return mav;
    }
}
