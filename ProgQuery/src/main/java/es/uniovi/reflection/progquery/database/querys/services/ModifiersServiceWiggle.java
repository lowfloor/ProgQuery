package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.MatchElement;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.NodeVar;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Path;
import es.uniovi.reflection.progquery.database.relations.RelationTypesWiggle;
import es.uniovi.reflection.progquery.utils.dataTransferClasses.Pair;

public class ModifiersServiceWiggle {

	public static MatchElement getClassModifiers(MatchElement classNode) {
		return new Path(classNode,
				Pair.createP(new NodeVar("classModifiers"),
				RelationTypesWiggle.HAS_CLASS_MODIFIERS));
	}

	public static MatchElement getMethodModifiers(MatchElement methodNode) {
		return new Path(methodNode,
				Pair.createP(new NodeVar("methodModifiers"), RelationTypesWiggle.HAS_METHODDECL_MODIFIERS));
	}
}
