package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class AuthorAndBookGenerator {
    private static void addAuthor(Schema schema) {
        Entity author = schema.addEntity("Author");
        author.addIdProperty();
        author.addStringProperty("Name").notNull();
        author.addIntProperty("Age");
        author.addBooleanProperty("Sex");
        
        
        Entity book = schema.addEntity("Book");
        book.addIdProperty();
        book.addStringProperty("Title").notNull();
        Property bookDate = book.addDateProperty("Date").getProperty();
        book.addDoubleProperty("Price");
        
        Property AuthorId = book.addLongProperty("AuthorId").notNull().getProperty();
        book.addToOne(author, AuthorId);
        
        ToMany authorToBooks = author.addToMany(book, AuthorId);
        authorToBooks.setName("author2book");
        authorToBooks.orderAsc(bookDate);
    }
    
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.example.orm.greendao");

        addAuthor(schema);

//        new DaoGenerator().generateAll(schema, "../DaoExample/src-gen");
        new DaoGenerator().generateAll(schema, "src-gen");
    }
    

}
