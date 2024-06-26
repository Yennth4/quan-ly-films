package com.yennth.assignment.service;

import com.yennth.assignment.util.HibernateUtil;
import com.yennth.assignment.entity.Favorite;
import com.yennth.assignment.entity.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoService {
    public List<Video> getAll() {
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        TypedQuery<Video> query = em.createQuery("SELECT m FROM Video m", Video.class);
        List<Video> list = query.getResultList();
        em.close();
        return list;
    }

    public void create(Video video) {
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        em.close();
    }

    public Video getById(String id) {
        EntityManager entityManager = HibernateUtil.getFACTORY().createEntityManager();
        Video m = entityManager.find(Video.class,id);
        entityManager.close();
        return m;

    }

    public void update(Video m) {
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public void deleteById(String id) {
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        try {
            em.getTransaction().begin();
            Video m = getById(id);
            em.remove(m);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
    public void deleteFavorite(String id, String username) {
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();


        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM Favorite f WHERE f.user.id = :username AND f.video.id = :id")
                    .setParameter("username", username)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        em.close();
    }


    public List<Video> search(String keyword) {
        String jpql = "SELECT v from  Video v WHERE v.title LIKE :keyword";
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("keyword", "%" + keyword + "%");
        List<Video> list = query.getResultList();
        em.close();
        return list;

    }


    public List<Video> searchFavorite(String username, String keyword) {
        String jpql = "SELECT  v.video from  Favorite v  WHERE v.user.id = :username and v.video.title LIKE :keyword";
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("username", username);
        query.setParameter("keyword", "%" + keyword + "%");
        List<Video> list = query.getResultList();
        em.close();
        return list;
    }

    public void addFavorite(Favorite f) {
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
    }


    public boolean checkDeleteUser(String id) {
        EntityManager em = HibernateUtil.getFACTORY().createEntityManager();
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.video.id=:id ";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("id", id);

            List<Favorite> resultList = query.getResultList();
            return !resultList.isEmpty();
        } finally {
            em.close();
        }
    }
}
