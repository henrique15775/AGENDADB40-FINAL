package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Participante;
import modelo.Reuniao;

public class DAOReuniao extends DAO<Reuniao>{
	public Reuniao read (Object chave) {
		int id = (Integer) chave;
		
		Query q = manager.query();
		q.constrain(Reuniao.class);
		q.descend("id").constrain(id);
		List<Reuniao> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;

		}
	
	
	//Consultas
	
	
	public List<Reuniao> readByDigitos(String digitos) {
		Query q = manager.query();
		q.constrain(Reuniao.class);
		q.descend("numero").constrain(digitos).like();
		List<Reuniao> result = q.execute(); 
		return result;
	}
	
	public List<Participante> readByData(String nome, int mes) {
		
		Query q = manager.query();
		q.constrain(Reuniao.class);
		System.out.println(String.valueOf(mes));
		List<Reuniao> reunioes = q.execute();
		List<Participante> reunioes_com_participante = new ArrayList<Participante>();
		
		for(Reuniao r: reunioes) {
			LocalDateTime data = r.getDatahora();
			int month = data.getMonthValue();
			if(r.localizarParticipante(nome) != null && month==mes) {
				reunioes_com_participante.addAll(r.getParticipantes());
			}
		}
		
		return reunioes_com_participante;
	}
	
	/*public List<Reuniao> readAll(){
		Query q = manager.query();
		q.constrain(Reuniao.class);
		q.descend("id");
		List<Reuniao> resultados = q.execute();
		if (resultados.size()>0)
			return resultados;
		else
			return null;
	}*/
	
	public void delete(Reuniao obj) {
		for(Participante p: obj.getParticipantes()) {
			if(p!=null) {
			p.remover(obj);
			}
		}
		manager.delete(obj);
	}
	
	public void create(Reuniao obj){
		obj.setId(getMaxId()+1);
		manager.store(obj);
	}
}