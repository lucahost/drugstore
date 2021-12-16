package ch.ffhs.drugstore.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.DeleteColumn;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ch.ffhs.drugstore.data.converters.DateConverter;
import ch.ffhs.drugstore.data.dao.DrugDao;
import ch.ffhs.drugstore.data.dao.DrugTypeDao;
import ch.ffhs.drugstore.data.dao.SignatureDao;
import ch.ffhs.drugstore.data.dao.SignatureDrugDao;
import ch.ffhs.drugstore.data.dao.SubstanceDao;
import ch.ffhs.drugstore.data.dao.TransactionDao;
import ch.ffhs.drugstore.data.dao.UnitDao;
import ch.ffhs.drugstore.data.dao.UserDao;
import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.entity.Signature;
import ch.ffhs.drugstore.data.entity.SignatureDrug;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.data.entity.User;

@Database(
        entities = {
                Drug.class,
                DrugType.class,
                Substance.class,
                Signature.class,
                SignatureDrug.class,
                Transaction.class,
                Unit.class,
                User.class
        },
        version = 4,
        autoMigrations = {
                @AutoMigration(from = 1, to = 2),
                @AutoMigration(from = 2, to = 3, spec =
                        DrugstoreDatabase.DropUnitColumnMigration.class),
                @AutoMigration(from = 3, to = 4)
        },
        exportSchema = true)
@TypeConverters({DateConverter.class})
public abstract class DrugstoreDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    /**
     * Override the onCreate method to populate the database. For this sample, we clear the database
     * every time it is created.
     */
    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                }
            };
    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile DrugstoreDatabase INSTANCE;

    public static DrugstoreDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DrugstoreDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(
                                    context.getApplicationContext(), DrugstoreDatabase.class,
                                    "drugstore_db")
                                    .addCallback(sRoomDatabaseCallback)
                                    //.fallbackToDestructiveMigration()
                                    // TODO @Luca temporary fix for drugs.getById()
                                    .allowMainThreadQueries()
                                    .createFromAsset("database/database_v3.db")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract DrugDao drugDao();

    public abstract DrugTypeDao drugTypeDao();

    public abstract UnitDao unitDao();

    public abstract TransactionDao transactionDao();

    public abstract SignatureDao signatureDao();

    public abstract SignatureDrugDao signatureDrugDao();

    public abstract SubstanceDao substanceDao();

    public abstract UserDao userDao();

    @DeleteColumn(tableName = "drugTypes", columnName = "unitId")
    static class DropUnitColumnMigration implements AutoMigrationSpec {
    }
}
