package es.uniovi.reflection.progquery.utils.dataTransferClasses;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Cardinalidad;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Edge;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeDirection;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.EdgeImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.relations.PartialRelation;
import es.uniovi.reflection.progquery.database.relations.RelationTypes;
import es.uniovi.reflection.progquery.database.relations.RelationTypesInterface;
import es.uniovi.reflection.progquery.database.relations.SimplePartialRelation;
import es.uniovi.reflection.progquery.node_wrappers.NodeWrapper;

public class Pair<X, Y> {
	
	private final X x;
	private final Y y;
	
	private Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	public static <X,Y>  Pair<X,Y> create(X x, Y y)
	{
		return new Pair<X, Y>(x, y);
	}
	public X getFirst() {
		return x;
	}

	public Y getSecond() {
		return y;
	}


	public static Pair<PartialRelation<RelationTypes>, Object> createPair(NodeWrapper node, RelationTypes r,
			Object arg) {
		return Pair.create(new SimplePartialRelation<RelationTypes>(node, r), arg);
	}

	public static Pair<PartialRelation<RelationTypes>, Object> createPair(NodeWrapper node, RelationTypes r) {
		return Pair.create(new SimplePartialRelation<RelationTypes>(node, r), null);
	}

	public static Pair<PartialRelation<RelationTypes>, Object> createPair(PartialRelation<RelationTypes> rel) {
		return Pair.create(rel, null);
	}

	public static Pair<Edge, es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement> createP(String nodeName,
			RelationTypesInterface... rel) {
		return Pair.create(new EdgeImpl(rel), new NodeVar(nodeName));
	}

	public static Pair<Edge, es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement> createP(String nodeName, Cardinalidad c,
			RelationTypesInterface... rel) {
		return Pair.create(new EdgeImpl(c, rel), new NodeVar(nodeName));
	}
	public static Pair<Edge, es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement> createP(MatchElement node,
			RelationTypesInterface... rel) {
		return Pair.create(new EdgeImpl(rel), node);
	}
	public static Pair<Edge, es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement> createInv(String nodeName,
			RelationTypesInterface... rel) {
		return Pair.create(new EdgeImpl(EdgeDirection.OUTGOING, rel), new NodeVar(nodeName));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

}
