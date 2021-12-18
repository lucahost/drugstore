package ch.ffhs.drugstore.presentation.dispensary.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic filter state providing filtering by search term a list of filters and favorites.
 *
 * @param <T> the type of the individual filters
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class FilterState<T> {
    private final List<T> filters = new ArrayList<>();
    private String searchFilter = "";
    private boolean favorites;

    /**
     * @return the currently active search filter
     */
    public String getSearchFilter() {
        return searchFilter;
    }

    /**
     * @param searchFilter the search term to filter by
     */
    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    /**
     * @return if filtering by favorites is enabled
     */
    public boolean isFavorites() {
        return favorites;
    }

    /**
     * Toggles filtering by favorites
     */
    public void toggleFavorites() {
        favorites = !favorites;
    }

    /**
     * @return the currently active filters
     */
    public List<T> getFilters() {
        return filters;
    }

    /**
     * @param filter individual filter to toggle
     */
    public void toggleFilter(T filter) {
        if (filters.contains(filter)) {
            filters.remove(filter);
        } else {
            filters.add(filter);
        }
    }
}
