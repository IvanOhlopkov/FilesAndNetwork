import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Query query = session.createSQLQuery("select course_name, " +
                "sum(if(month(subscription_date) > 0, 1, 0))" +
                " / (MAX(month(subscription_date))-MIN(month(subscription_date))+1)" +
                " from purchaselist where subscription_date like '2018%'" +
                " group by course_name;");
        List<PurchaseList> queryResult = query.getResultList();
        Iterator purchaseListIterator = queryResult.iterator();

        while (purchaseListIterator.hasNext()) {
            Object[] object = (Object[]) purchaseListIterator.next();
            System.out.println(object[0] + " - " + object[1]);

        }
        session.close();

        sessionFactory.close();
    }

}
