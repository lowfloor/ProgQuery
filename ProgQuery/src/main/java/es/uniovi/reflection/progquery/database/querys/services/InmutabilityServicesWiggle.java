package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Cardinalidad;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.CompleteNode;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ExprImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Expression;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Path;
import es.uniovi.reflection.progquery.database.relations.RelationTypesWiggle;
import es.uniovi.reflection.progquery.database.relations.TypeRelations;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class InmutabilityServicesWiggle {
	public static Expression isInmutableField(String field, String typeDec) {
		return isInmutableField(new ExprImpl(field), new ExprImpl(typeDec));
	}

	public static Expression isInmutableField(Expression fieldModifiers, Expression typeDecModifiers) {
		return new ExprImpl("NOT(" + typeDecModifiers.expToString() + ".flags CONTAINS 'public'  AND NOT "
				+ fieldModifiers.expToString() + ".flags CONTAINS 'final' AND NOT " + fieldModifiers.expToString()
				+ ".flags CONTAINS 'static' AND ( " + fieldModifiers.expToString() + ".flags CONTAINS 'public'  OR ("
				+ fieldModifiers.expToString() + ".flags CONTAINS 'protected' AND NOT "
				+ typeDecModifiers.expToString() + ".flags CONTAINS 'final') ))");
	}

	public static Path getTypesSuperTypesAndFieldsTransitive(MatchElement initialTypeDec) {
		return new Path(initialTypeDec, "p",
				Pair.create(
						new EdgeImpl(Cardinalidad.ONE_TO_INF, RelationTypesWiggle.DECLARES_FIELD,
								TypeRelations.ITS_TYPE_IS, // This is not a
															// Wiggle rel, but
															// is added to the
															// graph in the
															// query
								RelationTypesWiggle.IS_SUBTYPE_EXTENDS),
						new CompleteNode("next", Pair.create("typeKind", "DECLARED"))));
	}

}
