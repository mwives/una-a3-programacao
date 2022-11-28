# Projeto Restaurante

Projeto desenvolvido para a UC de Programação de Soluções Computacionais referente ao primeiro semestre do curso de Ciência da Computação da UNA.

## DDL

### Tabela garçons

```sql
CREATE TABLE garcons (
  codigo_garcom INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  data_nascimento DATE NOT NULL,
  email VARCHAR(255) NOT NULL,
  telefone VARCHAR(255) NOT NULL,
  cpf VARCHAR(255) NOT NULL,
  sexo ENUM('MASCULINO',
  'FEMININO',
  'OUTRO') NOT NULL,
  salario_fixo DOUBLE NOT NULL,
  PRIMARY KEY (codigo_garcom)
);
```

### Tabela mesas

```sql
CREATE TABLE mesas(
	codigo_mesa INT PRIMARY KEY AUTO_INCREMENT,
	numero_mesa INT NOT NULL,
	situacao enum('LIVRE',
  'OCUPADA',
  'RESERVADA') NOT NULL,
	capacidade_maxima INT NOT NULL,
	codigo_garcom INT NOT NULL,
	FOREIGN KEY (codigo_garcom) REFERENCES garcons(codigo_garcom) ON DELETE SET NULL
);
```
