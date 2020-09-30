package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Cardinalidad;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.CompleteNode;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ExprImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Expression;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Node;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Path;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.RelationshipImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.WhereClause;
import es.uniovi.reflection.progquery.database.relations.PDGRelationTypes;
import es.uniovi.reflection.progquery.database.relations.RelationTypes;
import es.uniovi.reflection.progquery.database.relations.TypeRelations;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class InmutabilityServicesPQ {

	public static Expression isInmutableField(String field, String typeDec) {
		return isInmutableField(new ExprImpl(field), new ExprImpl(typeDec));
	}

	public static Expression isInmutableField(Expression field, Expression typeDec) {
		return new ExprImpl("NOT(" + typeDec.expToString() + ".accessLevel='public'  AND NOT " + field.expToString()
				+ ".isFinal AND NOT " + field.expToString() + ".isStatic AND ( " + field.expToString()
				+ ".accessLevel='public'  OR (" + field.expToString() + ".accessLevel='protected' AND NOT "
				+ typeDec.expToString() + ".isFinal) ))");
	}

	public static MatchClause getOptionalMutationsRelsOfFields() {
		return new MatchClause(true,
				new RelationshipImpl(new NodeVar("field"),
						new EdgeImpl("mutationRel", PDGRelationTypes.STATE_MODIFIED_BY,
								PDGRelationTypes.STATE_MAY_BE_MODIFIED_BY, PDGRelationTypes.MODIFIED_BY)));
	}

	public static MatchClause getOptionalMutationsOfFields() {
		return new MatchClause(true,
				new RelationshipImpl(new NodeVar("field"), new NodeVar("ass"),
						new EdgeImpl("mutationRel", PDGRelationTypes.STATE_MODIFIED_BY,
								PDGRelationTypes.STATE_MAY_BE_MODIFIED_BY, PDGRelationTypes.MODIFIED_BY)));
	}

	public static MatchClause getOptionalModificationsOfFields() {
		return new MatchClause(true, new RelationshipImpl(new NodeVar("field"), new NodeVar("ass"),
				new EdgeImpl("mutationRel", PDGRelationTypes.MODIFIED_BY)));
	}

	public static Path getTypesSuperTypesAndFieldsTransitive(MatchElement initialTypeDec) {
		return new Path(initialTypeDec, "p",
				Pair.create(
						new EdgeImpl(Cardinalidad.ONE_TO_INF, RelationTypes.DECLARES_FIELD, TypeRelations.ITS_TYPE_IS,
								TypeRelations.IS_SUBTYPE_EXTENDS),
						new NodeVar("next")));
	}

	public static Path getTypesAndFieldsTransitive(Node initialTypeDec) {
		return new Path(initialTypeDec, "p",
				Pair.create(
						new EdgeImpl(Cardinalidad.ONE_TO_INF, RelationTypes.DECLARES_FIELD, TypeRelations.ITS_TYPE_IS),
						new CompleteNode("next", Pair.create("typeKind", "DECLARED"))));
	}

	public static Clause[] getTypesAndFieldsTransitiveFirstTypeDec(Node initialTypeDec) {
		return new Clause[] { new MatchClause(getTypesAndFieldsTransitive(initialTypeDec)),
				new WhereClause(new ExprImpl("typeDec:CLASS_DECLARATION OR typeDec:ENUM_DECLARATION")) };
	}

	public static Clause typeIsExternallyMutable(MatchElement type) {
		throw new IllegalStateException();
	}
}
