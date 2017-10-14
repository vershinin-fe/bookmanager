package net.feww.bookmanager.dao;

import net.feww.bookmanager.model.Paging;
import net.feww.bookmanager.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book successfully saved. Book details: " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();

        session.update(book);
        logger.info("Book successfully update. Book details: " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));

        if(book!=null){
            session.delete(book);
        }
        logger.info("Book successfully removed. Book details: " + book);
    }

    @Override
    public Long getCurrentCount(String searchString){
        Session session = this.sessionFactory.getCurrentSession();

        Long count;
        Query query;

        if(searchString != null && !"".equals(searchString)) {
            query = session.createQuery("SELECT COUNT(*) FROM Book WHERE title like ?1")
                    .setParameter(1, "%" + searchString + "%");
        } else {
            query = session.createQuery("SELECT COUNT(*) FROM Book");
        }

        count = (Long) query.getResultList().get(0);

        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks(Paging paging, String searchString) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList;
        Query<Book> query;
        int first = paging.getCurrentPage() * Paging.MAX_LINES_PER_PAGE;

        if(searchString != null && !"".equals(searchString)) {
            query = session.createQuery("FROM Book WHERE title like ?1")
                    .setParameter(1, "%" + searchString + "%")
                    .setFirstResult(first).setMaxResults(Paging.MAX_LINES_PER_PAGE);
        } else {
            query = session.createQuery("FROM Book")
                    .setFirstResult(first)
                    .setMaxResults(Paging.MAX_LINES_PER_PAGE);
        }

        bookList = query.getResultList();

        for(Book book: bookList){
            logger.info("Book list: " + book);
        }

        return bookList;
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully loaded. Book details: " + book);

        return book;
    }
}
