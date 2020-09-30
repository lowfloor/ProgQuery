package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchImpl;

public class MethodInvocationServicesWiggle {

	public static MatchElement getMethodInvocationOf(String methodName) {
		return new MatchImpl(
				" (mInv{nodeType:'JCMethodInvocation'})-[:METHODINVOCATION_METHOD_SELECT]->(mSelect{nodeType:'JCFieldAccess',name:'"
						+ methodName
						+ "'})-[:MEMBER_SELECT_EXPR]->(object)");

	}
}
