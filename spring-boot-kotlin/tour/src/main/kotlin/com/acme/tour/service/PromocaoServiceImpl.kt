package com.acme.tour.service

import com.acme.tour.model.Promocao
import com.acme.tour.repository.PromocaoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class PromocaoServiceImpl(val promocaorepository: PromocaoRepository): PromocaoService {

    @CacheEvict("promocoes", allEntries = true)
    override fun create(promocao: Promocao) {
        this.promocaorepository.save(promocao)
    }

    @CacheEvict("promocoes", allEntries = true)
    override fun delete(id: Long) {
        this.promocaorepository.deleteById(id)
    }

    @CacheEvict("promocoes", allEntries = true)
    override fun update(promocao: Promocao) {
        this.create(promocao)
    }

    override fun searchByLocal(localFilter: String): List<Promocao> {
        return listOf();
    }

    @Cacheable("promocoes")
    override fun getAll(start: Int, size: Int): List<Promocao> {
        val pages = PageRequest.of(start, size)
        return this.promocaorepository.findAll(pages).toList()
    }

    override fun count(): Long  = this.promocaorepository.count()

    override fun getAllSortedByLocal(): List<Promocao> = this.promocaorepository.
        findAll(Sort.by("local").descending()).toList()

    override fun findByPrecoMenorQue100() : List<Promocao>{
        return this.promocaorepository.findByPrecoMenorQue100()
    }


    override fun getById(id: Long): Promocao? = this.promocaorepository.findById(id).orElse(null)
}

