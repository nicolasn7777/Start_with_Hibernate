package ex_002_insert_and_update;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ex_002_insert_and_update.entity.Author;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList(){
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);

        cq.select(root);


        Query query = session.createQuery(cq);

        List<Author> authorList = query.getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }

    public Author addAuthor(Author author){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(author);

        session.getTransaction().commit();

        session.close();


        return author;

    }

    public Author setNewAuthorById(long id, String newName) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        author.setName(newName);
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }
    public void add200Authors(){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        for (int i = 0; i < 200; i++) {
            session.save(new Author("Author" + i));
            if (i % 10 == 0) {
                session.flush();
            }
        }
        session.getTransaction().commit();
        session.close();
    }
}
