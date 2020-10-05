package es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.pq;

import es.uniovi.reflection.progquery.database.nodes.NodeTypes;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.AbstractQuery;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.AnonymousNode;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Any;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.CompleteNode;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Extract;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Filter;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Node;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Path;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.RelationshipImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ReturnClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.SimpleWithClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.UnwindClause;
import es.uniovi.reflection.progquery.database.querys.services.InmutabilityServicesPQ;
import es.uniovi.reflection.progquery.database.relations.PDGRelationTypes;
import es.uniovi.reflection.progquery.database.relations.RelationTypes;
import es.uniovi.reflection.progquery.database.relations.TypeRelations;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class OBJ56_LONG extends AbstractQuery {

	public OBJ56_LONG(boolean isProgQuery) {
		super(isProgQuery);

	}

	@Override
	protected void initiate() {
		Node typeDec = new NodeVar("typeDec");
		clauses = new Clause[] {
				new MatchClause(
						getStatementServices().getMethodFromStatement(
								new MatchImpl(
										"(enclosingType{accessLevel:'public'})-[:DECLARES_FIELD]->(field)-[:USED_BY]->(id)<-[:RETURN_EXPR]-()"),
								new CompleteNode("method", NodeTypes.METHOD_DEF)),
						new RelationshipImpl(new NodeVar("field"), typeDec, new EdgeImpl(TypeRelations.ITS_TYPE_IS))),
				InmutabilityServicesPQ.typeIsExternallyMutable(typeDec),

				new SimpleWithClause(
						"enclosingType, field, method, typeDec,p,CASE WHEN p IS NULL THEN [] ELSE NODES(p) END as nodes"),

				new SimpleWithClause("enclosingType, field, method,p, typeDec,"
						+ new Extract("RANGE(0,SIZE(nodes),1)", "[x, nodes[x]]").expToString() + " as nodes"),
				new UnwindClause("nodes", "nodeInP"),
				new SimpleWithClause(
						"enclosingType, field, method, typeDec,p, nodeInP[1] as nodeInP, nodeInP[0] as order"),
				new MatchClause(true,
						new Path(new NodeVar("nodeInP"),
								Pair.create(new EdgeImpl(PDGRelationTypes.HAS_THIS_REFERENCE), new AnonymousNode()),
								Pair.create(
										new EdgeImpl(PDGRelationTypes.STATE_MAY_BE_MODIFIED_BY,
												PDGRelationTypes.STATE_MODIFIED_BY),
										new CompleteNode("setMethod", NodeTypes.METHOD_DEF,
												Pair.create("accessLevel", "public"), Pair.create("isStatic", false)))),
						new RelationshipImpl(new NodeVar("nodeInP"), new NodeVar("setMethod"),
								new EdgeImpl(RelationTypes.DECLARES_METHOD))

				),

				new MatchClause(true,
						getStatementServices().getMethodFromStatement(
								new Path(new NodeVar("nodeInP"), Pair.createP("f", RelationTypes.DECLARES_FIELD),
										Pair.createP("", PDGRelationTypes.USED_BY),
										Pair.createInv("", RelationTypes.RETURN_EXPR)),
								new CompleteNode("getMethod", NodeTypes.METHOD_DEF,
										Pair.create("accessLevel", "public"), Pair.create("isStatic", false))),
						new RelationshipImpl(new NodeVar("f"), new NodeVar("fType"),
								new EdgeImpl(TypeRelations.ITS_TYPE_IS))),

				new SimpleWithClause(
						"enclosingType, field, method, typeDec, nodeInP,order,p, setMethod, COLLECT(DISTINCT fType) as mutableDependencies"),
				new MatchClause(true,
						new Path(new NodeVar("nodeInP"),
								Pair.createP(new CompleteNode("f", Pair.create("isStatic", false),
										Pair.create("accessLevel", "public")), RelationTypes.DECLARES_FIELD),
								Pair.create(new EdgeImpl(TypeRelations.ITS_TYPE_IS), new NodeVar("fType")))),
				new SimpleWithClause(
						"enclosingType, field, method, typeDec, nodeInP,order,p, SIZE( COLLECT( setMethod))>0 as isThereASetMethod, mutableDependencies, COLLECT(DISTINCT [f,fType]) as otherMutableDependencies "),
				new SimpleWithClause(
						"enclosingType, field, method, typeDec, nodeInP, order,p, isThereASetMethod, mutableDependencies, "
								+ new Extract(new Filter("otherMutableDependencies", "x[0].isFinal"), "y[1]", "y")
										.expToString()
								+ " as mutableDependenciesBis, "
								+ new Any("otherMutableDependencies", "NOT  x[0].isFinal").expToString()
								+ " as isMutableDueToPublicField ORDER BY order"),
				new SimpleWithClause(
						"enclosingType, field, method, typeDec,p, COLLECT([nodeInP, isThereASetMethod,isMutableDueToPublicField,mutableDependenciesBis,mutableDependencies]) as nodesWithInfo "),
				new ReturnClause(
						"enclosingType.fullyQualifiedName, field.name, method.name, typeDec.fullyQualifiedName,  "
								+ new Extract("nodesWithInfo", "x[0]").expToString())
				// new ReturnClause(
				// "enclosingType.fullyQualifiedName, field.name, method.name,
				// typeDec.fullyQualifiedName, NODES(p) ")
		};
	}
}
