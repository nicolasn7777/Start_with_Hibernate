package ex_002_insert_and_update;

import org.jboss.logging.Logger;
import ex_002_insert_and_update.entity.Author;

import java.util.List;

public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

    public static void main(String[] args) {
         BookHelper bh = new BookHelper();

         bh.getBookNameAndAuthorList();

         AuthorHelper ah = new AuthorHelper();

         Author author = ah.getAuthorById(2);

         System.out.println(author.getName() + "\n");

         Author newAuthor = ah.setNewAuthorById(2, "Max");

         List<Author> authors = ah.getAuthorList();

        for (Author a : authors) {
            System.out.println(a.getName());
        }

        Author longAuthor = new Author();
                ah.add200Authors();

        LOG.debug(author.getId() + " " + author.getName() + " " + author.getLastName());
    }
}
