package com.yennth.assignment.service;


import com.yennth.assignment.util.HibernateUtil;
import com.yennth.assignment.entity.Favorite;
import com.yennth.assignment.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserService {

    public List<User> getAll() {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        try {
            String jpql="SELECT u FROM User u";
            TypedQuery<User> query = em.createQuery(jpql,User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    public void create(User user) {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();

        }
        em.close();
    }

    public User getById(String id) {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        User u = em.find(User.class, id);
        em.close();
        return u;
    }

    public void update(User u) {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        try {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteById(String id) {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        try {
            em.getTransaction().begin();
            User u = getById(id);
            em.remove(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public boolean checkUser(String username, String password) {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        try {
            String jpql = "SELECT o FROM User o WHERE o.id=:username and o.password=:password";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<User> resultList = query.getResultList();
            return !resultList.isEmpty();
        } finally {
            em.close();
        }


    }

    public boolean CheckStatus(String username, String id) {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id=:username and f.video.id=:id";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("username", username);
            query.setParameter("id", id);

            List<Favorite> resultList = query.getResultList();
            return !resultList.isEmpty();
        } finally {
            em.close();
        }


    }

    public boolean checkDeleteUser(String id) {
        EntityManager em = HibernateUtil.getFACTORY().openSession();
        try {
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id=:username ";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
            query.setParameter("username", id);

            List<Favorite> resultList = query.getResultList();
            return !resultList.isEmpty();
        } finally {
            em.close();
        }
    }
}
