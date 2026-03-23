package com.fooddelivery.app.di

import com.fooddelivery.app.data.local.*
import com.fooddelivery.app.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt 仓库模块 - 架构师配置
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    // 仓库类使用 @Inject 构造函数，Hilt 会自动提供实例
}
