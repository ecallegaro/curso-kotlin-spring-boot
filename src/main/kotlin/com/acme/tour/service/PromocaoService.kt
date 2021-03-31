package com.acme.tour.service

import com.acme.tour.model.Promocao

interface PromocaoService {
    fun getById(id: Long): Promocao?
    fun create(promocao: Promocao)
    fun delete(id: Long)
    fun update(promocao: Promocao)
    fun searchByLocal(localFilter:String):List<Promocao>
    fun getAll(start: Int, size: Int): List<Promocao>
    fun count(): Long
    fun getAllSortedByLocal(): List<Promocao>
    fun findByPrecoMenorQue100(): List<Promocao>
}