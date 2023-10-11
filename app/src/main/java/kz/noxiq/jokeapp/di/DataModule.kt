package kz.noxiq.jokeapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.noxiq.jokeapp.data.joke.repository.BaseJokeRepository
import kz.noxiq.jokeapp.domain.joke.repository.JokeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsJokeRepository(baseJokeRepository: BaseJokeRepository): JokeRepository
}