package id.go.bpkp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.go.bpkp.entity.Actor;
import id.go.bpkp.entity.FilmActor;



@Service
public class ActorDAO {
	
	@Autowired
	private EntityManagerFactory emf;
	
	public List<Actor> allActors() {
		return (List<Actor>) emf
				.createEntityManager()
				.createQuery("from Actor")
				.getResultList();

	}
	
	public Actor getActor(int id) {
		return (Actor) emf
				.createEntityManager()
				.createQuery("from Actor where actorId = " + id)
				.getSingleResult();

	}
	
	public int addActor(Actor actor){
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		int hasil = 0;
		try {
			trans.begin();
			actor.setLastUpdate(new Date());
			em.persist(actor);			
			trans.commit();
		} catch(Exception ex){
			trans.rollback();
			hasil = -1;
			System.out.println(ex.getMessage());
		}
		return hasil;
		
	}
	
}
