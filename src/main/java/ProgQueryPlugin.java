
//import org.neo4j.graphdb.GraphDatabaseService;

import java.time.ZonedDateTime;

import com.sun.source.util.JavacTask;

import es.uniovi.reflection.progquery.database.DatabaseFachade;
import es.uniovi.reflection.progquery.database.EmbeddedGGDBServiceInsertion;
import es.uniovi.reflection.progquery.database.InsertionStrategy;
import es.uniovi.reflection.progquery.database.Neo4jDriverLazyWrapperInsertion;
import es.uniovi.reflection.progquery.tasklisteners.GetStructuresAfterAnalyze;

public class ProgQueryPlugin implements com.sun.source.util.Plugin {

	private static final String PLUGIN_NAME = "ProgQueryPlugin";

	@Override
	public void init(JavacTask task, String[] args) {
		// final GraphDatabaseService graphDb = args.length > 0 ?
		// DatabaseFachade.getDB(args[0]) : DatabaseFachade.getDB();
		// First argument if any contents the db path

		// DatabaseFachade.setDB(graphDb);
		String id = args.length == 0
				? "NO_SPECIFIED_ID_" + ZonedDateTime.now()
				: args[0];
		try {
			DatabaseFachade.init(this.loadInsertion(args));
			task.addTaskListener(new GetStructuresAfterAnalyze(task, id));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private InsertionStrategy loadInsertion(String[] args) {
		return args.length == 1
				? new EmbeddedGGDBServiceInsertion()
				: args[1].contains("S")
						? args.length == 2
								? invalidArgs()
								: args.length == 3
										? serverTwoArgs(args[2])
										: new Neo4jDriverLazyWrapperInsertion(
												Integer.parseInt(args[3]),
												args[2])
						: args.length > 2
								? new EmbeddedGGDBServiceInsertion(args[2])
								: new EmbeddedGGDBServiceInsertion();
	}

	private InsertionStrategy invalidArgs() {
		throw new IllegalArgumentException(
				"You need to specify the connection string to run PQ Server");
	}

	private InsertionStrategy serverTwoArgs(String arg2) {

		return new Neo4jDriverLazyWrapperInsertion(arg2);
	}

	@Override
	public String getName() {
		return PLUGIN_NAME;
	}

}
