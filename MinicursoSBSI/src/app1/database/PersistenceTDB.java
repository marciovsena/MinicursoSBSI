package app1.database;

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

public class PersistenceTDB {
	
	Dataset dataset = null;

	public PersistenceTDB() {
		String diretorio = "MinicursoSBSI_DatabaseTDB/Dataset_app1";
		dataset = TDBFactory.createDataset(diretorio);
	}

	/**
	 * Recebe modelo e persite utilizando TDB
	 * @param model modelo RDF
	 */
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

	/**
	 * Recebe query e realiza a consulta no modelo armazenado em TDB
	 * @param query string SPARQL
	 */
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

	/**
	 * Recebe modelo e remove-o do TDB
	 * @param model modelo RDF
	 */
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

	/**
	 * Recebe modelo e remove-o do TDB
	 * @param model modelo RDF
	 */
	public void closeConection() {
		dataset.close();
	}	

}
