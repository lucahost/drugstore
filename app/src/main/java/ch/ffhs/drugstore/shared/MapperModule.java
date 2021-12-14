package ch.ffhs.drugstore.shared.mappers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class MapperModule {

    @Provides
    @Singleton
    DrugstoreMapper provideDtoMapper() {
        return DrugstoreMapper.INSTANCE;
    }
}
