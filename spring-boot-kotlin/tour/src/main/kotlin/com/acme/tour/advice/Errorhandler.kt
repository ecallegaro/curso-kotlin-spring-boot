package com.acme.tour.advice

import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class Errorhandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest,
    servletResponse: HttpServletResponse, exception: Exception) :ResponseEntity<ErrorMessage>{
        var error = ErrorMessage("Json ERROR", exception.message ?: "invalid json")
        return ResponseEntity( error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoNotFoundExceptionHandler(servletRequest: HttpServletRequest,
                                  servletResponse: HttpServletResponse, exception: Exception) :ResponseEntity<ErrorMessage>{
        var error = ErrorMessage("Promocao nao localizada", exception.message !!)
        return ResponseEntity( error, HttpStatus.NOT_FOUND)
    }
}