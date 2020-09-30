package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Element;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;

public interface CFGServices {

	public static final CFGServices PROG_QUERY = new CFGServicesProgQueryImpl();
	public static final CFGServices WIGGLE = new CFGServicesWiggle();

	// public static final PDGServices WIGGLE = ;
	public Element getCFGSuccesorsOf(MatchElement stat);

	public Element getCFGSuccesorsOf(MatchElement stat, String elementsToPreserve);
	// (assignStat)-[" + cfgSuccesor + "*0..]->(useStat) "
	public MatchElement getCFGSuccesorsAndItSelfOf(MatchElement p);
}
