package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.CreateClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Node;

public class CreationServices {

	public Clause createNode(Node n) {
		return new CreateClause(n);
	}
}
