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

    @ExceptionHandler(SucursalNotFoundException.class)
    public ModelAndView handleSucursalNotFound(CustomException ex){
        ModelAndView mav = new ModelAndView("llista");
        mav.addObject("inexistent",ex.getDetail());
        return mav;
    }

    @ExceptionHandler({DataConflictException.class, IdException.class})
    public ModelAndView handleDataConflictive(CustomException ex){
        ModelAndView mav = new ModelAndView("customerror");
        mav.addObject("error",ex.getDetail());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleCriticalException(HttpServletRequest request, Exception ex){
        String msg = String.format("Request: %s raised %s",request.getRequestURL(),ex);
        LoggerFactory.getLogger(ExceptionManager.class).info(msg);
        ex.printStackTrace();
        ModelAndView mav = new ModelAndView("customerror");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("error",ex.getMessage());
        return mav;
    }
}
