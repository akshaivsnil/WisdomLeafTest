package com.akshai.tutorials.wisdomleaftest.mapper

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

}