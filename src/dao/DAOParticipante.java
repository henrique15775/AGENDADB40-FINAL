package dao;

import java.lang.reflect.Field;
import modelo.Participante;
import modelo.Reuniao;


import java.util.List;
import com.db4o.query.Query;

public class DAOParticipante extends DAO<Participante>{
	public Participante read (Object chave) {
		String nome = (String) chave;
		
		Query q = manager.query();
		q.constrain(Participante.class);
		q.descend("nome").constrain(nome);
		List<Participante> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;

		}
	
	

	//Consultas
	
	public  List<Participante> readByCaracteres(String caracteres) {
		Query q = manager.query();
		q.constrain(Participante.class);
		q.descend("nome").constrain(caracteres).like();
		List<Participante> result = q.execute(); 
		return result;
	}
	
	public Participante readByEmail(String e){
		Query q = manager.query();
		q.constrain(Participante.class);
		q.descend("email").constrain(e);
		List<Participante> resultados = q.execute();
		if(resultados.size()==0)
			return null;
		else
			return resultados.get(0);
	}
	
	public List<Participante> readAll(){
		Query q = manager.query();
		q.constrain(Participante.class);
		q.descend("nome");
		List<Participante> resultados = q.execute();
		if (resultados.size()>0)
			return resultados;
		else
			return null;
	}
	
}