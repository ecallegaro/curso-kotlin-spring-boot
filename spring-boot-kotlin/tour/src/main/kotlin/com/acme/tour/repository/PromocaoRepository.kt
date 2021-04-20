package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
public interface PromocaoRepository : PagingAndSortingRepository<Promocao, Long>{

    @Query("select p from com.acme.tour.model.Promocao p where p.preco <= 100")
    fun findByPrecoMenorQue100(): List<Promocao>
}