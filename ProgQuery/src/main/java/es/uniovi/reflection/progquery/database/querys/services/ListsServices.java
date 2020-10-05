package es.uniovi.reflection.progquery.database.querys.services;

import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Binop;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Clause;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ExprImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Expression;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ForEach;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Function;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.SetClause;

public class ListsServices {
	public static Clause forAllEvensInList(String length, int space, String accum, String condition) {
		return forAllEvensInList(new ExprImpl(length), space, new ExprImpl(accum), new ExprImpl(condition));
	}

	public static Clause forAllEvensInList(Expression lenght, int space, Expression accum, Expression condition) {
		return new ForEach("i", new Function("RANGE", new ExprImpl("0"), lenght, new ExprImpl(space + "")),
				new SetClause(accum, new Binop("AND", accum, condition)));
	}
}
