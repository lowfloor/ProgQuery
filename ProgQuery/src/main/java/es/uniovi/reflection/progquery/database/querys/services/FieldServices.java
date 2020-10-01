package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.CompleteNode;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Node;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Path;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.RelationshipImpl;
import es.uniovi.reflection.progquery.database.relations.RelationTypes;
import es.uniovi.reflection.progquery.database.relations.TypeRelations;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class FieldServices {

	public static MatchClause typesAndDeclaredFields(Node declaringType) {
		return new MatchClause(
				new RelationshipImpl(declaringType, new CompleteNode("attr", Pair.create("typeKind", "DECLARED")),
						new EdgeImpl(RelationTypes.DECLARES_FIELD)));
	}

	public static MatchClause typesAndDeclaredFieldsAndTypes(Node declaringType) {
		return new MatchClause(new Path(declaringType,
				Pair.create(new EdgeImpl(RelationTypes.DECLARES_FIELD),
						new CompleteNode("attr", Pair.create("typeKind", "DECLARED"))),
				Pair.create(new EdgeImpl(TypeRelations.ITS_TYPE_IS), new NodeVar("typeDec"))));
	}
}
