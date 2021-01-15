package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchClause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Node;

public interface PDGServices {
	public static final PDGServices PROG_QUERY = new PDGServicesProgQueryImpl();
	// public static final PDGServices WIGGLE = ;
	public static final PDGServices WIGGLE = new PDGServicesWiggle();

	public MatchClause getOptionalUsesAndStateModsAndStatementsOf(Node declaration);

	public MatchClause getOptionalUsesAndStatementsOf(Node declaration);

	public MatchClause getOptionalUsesAndStatementsOf(Node declaration, Node use);

	public MatchElement getUsesOf(Node declaration);

	public MatchElement getUsesOf(Node declaration, Node use);

	public MatchElement getAssingnmentsAndVarDeclarations(MatchElement assignment);

	public MatchElement getAssingnmentsAndVarDeclarations(Node assignment);

	public MatchElement getIdsAndVarDeclarations(MatchElement id);

	MatchElement getIdsAndVarDeclarations(MatchElement id, String varsToPreserve);

	Clause getModificationsOnFields(String fieldSetName, String elementsToPreserve);

	MatchElement getCompleteIdentification(MatchElement id, String varsToPreserve);

	MatchElement getIdsAndVarDeclarations(MatchElement id, MatchElement varDec, String varsToPreserve);

	MatchElement getCompleteIdentificationFromVar(MatchElement varDec, String varsToPreserve);

	MatchElement getCompleteIdentificationFromVar(String varsToPreserve);
}
