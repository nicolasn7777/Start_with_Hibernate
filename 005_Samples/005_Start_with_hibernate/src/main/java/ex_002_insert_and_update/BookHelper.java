package ex_002_insert_and_update;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ex_002_insert_and_update.entity.Author;
import ex_002_insert_and_update.entity.Book;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookHelper {
    private SessionFactory sessionFactory;

    public BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList(){
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Book.class);

        Root<Book> root = cq.from(Book.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Book> bookList = query.getResultList();

        session.close();

        return bookList;
    }

    public Book getBookById(long id) {
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, id);
        return book;
    }

    public Book addBook(Book book){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(book);

        session.getTransaction().commit();

        session.close();

        return book;

    }

    public void getBookNameAndAuthorList(){
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaBuilder cb2 = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Book.class);

        CriteriaQuery cq2 = cb2.createQuery(Author.class);

        Root<Book> root = cq.from(Book.class);

        Root<Author> root2 = cq2.from(Author.class);

        cq.select(root);
        cq2.select(root2);

        Query query = session.createQuery(cq);

        Query query2 = session.createQuery(cq2);

        List<Book> bookList = query.getResultList();

        List<Author> authorList = query2.getResultList();

        for (Author a : authorList) {
            for (Book b : bookList) {
                if (b.getAuthorId() == a.getId())
                    System.out.println(b.getName() + " " + a.getName() + " " + a.getLastName());
            }
        }
        session.close();
    }
    public Book setNewBookById(long id, String newName) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        book.setName(newName);
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }
}
