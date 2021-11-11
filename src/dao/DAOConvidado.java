package dao;


import java.util.ArrayList;
import java.util.List;
import com.db4o.query.Query;
import java.lang.reflect.Field;

import modelo.Convidado;
import modelo.Participante;
import modelo.Reuniao;


public class DAOConvidado extends DAO<Convidado>{
	public Convidado read (Object chave) {
		String nome = (String) chave;
		
		Query q = manager.query();
		q.constrain(Convidado.class);
		q.descend("nome").constrain(nome);
		List<Convidado> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;

		}
	
	

	//Consultas
	
	public List<Reuniao> reuniaoTemConvidado() {
		Query q = manager.query();
		q.constrain(Convidado.class);
		List<Convidado> convidados = q.execute();
		ArrayList<Reuniao> reunioes_com_convidado = new ArrayList<Reuniao>();
		for(Convidado c: convidados) {
			for(Reuniao r: c.getReunioes()) {
				if(reunioes_com_convidado.contains(r) == false) {
					reunioes_com_convidado.add(r);
				}
				}
			}
		return reunioes_com_convidado;	
		}
		
	
	
	public  List<Convidado> readByCaracteres(String caracteres) {
		Query q = manager.query();
		q.constrain(Convidado.class);
		q.descend("nome").constrain(caracteres).like();
		List<Convidado> result = q.execute(); 
		return result;
	}
	
	public Convidado readByEmail(String e){
		Query q = manager.query();
		q.constrain(Convidado.class);
		q.descend("email").constrain(e);
		List<Convidado> resultados = q.execute();
		if(resultados.size()==0)
			return null;
		
		return resultados.get(0);
	}
	
	public List<Convidado> readAll(){
		Query q = manager.query();
		q.constrain(Convidado.class);
		q.descend("nome");
		List<Convidado> resultados = q.execute();
		if (resultados.size()>0)
			return resultados;
		else
			return null;
	}
	
}
