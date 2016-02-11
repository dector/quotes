package io.github.dector.quotes.qoutes.storage;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;

@Entity
public class QuoteDbModel {

    @Key @Generated
    public int key;

    public String text;
    public String author;
}
