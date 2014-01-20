package com.esiee.cloud;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;

@SuppressWarnings("serial")
public class AjoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String numero = req.getParameter("numero");
		String email = req.getParameter("email");
		String adresse = req.getParameter("adresse");
		String repertoire = req.getParameter("repertoire");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity personne = new Entity("Personne",KeyFactory.createKey("Humain","humain"));
		personne.setProperty("nom", nom);
		personne.setProperty("prenom", prenom);
		personne.setProperty("numero", numero);
		personne.setProperty("email", email);
		personne.setProperty("adresse", adresse);

		Transaction tx = datastore.beginTransaction();
		datastore.put(personne);
		tx.commit();

		System.out.println("OK");

		req.setAttribute("repertoire", repertoire);
		req.setAttribute("nom", nom);
		req.setAttribute("prenom", prenom);
		req.setAttribute("numero", numero);
		req.setAttribute("email", email);
		req.setAttribute("adresse", adresse);
		resp.sendRedirect("/requete?repertoire="+repertoire+"&nom="+nom);

	}
}
