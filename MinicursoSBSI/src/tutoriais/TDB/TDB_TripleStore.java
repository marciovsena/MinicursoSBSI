package tutoriais.TDB;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 * 
 * @author Ernesto
 *
 */

public class TDB_TripleStore {
	
	Dataset dataset = null;

	public TDB_TripleStore() {
		String diretorio = "MinicursoSBSI_DatabaseTDB/Dataset_example";
		dataset = TDBFactory.createDataset(diretorio);
	}

	public void updateModel(Model model) {
		try {
			dataset.begin(ReadWrite.WRITE);
			Model modelTDB = dataset.getDefaultModel();
			modelTDB.add(model);
			dataset.commit();
			System.out.println("Modelo armazenado com sucesso!\n");
		} finally {
			dataset.end();
		}
	}

	public void queryModel(String query) {
		dataset.begin(ReadWrite.READ);
		try {
			QueryExecution qexec = QueryExecutionFactory.create(query, dataset);
			ResultSet rs = qexec.execSelect();
			try {
				ResultSetFormatter.out(rs);
			} finally {
				qexec.close();
			}
		} finally {
			dataset.end();
		}
	}
	
	public void removeModel(Model model) {
		try {
			dataset.begin(ReadWrite.WRITE);
			Model modelTDB = dataset.getDefaultModel();
			modelTDB.remove(model);
			dataset.commit();
			System.out.println("Modelo deletado com sucesso!\n");
		} finally {
			dataset.end();
		}
	}

	public void close() {
		dataset.close();
	}	

}

