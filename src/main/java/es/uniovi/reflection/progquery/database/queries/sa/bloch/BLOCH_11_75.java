package es.uniovi.reflection.progquery.database.queries.sa.bloch;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.AbstractQuery;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ClauseImpl;
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

public class BLOCH_11_75 extends AbstractQuery {

	public BLOCH_11_75() {
		super(true);

	}

	// WTIH ATTR, DECLARING TYPE
	public static void main(String[] args) {
		System.out.println(new BLOCH_11_75().queryToString());
	}

	@Override
	protected void initiate() {
		clauses = new Clause[] {

new ClauseImpl("MATCH (cu)-[:HAS_TYPE_DEF | :HAS_INNER_TYPE_DEF]->(typeDec)-[:IS_SUBTYPE_EXTENDS | :IS_SUBTYPE_IMPLEMENTS*]->( {fullyQualifiedName:'java.io.Serializable'})"
		+ "\n OPTIONAL MATCH (typeDec)-[:DECLARES_METHOD]->(method)-[:CALLABLE_HAS_THROWS]->(throwClause) "
		+ "\n WITH cu, typeDec, method, COLLECT(throwClause.actualType) as throws "
		+ "\n WHERE  NOT( method.fullyQualifiedName ENDS WITH ':readObject(java.io.ObjectInputStream)void' AND method.accessLevel='private' AND SIZE(throws)=2 AND 'java.io.IOException' IN throws AND 'java.lang.ClassNotFoundException' IN throws AND NOT method.isStatic AND NOT method.isFinal AND NOT method.isSynchronized AND NOT method.isStrictfp AND NOT method.isNative "
		+ " OR method.fullyQualifiedName ENDS WITH ':readResolve()java.lang.Object' AND SIZE(throws)=1 AND throws[0]='java.io.ObjectStreamException')"
		+ ""
		+ "\n RETURN DISTINCT '[BLOCH-10.75;'+ cu.fileName +';type_definition;examples.test.rule_10_75.NC1;'+ typeDec.lineNumber +'; You must implement the standard readObject method (or, at least, the readResolve method) for all your classes implementing java.io.Serializable.]'")
};
		}

}
