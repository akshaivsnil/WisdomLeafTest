package com.akshai.tutorials.wisdomleaftest.mapper

import com.akshai.tutorials.wisdomleaftest.domain.ListDomainModel
import com.akshai.tutorials.wisdomleaftest.entity.ListApiResponse


/**
 * Created by ATM on 20/October/2022
 */

class ListModelMapper : EntityMapper<ListApiResponse,ListDomainModel> {

    override fun mapFromEntity(entity: ListApiResponse): ListDomainModel {
        return ListDomainModel(
            id = entity.id,
            author = entity.author,
            url = entity.url,
            downloadUrl = entity.downloadUrl,
            width = entity.width,
            height = entity.height,
        )
    }

    fun fromEntityList(initial : List<ListApiResponse>) : List<ListDomainModel>{
        return initial.map { mapFromEntity(it) }
    }

}