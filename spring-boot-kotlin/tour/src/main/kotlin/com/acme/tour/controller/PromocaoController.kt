package com.acme.tour.controller

import com.acme.tour.model.ErrorMessage
import com.acme.tour.model.Promocao
import com.acme.tour.model.RespostaJSON
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value=["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<Any> {
       var promocao = promocaoService.getById(id);
       return if (promocao != null)
           return ResponseEntity(promocao, HttpStatus.OK)
       else
           return ResponseEntity(ErrorMessage("Promocao nao localizada", "promocao ${id} nao localizada"),
               HttpStatus.NOT_FOUND)
    }

    @PostMapping()
    fun create(@RequestBody promocao: Promocao) : ResponseEntity<RespostaJSON> {
        promocaoService.create(promocao);
        var resposta = RespostaJSON("OK", Date())
        return ResponseEntity(resposta, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (promocaoService.getById(id) != null) {
            promocaoService.delete(id);
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao : Promocao) : ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if (promocaoService.getById(id) != null) {
            promocaoService.update(promocao);
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "5") size: Int) : ResponseEntity<List<Promocao>>{
           val list = promocaoService.getAll(start = start, size = size)
           val status = if (list.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(list, status)
    }

    @GetMapping("/count")
    fun count() : ResponseEntity<Map<String,Long>> = ResponseEntity.ok().body(mapOf("count" to promocaoService.count()))

    @GetMapping("/ordenados")
    fun ordenados() = this.promocaoService.getAllSortedByLocal()

    @GetMapping("/menoresque100")
    fun getMenoresQue100() = this.promocaoService.findByPrecoMenorQue100()

}