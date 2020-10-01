package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Cardinalidad;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.RelationshipImpl;
import es.uniovi.reflection.progquery.database.relations.RelationTypesWiggle;

public class TypeServicesWiggle {

	public static MatchElement getSuperTypesOf(MatchElement type, MatchElement superType) {
		return new RelationshipImpl(type, superType,
				new EdgeImpl(Cardinalidad.MIN_TO_INF(0), RelationTypesWiggle.IS_SUBTYPE_OF));
		
	}

	public static MatchElement getSuperTypesOf(MatchElement type) {
		return new RelationshipImpl(type, new NodeVar("superType"),
				new EdgeImpl(Cardinalidad.MIN_TO_INF(0), RelationTypesWiggle.IS_SUBTYPE_OF));

	}
}
