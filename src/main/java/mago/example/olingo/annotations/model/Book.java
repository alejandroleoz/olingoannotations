package mago.example.olingo.annotations.model;

import org.apache.olingo.odata2.api.annotation.edm.*;

@EdmEntityType
@EdmEntitySet
public class Book {

    @EdmKey
    @EdmProperty
    private Long id;

    @EdmProperty
    private String title;

    @EdmProperty
    private String description;

    @EdmNavigationProperty(
            name = "author",
            toType = Author.class,
            toMultiplicity = EdmNavigationProperty.Multiplicity.ZERO_OR_ONE)
    private Author author;

    public Book() {
    }

    public Book(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
