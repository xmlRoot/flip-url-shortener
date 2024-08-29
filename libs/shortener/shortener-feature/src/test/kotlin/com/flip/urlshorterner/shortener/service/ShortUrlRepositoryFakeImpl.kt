package com.flip.urlshorterner.shortener.service

import com.flip.urlshorterner.shortener.model.document.ShortUrl
import com.flip.urlshorterner.shortener.persistence.ShortUrlRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery
import java.util.Optional
import java.util.function.Function

object ShortUrlRepositoryMockImpl : ShortUrlRepository {

    val db : HashMap<String, ShortUrl> = HashMap()

    override fun findByUuid(uuid: String): ShortUrl? {
        TODO("Not yet implemented")
    }

    override fun findByShortenedUrl(shortUrl: String): ShortUrl {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl> save(entity: S): S {
        db[entity.id] = entity
        return entity
    }

    override fun <S : ShortUrl?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Optional<ShortUrl> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<ShortUrl> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<ShortUrl> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<ShortUrl> {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<String>): MutableList<ShortUrl> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: ShortUrl) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<String>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<ShortUrl>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FetchableFluentQuery<S>, R>
    ): R {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> insert(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : ShortUrl?> insert(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

}