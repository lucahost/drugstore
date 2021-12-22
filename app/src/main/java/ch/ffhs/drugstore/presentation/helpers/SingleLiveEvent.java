package ch.ffhs.drugstore.presentation.helpers;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 *
 * <p>This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 *
 * <p>Note that only one observer is going to be notified of changes.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    /**
     * @param owner the LifecycleOwner who owns the event
     * @param observer an observable object who subscribes to the event
     */
    @Override
    @MainThread
    public void observe(@NonNull LifecycleOwner owner,
            @NonNull final Observer<? super T> observer) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        // Observe the internal MutableLiveData
        super.observe(
                owner,
                t -> {
                    if (mPending.compareAndSet(true, false)) {
                        observer.onChanged(t);
                    }
                });
    }

    /**
     * @param value the generic value to pass to the observer
     */
    @Override
    @MainThread
    public void setValue(@Nullable T value) {
        mPending.set(true);
        super.setValue(value);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}
