package com.flip.urlshorterner.migration.changeunit

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.IndexDefinition
import org.springframework.data.mongodb.core.index.IndexOperations

class IndexBuilder {

    private val mongoTemplate: MongoTemplate
    private lateinit var entityClass: Class<*>
    private lateinit var collectionName : String

    constructor(mongoTemplate: MongoTemplate, entityClass: Class<*>){
        this.mongoTemplate = mongoTemplate
        this.entityClass = entityClass
    }

    constructor(mongoTemplate: MongoTemplate, collectionName: String) {
        this.mongoTemplate = mongoTemplate
        this.collectionName = collectionName
    }

    fun createIndex(indexBuilder: () -> IndexDefinition) {
        val index = indexBuilder()
        getIndexOps().addIndex(index)
    }

    fun createIndexes(indexBuilder: () -> List<IndexDefinition>) {
        val indexList = indexBuilder()
        val indexOps = getIndexOps()
        indexList.forEach { indexOps.addIndex(it) }
    }

    private fun getIndexOps(): IndexOperations =
        if ( ::entityClass.isInitialized )
            mongoTemplate.indexOps(entityClass)
        else mongoTemplate.indexOps(collectionName)
}

fun IndexOperations.addIndex(
    index: IndexDefinition
) {
    if (indexInfo.none { it.name == index["name"] }) {
        ensureIndex(index)
    }
}

private operator fun IndexDefinition.get(key: Any): Any? = indexKeys[key]

fun MongoTemplate.createIfNotExists(entityClass: Class<*>): IndexBuilder {
    val collectionExists = collectionExists(entityClass)
    if (!collectionExists) {
        createCollection(entityClass)
    }
    return IndexBuilder(this, entityClass)
}
