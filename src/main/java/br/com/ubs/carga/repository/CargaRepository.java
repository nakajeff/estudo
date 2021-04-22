package br.com.ubs.carga.repository;

import java.util.List;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ubs.carga.modelo.Carga;

public interface CargaRepository extends JpaRepository<Carga, Integer>{

	List<Carga> findByProduct(String product);

	@Query(value = "SELECT product, quantity, price, type, industry, origin, COUNT(*) FROM carga GROUP BY product, quantity, price, type, industry, origin HAVING COUNT(*) > 1;",nativeQuery = true)
	String findDuplicados();

	@Query(value = "delete carga where id > (select min(id) from carga c2 where carga.product = c2.product and carga.quantity = c2.quantity and carga.price = c2.price and carga.type = c2.type and carga.industry = c2.industry and carga.origin = c2.origin );",nativeQuery = true)
	void deleteDuplicados();
	

}
