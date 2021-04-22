
Para efetuar a carga http://localhost:8080/carga
Para conferir a base de dados: http://localhost:8080/h2-console
Para validar o servico: localhost:8080/carga/EMMS/1
Para retornar todos os registros: localhost:8080/carga/listall

TODO - faltou a metade da parte dois calculo de estoque para lojistas



queries de exemplo:
select * from carga where product ='EMMS';

SELECT product, quantity, price, type, industry, origin, COUNT(*) FROM carga GROUP BY product, quantity, price, type, industry, origin HAVING COUNT(*) > 1;

select count(*) from carga;
