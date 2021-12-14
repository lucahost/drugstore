package ch.ffhs.drugstore.presentation.dispensary.view;

import java.util.ArrayList;
import java.util.List;

public class FilterState<T> {
    private String searchFilter = "";
    private boolean favorites;
    private final List<T> filters = new ArrayList<>();

    public String getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void toggleFavorites() {
        favorites = !favorites;
    }

    public List<T> getFilters() {
        return filters;
    }

    public void toggleFilter(T filter) {
        if (filters.contains(filter)) {
            filters.remove(filter);
        } else {
            filters.add(filter);
        }
    }
}
