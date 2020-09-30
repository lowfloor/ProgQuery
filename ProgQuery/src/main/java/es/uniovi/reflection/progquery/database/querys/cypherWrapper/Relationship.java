package es.uniovi.reflection.progquery.database.querys.cypherWrapper;

public interface Relationship extends MatchElement {

	String relToString();

	default String matchToString() {
		return relToString();
	}
}
