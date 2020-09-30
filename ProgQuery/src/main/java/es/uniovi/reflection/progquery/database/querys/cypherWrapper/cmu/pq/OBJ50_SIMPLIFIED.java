package es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.pq;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.AbstractQuery;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.CompleteNode;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ExprImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Expression;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.RelationshipImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ReturnClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.SimpleWithClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.WhereClause;
import es.uniovi.reflection.progquery.database.querys.services.FieldServices;
import es.uniovi.reflection.progquery.database.relations.PDGRelationTypes;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class OBJ50_SIMPLIFIED extends AbstractQuery {

	private static final Expression ATTR_PROPERTIES = new ExprImpl(" attr.isFinal ");

	private static final Clause[] DECLARED_TYPES_AND_DECLARED_FIELDS = new WhereClause(ATTR_PROPERTIES)
			.addToClauses(FieldServices.typesAndDeclaredFieldsAndTypes(
					new CompleteNode("declaringType", Pair.create("isDeclared", "true"))));

	public OBJ50_SIMPLIFIED() {
		super(true);

	}

	// WTIH ATTR, DECLARING TYPE
	public static void main(String[] args) {
		System.out.println(new OBJ50_SIMPLIFIED().queryToString());
	}

	@Override
	protected void initiate() {
		clauses = new Clause[] {
				// (NOT EXISTS(mutatorExpr.isInit) OR NOT mutatorExpr.isInit)

				new MatchClause(
						new RelationshipImpl(new NodeVar("declaration{isFinal:true}"), new NodeVar("mutatorExpr"),
								new EdgeImpl("r", PDGRelationTypes.STATE_MODIFIED_BY,
										PDGRelationTypes.STATE_MAY_BE_MODIFIED_BY))),
				new WhereClause(" NOT mutatorExpr:INITIALIZATION "),
				new MatchClause(new MatchImpl(getStatementServices()
						.getMethodFromStatement(getExpressionServices().getStatementFromExp(new NodeVar("mutatorExpr"))).matchToString()
						+ "<-[:DECLARES_FIELD|DECLARES_METHOD|DECLARES_CONSTRUCTOR|HAS_STATIC_INIT]-(exprEnclClass)")),
				new WhereClause(" r.isOwnAccess IS NULL OR r.isOwnAccess=FALSE OR NOT method.isInitializer "),
				new SimpleWithClause("declaration, mutatorExpr,exprEnclClass "),
				new MatchClause(getStatementServices().getEnclosingClassFromDeclaration(new NodeVar("declaration"))),
				new SimpleWithClause(
						"declaration, enclClass,COLLECT(HEAD(LABELS(mutatorExpr))+', line '+mutatorExpr.lineNumber+ ', class '+ exprEnclClass.fullyQualifiedName"
								+ ") as mutatorsMessage"),
				new ReturnClause(
						"'Warning [CMU-OBJ50] Declaration with name '+ declaration.name+ '( in line ' + declaration.lineNumber +', class ' +enclClass.fullyQualifiedName "
								+ "+ ') is not actually final, only the reference. Concretely, '+ declaration.name +' may be mutated in '+mutatorsMessage") };

	}

}
