package ch.ffhs.drugstore.data.database;

import android.content.Context;

import javax.inject.Singleton;

import ch.ffhs.drugstore.data.dao.DrugDao;
import ch.ffhs.drugstore.data.dao.DrugTypeDao;
import ch.ffhs.drugstore.data.dao.SignatureDao;
import ch.ffhs.drugstore.data.dao.SignatureDrugDao;
import ch.ffhs.drugstore.data.dao.SubstanceDao;
import ch.ffhs.drugstore.data.dao.TransactionDao;
import ch.ffhs.drugstore.data.dao.UnitDao;
import ch.ffhs.drugstore.data.dao.UserDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * This class acts as dependency injection module using android hilt / dagger It provides the
 * current DB DAOs to where needed
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@InstallIn(SingletonComponent.class)
@Module
public class DatabaseModule {

    @Singleton
    @Provides
    DrugstoreDatabase provideAppDatabase(@ApplicationContext final Context context) {
        return DrugstoreDatabase.getDatabase(context);
    }

    @Provides
    DrugDao provideDrugDao(DrugstoreDatabase db) {
        return db.drugDao();
    }

    @Provides
    DrugTypeDao provideDrugTypeDao(DrugstoreDatabase db) {
        return db.drugTypeDao();
    }

    @Provides
    SignatureDao provideSignatureDao(DrugstoreDatabase db) {
        return db.signatureDao();
    }

    @Provides
    SignatureDrugDao provideSignatureDrugDao(DrugstoreDatabase db) {
        return db.signatureDrugDao();
    }

    @Provides
    SubstanceDao provideSubstanceDao(DrugstoreDatabase db) {
        return db.substanceDao();
    }

    @Provides
    TransactionDao provideTransactionDao(DrugstoreDatabase db) {
        return db.transactionDao();
    }

    @Provides
    UnitDao provideUnitDao(DrugstoreDatabase db) {
        return db.unitDao();
    }

    @Provides
    UserDao provideUserDao(DrugstoreDatabase db) {
        return db.userDao();
    }

}
