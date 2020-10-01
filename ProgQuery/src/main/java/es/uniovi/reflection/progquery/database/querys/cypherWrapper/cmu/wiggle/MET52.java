package es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.wiggle;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.AbstractQuery;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeDirection;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.RelationshipImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ReturnClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.SimpleWithClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.WhereClause;
import es.uniovi.reflection.progquery.database.querys.services.ModifiersServiceWiggle;
import es.uniovi.reflection.progquery.database.relations.RelationTypesWiggle;

public class MET52 extends AbstractQuery {

	public MET52() {
		super(false);
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 * private static final String
	 * MET52_DO_NOT_USE_CLONE_WITH_UNTRUSTED_PARAMETERS =
	 * " MATCH (typeDec{accessLevel:'public'})-[:DECLARES_METHOD]->(method{accessLevel:'public'})-[:HAS_METHODDECL_PARAMETERS]->(param)"
	 * +
	 * " -[:USED_BY]->(id), (mInv:METHOD_INVOCATION)-[:METHODINVOCATION_METHOD_SELECT]->(mSelect:MEMBER_SELECTION{memberName:'clone'})-[:MEMBER_SELECT_EXPR]->(id), (mInv)-[:HAS_DEC]->(dec) WHERE dec.fullyQualifiedName CONTAINS '()' WITH param, mInv.lineNumber as line, method.fullyQualifiedName as methodName"
	 * +
	 * " MATCH (typeDec{fullyQualifiedName:param.actualType, accessLevel:'public', isFinal:false})"
	 * +
	 * " RETURN  'Warning [CMU-MET52] You must not use the clone method to copy unstrasted parameters (like parameter ' + param.name+ ', cloned in line '+ line + ' in method ' + methodName +').'"
	 * ;
	 * 
	 * 
	 */
	public static void main(String[] args){
		System.out.println(new MET52().queryToString());
	}
	@Override
	protected void initiate() {
		clauses = new Clause[] {
				new MatchClause(ModifiersServiceWiggle.getClassModifiers(new NodeVar("typeDec")),
						new RelationshipImpl(new NodeVar("typeDec"), new NodeVar("method"),
								new EdgeImpl(RelationTypesWiggle.DECLARES_METHOD)),
						new RelationshipImpl(new NodeVar("param"),
								ModifiersServiceWiggle.getMethodModifiers(new NodeVar("method")),
								new EdgeImpl(EdgeDirection.OUTGOING, RelationTypesWiggle.HAS_METHODDECL_PARAMETERS))),
				new WhereClause("methodModifiers.flags CONTAINS 'public' AND classModifiers.flags CONTAINS 'public'"),
				new SimpleWithClause("param", "method"),
				new MatchClause(getPDGServices().getIdsAndVarDeclarations(new NodeVar("useId"), new NodeVar("param"),
						"method")),
				new MatchClause(new MatchImpl(
						"(mInv{nodeType:'JCMethodInvocation'})-[:METHODINVOCATION_METHOD_SELECT]->(mSelect{name:'clone'})-[:MEMBER_SELECT_EXPR]->(useId)")),
				new MatchClause(true,
						new RelationshipImpl(new NodeVar("mInv"), new NodeVar("arg"),
								RelationTypesWiggle.METHODINVOCATION_ARGUMENTS)),
				new WhereClause("arg IS NULL"),
				new SimpleWithClause("param, mInv.lineNumber as line, method.name as methodName"),
				new MatchClause(ModifiersServiceWiggle.getClassModifiers(
						new NodeVar("typeDec"))),
				new WhereClause(
						"classModifiers.flags CONTAINS 'public' AND NOT classModifiers.flags CONTAINS 'final' AND SPLIT(typeDec.fullyQualifiedName, '<')[0]=SPLIT(param.actualType, '<')[0]"),
				new ReturnClause(
						"'Warning [CMU-MET52] You must not use the clone method to copy unstrasted parameters (like parameter ' + param.name+ ', cloned in line '+ line + ' in method ' + methodName +').'")

		};
	}

}
