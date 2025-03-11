package HibernateUtils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(FutaBus.bean.BenXe.class);
            configuration.addAnnotatedClass(FutaBus.bean.ChuyenXe.class);
            configuration.addAnnotatedClass(FutaBus.bean.LoaiXe.class);
            configuration.addAnnotatedClass(FutaBus.bean.LoTrinh.class);
            configuration.addAnnotatedClass(FutaBus.bean.NguoiDung.class);
            configuration.addAnnotatedClass(FutaBus.bean.PhanQuyen.class);
            configuration.addAnnotatedClass(FutaBus.bean.PhieuDatVe.class);
            configuration.addAnnotatedClass(FutaBus.bean.QuanHuyen.class);
            configuration.addAnnotatedClass(FutaBus.bean.TinhThanh.class);
            configuration.addAnnotatedClass(FutaBus.bean.TuyenXe.class);
            configuration.addAnnotatedClass(FutaBus.bean.VeXe.class);
            configuration.addAnnotatedClass(FutaBus.bean.ViTriGhe.class);
            configuration.addAnnotatedClass(FutaBus.bean.Xe.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            StandardServiceRegistry serviceRegistry = builder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.out.println("Error at session factory initialization");
            ex.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
    	if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
