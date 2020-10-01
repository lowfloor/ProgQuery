package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.nodes.NodeTypes;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Cardinalidad;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Node;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Path;
import es.uniovi.reflection.progquery.database.relations.RelationTypesWiggle;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class AssignmentServicesWiggle extends AssignmentServicesProgQueryImpl {
	@Override
	public Path getRightPartAssignments(Node rhs) {
		return getRightPartAssignments(Node.nodeForWiggle("assign", NodeTypes.ASSIGNMENT), rhs);
	}

	@Override
	public MatchElement getMemberSelectionsLeftSide(MatchElement assign) {
		return getLeftPartAssignments(assign).append(Pair.create(
				new EdgeImpl(Cardinalidad.MIN_TO_INF(0), RelationTypesWiggle.ARRAYACCESS_EXPR,
						RelationTypesWiggle.MEMBER_SELECT_EXPR),
				Node.nodeForWiggle("memberSelection", NodeTypes.MEMBER_SELECTION)));
	}

	@Override
	public MatchElement getLeftMostId(MatchElement assign) {
		return getLeftPartAssignments(assign).append(Pair.create(
				new EdgeImpl(Cardinalidad.MIN_TO_INF(0), RelationTypesWiggle.ARRAYACCESS_EXPR,
						RelationTypesWiggle.MEMBER_SELECT_EXPR),
				Node.nodeForWiggle("leftMostId", NodeTypes.IDENTIFIER)));
	}
}
