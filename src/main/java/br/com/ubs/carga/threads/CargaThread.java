package br.com.ubs.carga.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ubs.carga.modelo.Carga;
import br.com.ubs.carga.modelo.Carregado;
import br.com.ubs.carga.modelo.Data;
import br.com.ubs.carga.repository.CargaRepository;
import br.com.ubs.carga.repository.CarregadoRepository;

public class CargaThread implements Runnable{
	
	public CargaThread(String arquivo,
			CargaRepository cargaRepository,
			CarregadoRepository carregadoRepository) {
			        this.arquivo = arquivo;
			        this.cargaRepository = cargaRepository;
			        this.carregadoRepository = carregadoRepository;
	}
	
	private String arquivo;
	
	private CargaRepository cargaRepository;
	private CarregadoRepository carregadoRepository;
	
	@Override
	public void run() {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = new FileInputStream(new File(this.arquivo));
			TypeReference<Data> typeReference = new TypeReference<Data>() {};
			Data cargas = mapper.readValue(inputStream, typeReference);
			System.out.println(cargas.getData().size());
			for (Carga c : cargas.getData()) { 
				//System.out.println(c.getProduct()); 
				cargaRepository.save(c);
				
			}
			
			Carregado carregado = new Carregado();
			carregado.setFile(this.arquivo);
				carregado.setQuantity(Integer.toString(cargas.getData().size()));
			
			carregadoRepository.save(carregado);
		
		}catch (Exception e) {
			System.out.println(e.getMessage()); 
		}
	}

}
