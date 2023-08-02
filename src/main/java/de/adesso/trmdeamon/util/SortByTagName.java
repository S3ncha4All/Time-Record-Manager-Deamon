package de.adesso.trmdeamon.util;

import de.adesso.trmdeamon.v1.model.BookingTags;

import java.util.Comparator;

public class SortByTagName implements Comparator<BookingTags> {
    @Override
    public int compare(BookingTags o1, BookingTags o2) {
        return  o1.getTag().getName().compareTo(o2.getTag().getName());
    }
}
