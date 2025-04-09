package Dao;

import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import FutaBus.bean.VeXe;

@Repository
public class VeXeDao {
	
	public void save(Session session, VeXe veXe) {
        session.save(veXe);
    }
	
}