package es.uniovi.reflection.progquery.database.querys.cypherWrapper;

public class SeparationClause implements Clause {

	@Override
	public String clauseToString() {
		return ";";
	}

}
