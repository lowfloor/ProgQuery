package es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.wiggle;

import es.uniovi.reflection.progquery.database.nodes.NodeTypes;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.AbstractQuery;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.CreateClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ExprImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Node;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.SimpleWithClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.UnwindClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.WhereClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.WithClause;
import es.uniovi.reflection.progquery.database.querys.services.StatementServices;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class OBJ54 extends AbstractQuery {

	public OBJ54() {
		super(false);
	}

	@Override
	protected void initiate() {
		NodeVar assign = new NodeVar("assign");
		clauses = new Clause[] {
				new MatchClause(
						getPDGServices().getIdsAndVarDeclarations(Node.nodeForWiggle("id", NodeTypes.IDENTIFIER))),
				new WithClause(new String[] { "varDec" },
						Pair.create("identifications", new ExprImpl("COLLECT( DISTINCT id)")))

				//
				, new MatchClause(StatementServices.WIGGLE.getEnclosingClassFromStatement("stat"),

						getAssignmentServices().getLeftPartAssignments(
								getAssignmentServices().getRightPartAssignments(
										Node.nodeForWiggle(NodeTypes.LITERAL, Pair.create("typeKind", "NULL"))),
								Node.nodeForWiggle("id", NodeTypes.IDENTIFIER)),
						getExpressionServices().getStatementFromExp(assign)),
				new WhereClause("id IN identifications ").append(getCFGServices().getCFGSuccesorsOf(new NodeVar("stat"),
						"assign, stat, enclClass, varDec, identifications")),
				new UnwindClause("identifications", "id"),
				new MatchClause(true,
						getAssignmentServices().getLeftPartAssignments(new NodeVar("anyAssign"), new NodeVar("id"))),
				new SimpleWithClause("assign, id, anyAssign, succesors, varDec, enclClass"),
				new MatchClause(getExpressionServices().getStatementFromExp(new NodeVar("id"))),

				new WhereClause("anyAssign IS NULL "), new UnwindClause("succesors", "succesor"),
				new CreateClause(new MatchImpl(
						"(assign)-[:REL_ONE]->( succesor),(assign)-[:REL_TWO]->( stat), (assign)-[:REL_THREE]->(varDec),(assign)-[:REL_FOUR]->(enclClass)")) };
	}
	

}
