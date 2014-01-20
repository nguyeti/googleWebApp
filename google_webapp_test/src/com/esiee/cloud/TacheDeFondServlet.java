package com.esiee.cloud;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@SuppressWarnings("serial")
public class TacheDeFondServlet extends HttpServlet {

	static int compteur = 0;

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String nom = req.getParameter("nom");
		String repertoire = req.getParameter("repertoire");
		System.out.println("Debut tache de fond avec parametre, ajout de : "
				+ nom);

		if (compteur < 1) {
			System.out.println("Tache en erreur : elle va être relancée !");
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			compteur++;
		} else {

			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query q = new Query("Personne");
			PreparedQuery pq = datastore.prepare(q);
			System.out.println("Il y a " + pq.countEntities() + " entites dans le repertoire " + repertoire + " !");
			System.out.println("(\\_/)");
			System.out.println("(o.o)");
			System.out.print("(___)0");
			for (int i = 0; i < 10; i++) {
				System.out.print(". ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}

			System.out.println("La tache se termine normalement");
		}

	}
}
