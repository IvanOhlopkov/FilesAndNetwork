import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        String hql = "From " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hql).getResultList();

        Transaction transaction = session.beginTransaction();

        for(PurchaseList purchase : purchaseList){

            String hql2 = "From " + Student.class.getSimpleName() + " Where name = '"
                    + purchase.getStudentName() + "'";
            Student student = (Student) session.createQuery(hql2).getSingleResult();

            String hql3 = "From " + Course.class.getSimpleName() + " Where name = '"
                    + purchase.getCourseName() + "'";
            Course course = (Course) session.createQuery(hql3).getSingleResult();

            LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();

            linkedPurchase.setId(new LinkedPurchaseListKey(student.getId(), course.getId()));
            linkedPurchase.setStudentId(student.getId());
            linkedPurchase.setCourseId(course.getId());
            session.save(linkedPurchase);
        }
        transaction.commit();
        sessionFactory.close();
    }
}
