package ch.ffhs.drugstore.shared;

import javax.inject.Singleton;

import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;
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
        //return DrugstoreMapper.INSTANCE;
        return null;
    }
}
