package com.esiee.cloud;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class ResultServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String nom = req.getParameter("nom");
		String repertoire = req.getParameter("repertoire");
		

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query q = new Query("Personne").setAncestor(
				KeyFactory.createKey("Humain", "humain")).addSort("nom",
				SortDirection.DESCENDING);
		PreparedQuery pq = datastore.prepare(q);

		List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
		Iterator<Entity> entities = pq.asIterable().iterator();
		Entity entity = null;
		while (entities.hasNext()) {
			entity = entities.next();
		}
		if (entity != null) {

			System.out.println("Envoi demande exécution de la tache de fond");

			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(TaskOptions.Builder.withUrl("/tacheDeFond")
					.method(Method.POST).param("nom", nom).param("repertoire", repertoire));

			System.out
					.println("Fin envoi demande exécution de la tache de fond");
			try {
				req.setAttribute("results", results);
				this.getServletContext().getRequestDispatcher("/resultat.jsp")
						.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("erreur");
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	}
}
