package com.flip.urlshorterner.migration.changeunit

import com.flip.urlshorterner.shortener.model.document.ShortUrl
import io.mongock.api.annotations.BeforeExecution
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackBeforeExecution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Index

@ChangeUnit(id = "create_url_shortener_ddl", order = "1", author = "url_shortener", systemVersion = "1")
class AddUrlShortener {

    @BeforeExecution
    fun initializeDB(mongo: MongoTemplate) {
        mongo.createIfNotExists(ShortUrl::class.java).createIndexes { listOf(
            Index("_uuid", Sort.Direction.ASC).unique(),
            Index("shortenedUrl", Sort.Direction.ASC).unique()
                .on("targetUrl", Sort.Direction.ASC)
        ) }
    }

    @Execution
    fun execution(){}
    @RollbackExecution
    fun rollbackExecution(){}
    @RollbackBeforeExecution
    fun rollbackBeforeExecution(){}

}