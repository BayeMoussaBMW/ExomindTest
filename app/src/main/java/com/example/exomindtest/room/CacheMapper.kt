package com.example.exomindtest.room

import com.example.exomindtest.model.UserItem
import com.example.exomindtest.util.EntityMapper
import javax.inject.Inject

class CacheMapper

@Inject
constructor():
    EntityMapper<UserItemCacheEntity, UserItem> {
    override fun mapFromEntity(entity: UserItemCacheEntity): UserItem {
        return UserItem(
            id = entity.id,
            name = entity.name,
            username = entity.username,
            email = entity.email,
            address = entity.address,
            phone = entity.phone,
            website = entity.website,
            company = entity.company
        )
    }

    override fun mapToEntity(domainModel: UserItem): UserItemCacheEntity {
        return UserItemCacheEntity(
            id = domainModel.id,
            name = domainModel.name,
            username = domainModel.username,
            email = domainModel.email,
            address = domainModel.address,
            phone = domainModel.phone,
            website = domainModel.website,
            company = domainModel.company
        )
    }

    fun mapFromEntityList(entities: List<UserItemCacheEntity>): List<UserItem>{
        return entities.map { mapFromEntity(it) }
    }
}