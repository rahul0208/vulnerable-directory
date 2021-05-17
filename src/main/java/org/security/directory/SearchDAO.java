package org.security.directory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Object[]> findByZipCode(String zipCode) {
        EntityManager em = entityManagerFactory.createEntityManager();
        String q = "select * from PROVIDERS where zip_code = '" + zipCode + "'";
        Query query = em.createNativeQuery(q);
        List<Object[]> results = query.getResultList();
        return results;
    }
}


