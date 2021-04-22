package br.com.ubs.carga.controller;

import java.util.ArrayList;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.ubs.carga.modelo.Lojista;
import br.com.ubs.carga.modelo.Carga;
import br.com.ubs.carga.modelo.Carregado;
//import br.com.ubs.carga.modelo.Data;
import br.com.ubs.carga.repository.CargaRepository;
import br.com.ubs.carga.repository.CarregadoRepository;
import br.com.ubs.carga.threads.CargaThread;
import br.com.ubs.carga.controller.dto.CargaDto;
import br.com.ubs.carga.controller.dto.LojistaDto;

@RestController
@RequestMapping("/carga")
public class CargaController {
	
	@Autowired
	private CargaRepository cargaRepository;
	@Autowired
	private CarregadoRepository carregadoRepository;
	
	@GetMapping
	public String carregaArquivos() {
		try {
			
			//TODO validar se o arquivos ja foram carregados
			List<Carregado> carregado = carregadoRepository.findAll();
			Long carregados = carregado.stream().count();
			
			if (carregados !=4) {
				//efetua carga dos arquivos
				CargaThread tarefa = new CargaThread("massa/data_1.json", cargaRepository, carregadoRepository);
				Thread cargaThread = new Thread(tarefa);
				CargaThread tarefa2 = new CargaThread("massa/data_2.json", cargaRepository, carregadoRepository);
				Thread cargaThread2 = new Thread(tarefa2);
				CargaThread tarefa3 = new CargaThread("massa/data_3.json", cargaRepository, carregadoRepository);
				Thread cargaThread3 = new Thread(tarefa3);
				CargaThread tarefa4 = new CargaThread("massa/data_4.json", cargaRepository ,carregadoRepository);
				Thread cargaThread4 = new Thread(tarefa4);
				
				cargaThread.start();
				//cargaThread2.start();
				//cargaThread3.start();
				//cargaThread4.start();
				
				//aguarda a carga para fazer a validacao
				cargaThread.join();
				//cargaThread2.join();
				//cargaThread3.join();
				//cargaThread4.join();
			} else {
				System.out.println("os arquivos ja foram carregados!");
			}
			
		} catch (Exception e) {
			return e.getMessage();
		} 
		
		//verifica duplicados na base
		String duplicados = cargaRepository.findDuplicados();
		if (duplicados != null)
			cargaRepository.deleteDuplicados();;
		
		return "Carga efetuada com sucesso! <br> Para conferir a base de dados: http://localhost:8080/h2-console <br> Para validar o servico: localhost:8080/carga/EMMS/1 <br> Para retornar todos os registros: localhost:8080/carga/listall "+
		"<br><br> queries de exemplo: <br>"+
		"select * from carga where product ='EMMS'; <br>"
		+ "<br>"
		+ "SELECT product, quantity, price, type, industry, origin, COUNT(*) FROM carga GROUP BY product, quantity, price, type, industry, origin HAVING COUNT(*) > 1;<br>"
		+ "<br>"
		+ "select count(*) from carga;";
	}
	
	
	@GetMapping("/listall") 
	public List<CargaDto> listaCarga() {
	  
		List<Carga> cargas = cargaRepository.findAll();
	  	  
	  	return CargaDto.converter(cargas); 
	}
	 
	
	@GetMapping("/{product}/{qtdLojistas}")
	public List<LojistaDto> calculaCarga(@PathVariable String product, 
			@PathVariable Integer qtdLojistas) {
		
		List<Carga> cargas = cargaRepository.findByProduct(product);
		
		//dividir a quantidade por lojista
		
		List<Lojista> lojistas = new ArrayList<>();
		long x = 1;		
		for (Carga c : cargas) {
			
			//for (int x=1 ; x <= qtdLojistas ; x++) {
			
				Lojista lojista = new Lojista();
				//TODO calcular saida para endpoint
				lojista.setId((x) );
				//int qtd = (Integer.parseInt(c.getQuantity())/qtdLojistas);
				lojista.setQuantity(c.getQuantity());
				lojista.setPrice(c.getPrice());
				lojista.setVolume("multiplica quantidade por preco");
				lojista.setQtdTotal("soma da quantidade");
				lojista.setFinanceiro("soma do volume");
				lojista.setPrecoMedio("media dos precos");				
				lojistas.add(lojista);
				
				System.out.println(c);
				x++;
			//}		
		}
		
		
		return CargaDto.converterLojista(lojistas);
	}

}
