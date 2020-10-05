package es.uniovi.reflection.progquery.database.querys.eval;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.neo4j.graphdb.GraphDatabaseService;

import es.uniovi.reflection.progquery.database.embedded.EmbeddedDBManager;
import es.uniovi.reflection.progquery.database.queries.sa.bloch.BLOCH_11_75;
import es.uniovi.reflection.progquery.database.queries.sa.bloch.BLOCH_8_45;
import es.uniovi.reflection.progquery.database.queries.sa.bloch.BLOCH_8_45_V2;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Any;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.ClauseImpl;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Filter;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.Reduce;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.pq.OBJ50;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.pq.OBJ54;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.pq.OBJ54_SA;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.pq.OBJ56_SIMPLIFIED;
import es.uniovi.reflection.progquery.database.querys.cypherWrapper.cmu.pq.OBJ56_SIMPLIFIED_SA;
import es.uniovi.reflection.progquery.database.relations.CFGRelationTypes;
import es.uniovi.reflection.progquery.database.relations.RelationTypes;
import es.uniovi.reflection.progquery.database.relations.RelationTypesInterface;
import es.uniovi.reflection.progquery.evaluation.Rule;

public class BlochQueries {
	
	public static final Rule[] RULES = new Rule[] { new Rule(new BLOCH_11_75().queryToString()),  new Rule(new BLOCH_8_45_V2().queryToString())};

	public static void main(String[] args) throws IOException {
		final boolean MEASURING_MEMORY = false;
		int queryIndex = args.length == 0 ?1: Integer.parseInt(args[0]);
		try {
			GraphDatabaseService gs = EmbeddedDBManager.getNewEmbeddedDBService();

			Rule rule =
//					new Rule("MATCH (n) RETURN n")
					 RULES[queryIndex]
					;


			if (args.length == 0)
				System.out.println(rule.queries[0]);
			long ini = System.nanoTime();
			String res = rule.execute(gs).resultAsString();
			long end = System.nanoTime();
			if (!MEASURING_MEMORY) {
				System.err.println(res);
				// String resString = res.resultAsString();
				System.out.print((end - ini) / 1000_000);
			} else {
//				System.out.println("Press any key...");
//				new Scanner(System.in).nextLine();
			}
			if (args.length == 0) {
				// System.out.println(res.hashCode());
				// System.out.println(res.resultAsString());
				int size = 0;
				// System.out.println(size);
				// while (res.hasNext()) {
				// System.out.println(size);
				// res.next();
				// size++;
				//
				// }
				// System.out.println(size);
				// BufferedWriter bw = new BufferedWriter(new
				// FileWriter("outXX.txt"));
				// bw.write(resString);
				// bw.close();

			}
			// gs.execute(DELETE_ALL);}
		} catch (Throwable t) {
			t.printStackTrace(new PrintStream(new FileOutputStream("err" + queryIndex + ".txt")));
			BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
			bw.write(RULES[queryIndex].queries[0]);
			bw.close();
			System.out.println("-1");
		}
	}
}
